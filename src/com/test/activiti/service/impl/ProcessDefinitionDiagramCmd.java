package com.test.activiti.service.impl;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class ProcessDefinitionDiagramCmd implements Command<InputStream> {
	private String processDefinitionId;
	private List<String> activeActivityIds;
	private List<String> highLightedFlows;

	public ProcessDefinitionDiagramCmd(String processDefinitionId, List<String> activeActivityIds, List<String> highLightedFlows) {
		this.processDefinitionId = processDefinitionId;
		this.activeActivityIds = activeActivityIds;
		this.highLightedFlows = highLightedFlows;
	}

	public InputStream execute(CommandContext commandContext) {

		GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(processDefinitionId);
		BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);

		InputStream is = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds, highLightedFlows);

		return is;
	}
	
}
