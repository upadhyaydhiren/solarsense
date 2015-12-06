$(document).ready(function() {
	var exampleBarChartData = {
		"datasets": {
			"values": [56, 79, 31, 10, 17,89,72],
			"labels": [
				"Sunday", 
				"Monday", 
				"Tuesday", 
				"Wednesday", 
				"Thursday",
				"Friday",
				"Saturday"
			],
			"color": "blue"
		},
		"title": "Example Bar Chart",
		"height": "300px",
		"width": "1000px",
		"background": "#FFFFFF",
		"shadowDepth": "1"
	};

	MaterialCharts.bar("#bar-chart-example", exampleBarChartData)

	var examplePieChartData = {
		"dataset": {
			"values": [56, 79, 31, 10, 17,89,72],
			"labels": [
				"Sunday", 
				"Monday", 
				"Tuesday", 
				"Wednesday", 
				"Thursday",
				"Friday",
				"Saturday"
			],
		},
		"title": "Example Pie Chart",
		"height": "300px",
		"width": "1000px",
		"background": "#FFFFFF",
		"shadowDepth": "1"
	};

	MaterialCharts.pie("#pie-chart-example", examplePieChartData)
});
