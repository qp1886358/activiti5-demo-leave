package com.test.activiti.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.activiti.dao.IProcessRecordDao;
import com.test.activiti.model.ProcessRecord;
import com.test.activiti.service.IProcessService;

@Service("processService")
public class ProcessService implements IProcessService {

	private static final Logger logger = Logger.getLogger(ProcessService.class);

	@Autowired(required = true)
	@Qualifier("repositoryService")
	RepositoryService repositoryService;

	@Autowired(required = true)
	@Qualifier("runtimeService")
	RuntimeService runtimeService;

	@Autowired(required = true)
	@Qualifier("taskService")
	TaskService taskService;

	@Autowired(required = true)
	@Qualifier("formService")
	FormService formService;

	@Autowired(required = true)
	@Qualifier("processEngine")
	ProcessEngine processEngine;

	@Autowired(required = true)
	@Qualifier("historyService")
	HistoryService historyService;
	
	@Autowired(required = true)
	@Qualifier("processRecordDao")
	IProcessRecordDao processRecordDao;
	
	@Override
	public void startProcess(String processName, String startUserName, Integer leaveDayNumber, String leaveReason) {
		
		logger.info("Begin to start process: " + processName);
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionNameLike(processName).singleResult();

		Map<String, String> formProperties = new HashMap<String, String>();
		formProperties.put("leaveDayNumber", String.valueOf(leaveDayNumber));
		formProperties.put("leaveReason", leaveReason);
		formProperties.put("leaveApplicant", startUserName);

		ProcessRecord processRecord = new ProcessRecord();
		processRecord.setApplicant(startUserName);
		processRecordDao.addProcessRecord(processRecord);
		
		formProperties.put("businessKey", String.valueOf(processRecord.getId()));
		
		ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), String.valueOf(processRecord.getId()), formProperties);

		logger.info("Start process: " +  processName + " successfully.");
		
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
		for (Task task : taskList) {
			if ("uerTaskFillApplication".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), startUserName);

				formProperties.put("previousTaskId", task.getId());

				HistoricTaskInstance hisTask = historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
				formProperties.put("previousTaskDefinitionKey", hisTask.getTaskDefinitionKey());

				formService.submitTaskFormData(task.getId(), formProperties);
			}
		}

		taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
		for (Task task : taskList) {
			if ("userTaskApprovalHr".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), "hr");
			} else if ("userTaskApprovalManager".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), "manager");
			}
		}
		
		logger.info("Distribute the first task successfully.");
	}

	@Override
	public String getStartForm(String processName) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionNameLike(processName).singleResult();
		String startForm = formService.getRenderedStartForm(processDefinition.getId()).toString();
		return startForm;
	}

	@Override
	public void exportProcessImg(String taskId, HttpServletResponse response) {

		String executionId = "";
		String processDefinitionId = "";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (null == task) {
            HistoricTaskInstance hisTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
            executionId = hisTask.getExecutionId();
            processDefinitionId = hisTask.getProcessDefinitionId();
		}
		else {
			executionId = task.getExecutionId();
			processDefinitionId = task.getProcessDefinitionId();
		}
		
		List<String> activeActivityIds = getActivityIds(executionId);
		List<String> highLightedFlows = getHighLightedFlows(processDefinitionId, executionId);
		Command<InputStream> cmd = new ProcessDefinitionDiagramCmd(processDefinitionId, activeActivityIds, highLightedFlows);
		InputStream imageStream = processEngine.getManagementService().executeCommand(cmd);


		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		try {
			while ((len = imageStream.read(b, 0, b.length)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (imageStream != null) {
					imageStream.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}

	}

	public List<String> getActivityIds(String executionId) {
		List<String> activeActivityIds = new ArrayList<String>();

		if (isProcessFinished(executionId)) {
			// 如果流程已经结束，则得到结束节点
			activeActivityIds.add(historyService.createHistoricActivityInstanceQuery().executionId(executionId).activityType("endEvent").singleResult()
					.getActivityId());
		} else {
			// 如果流程没有结束，则取当前活动节点
			// 根据流程实例ID获得当前处于活动状态的ActivityId合集
			activeActivityIds = runtimeService.getActiveActivityIds(executionId);
		}
		
		return activeActivityIds;
	}
	
	public boolean isProcessFinished(String processInstanceId) {
		return historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processInstanceId).count() > 0;
	}

	public List<String> getHighLightedFlows(String processDefinitionId, String executionId) {

		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
		
		// 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
		List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().executionId(executionId)
				.orderByHistoricActivityInstanceStartTime().asc().list();
		
		List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
		for (int i = 0; i < historicActivityInstances.size(); i++) {// 对历史流程节点进行遍历
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());// 得
																																// 到节点定义的详细信息
			List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
			if ((i + 1) >= historicActivityInstances.size()) {
				break;
			}
			ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());// 将后面第一个节点放在时间相同节点的集合里
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
				if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {// 如果第一个节点和第二个节点开始时间相同保存
					ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
					sameStartTimeNodes.add(sameActivityImpl2);
				} else {// 有不相同跳出循环
					break;
				}
			}
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();// 取出节点的所有出去的线
			for (PvmTransition pvmTransition : pvmTransitions) {// 对所有的线进行遍历
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
				if (sameStartTimeNodes.contains(pvmActivityImpl)) {
					highFlows.add(pvmTransition.getId());
				}
			}
		}
		
		return highFlows;
	}

}
