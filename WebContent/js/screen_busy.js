var xval;
function blockScreen(element) {
	xval = getBusyOverlay(
			'undefined' == typeof(element) ? 'viewport' : element,
			{
				color : 'black',
				opacity : 0.3,
				text : 'viewport: loading...',
				style : 'text-shadow: 0 0 3px black;font-weight:bold;font-size:16px;color:white'
			}, {
				color : 'white',
				size : 32,
				type : 'tube'
			});
	if (xval) {
		xval.settext('加载数据 ...');
	}
}

function releaseScreen() {
	if ("object" == typeof(xval)) {
		xval.remove();
	}
}