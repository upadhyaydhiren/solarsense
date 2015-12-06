<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html lang="en">
<head>
<title>Home</title>
<meta charset="utf-8">
<security:csrfMetaTags />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="${pageContext.request.contextPath}/resources/assets/css/example.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/assets/css/material-charts.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/2.10.6/moment.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>

<script src="http://maps.googleapis.com/maps/api/js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/js/material-charts.js"></script>
<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Roboto:400,300);

@import
	url(http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css)
	;

body {
	background-color: #4a4a4a;
	color: white !important;
}

td {
	line-height: 0.5 !important;
}

th {
	line-height: 0.5 !important;
}

.brdr {
	border: 1px solid white;
}

.grph {
	height: 400px;
	padding: 10px;
}

.gap {
	margin-top: 1%
}

.checkbox label:after, .radio label:after {
	content: '';
	display: table;
	clear: both;
}

.checkbox .cr, .radio .cr {
	position: relative;
	display: inline-block;
	border: 1px solid #a9a9a9;
	border-radius: .25em;
	width: 1.3em;
	height: 1.3em;
	float: left;
	margin-right: .5em;
}

.radio .cr {
	border-radius: 50%;
}

.checkbox .cr .cr-icon, .radio .cr .cr-icon {
	position: absolute;
	font-size: .8em;
	line-height: 0;
	top: 50%;
	left: 20%;
}

.radio .cr .cr-icon {
	margin-left: 0.04em;
}

.checkbox label input[type="checkbox"], .radio label input[type="radio"]
	{
	display: none;
}

.checkbox label input[type="checkbox"]+.cr>.cr-icon, .radio label input[type="radio"]+.cr>.cr-icon
	{
	transform: scale(3) rotateZ(-20deg);
	opacity: 0;
	transition: all .3s ease-in;
}

.checkbox label input[type="checkbox"]:checked+.cr>.cr-icon, .radio label input[type="radio"]:checked+.cr>.cr-icon
	{
	transform: scale(1) rotateZ(0deg);
	opacity: 1;
}

.checkbox label input[type="checkbox"]:disabled+.cr, .radio label input[type="radio"]:disabled+.cr
	{
	opacity: .5;
}

@import url(http://fonts.googleapis.com/css?family=Roboto:400,300);

@import
	url(http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css)
	;

body {
	padding: 70px 0px;
}

.fa.pull-right {
	margin-left: 0.1em;
}

.date-picker, .date-container {
	position: relative;
	display: inline-block;
	width: 100%;
	color: white;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.date-container {
	padding: 0px 40px;
}

.date-picker h2, .date-picker h4 {
	margin: 0px;
	padding: 0px;
	font-family: 'Roboto', sans-serif;
	font-weight: 200;
}

.date-container .date {
	text-align: center;
}

.date-picker span.fa {
	position: absolute;
	font-size: 4em;
	font-weight: 100;
	padding: 8px 0px 7px;
	cursor: pointer;
	top: 0px;
}

.date-picker span.fa[data-type="subtract"] {
	left: 0px;
}

.date-picker span.fa[data-type="add"] {
	right: 0px;
}

.date-picker span[data-toggle="calendar"] {
	display: block;
	position: absolute;
	top: -7px;
	right: 45px;
	font-size: 1em !important;
	cursor: pointer;
}

.date-picker .input-datepicker {
	display: none;
	position: absolute;
	top: 50%;
	margin-top: -17px;
	width: 100%;
}

.date-picker .input-datepicker.show-input {
	display: table;
}

h2 {
	font-size: 26px;
}

@media ( min-width : 768px) and (max-width: 1010px) {
	.date-picker h2 {
		font-size: 1.5em;
		font-weight: 400;
	}
	.date-picker h4 {
		font-size: 1.1em;
	}
	.date-picker span.fa {
		font-size: 3em;
	}
}
</style>
<script type="text/javascript">
	var map;
	function initialize() {
		var mapProp = {
			center : new google.maps.LatLng(23.191499, 72.630434),
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	}
	var address = {};
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div class="col-sm-12" data-spy="affix" data-offset-top="197"
		style="z-index: 1000; margin-top: -70px; background-color: black">
		<div class="row text-center brdr" style="">
			<div class="col-sm-3 brdr">
				Total Generation<br> <strong style="font-size: 24px"
					id="pconsume"></strong>
			</div>
			<div class="col-sm-3 brdr">
				Grid Terrif<br> <strong style="font-size: 24px" id="tarrif"></strong>
			</div>
			<div class="col-sm-3 brdr">
				Consume Amount<br> <strong style="font-size: 24px"
					id="pconsumetotal"></strong>
			</div>
			<div class="col-sm-3 text-left">

				<div class="row text-center" style="font-size: 24px">
					Welcome &nbsp;&nbsp;&nbsp; <a class="btn btn-xs btn-danger"
						style="float: right; margin: 2% 5% 2% 2%" href="logout"> <i
						class="glyphicon glyphicon-off"></i>&nbsp;Sign Out
					</a>
				</div>
				<div class="row">
					<c:if test="${fn:contains(user.userName, '@')}">&nbsp;&nbsp; <i
							class="glyphicon glyphicon-envelope"></i>&nbsp;${user.userName}</c:if>
					<c:if test="${! fn:contains(user.userName, '@')}">
						<i class="glyphicon glyphicon-phone"></i>&nbsp;${user.userName}
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="col-sm-3" style="height: 100vh;">
			<div class="row">
				<c:forEach items="${user.meters}" var="meter">
					<div class="checkbox" style="margin-top: 1%; padding-left: 20%">
						<label style="font-size: 1.5em"> <input type="checkbox"
							value="${meter.id}" class="meter"> <span class="cr"><i
								class="cr-icon glyphicon glyphicon-ok"></i></span>
							${meter.meterSerialNo}
						</label>
						<script type="text/javascript">
							var locations = {};
							locations['latitude'] = '${meter.latitude}';
							locations['longitude'] = '${meter.longitude}';
							address['${meter.id}'] = locations;
						</script>
					</div>
				</c:forEach>
			</div>


		</div>
		<div class="col-sm-9">
			<div class="row gap">


				<div id="googleMap" style="width: 100%; height: 380px;"></div>


			</div>
			<div class="row gap">
				<div class="col-md-12">
					<div class="col-md-1 text-right" style="line-height: 2.50">View
						:</div>
					<div class="col-md-2">
						<div class="form-group">
							<select id="view" class="form-control"
								onchange="loadChartData(this);">
								<option value="daily">Daily View</option>
								<option value="weekly">Weekly View</option>
								<option value="monthly">Monthly View</option>
							</select>
						</div>
					</div>

					<div class="col-md-9">
						<table class="table table-bordered">
							<thead style="background-color: #2c2c2c">
								<strong>
									<tr>
										<th colspan="4" class="text-center">Instant Data</th>
									</tr>
									<tr>
										<th>Voltage</th>
										<th>Current</th>
										<th>Power Factor</th>
										<th>Power</th>
									</tr>
								</strong>
							</thead>
							<tbody id="instant_data">
							</tbody>
						</table>
					</div>
				</div>
			</div>


			<div class="row gap">

				<div class="example-container clearfix">
					<div class="example-chart">
						<div id="bar-chart-div" style="width: auto !important;"></div>
					</div>
					<div class="example-code">
						<pre style="display: none">
                                              
                        </pre>

					</div>

				</div>
			</div>
			<div class="row gap">
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-4"></div>
					<div class="col-md-2"></div>
				</div>
			</div>
			<div class="row gap" style="margin-bottom: 5%">
				<div id="chart_div" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1.1','packages':['corechart']}]}"></script>
	<script>
		google.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = google.visualization.arrayToDataTable([
					[ 'Day', 'Consuption' ], [ 'Sunday', 1000 ],
					[ 'Saturday', 100 ], [ 'Monday', 660 ], [ 'Tuesday', 500 ],
					[ 'Wednesday', 800 ], [ 'Thursday', 320 ],
					[ 'Friday', 390 ]

			]);

			var options = {
				title : 'Company Performance',
				hAxis : {
					title : 'Week',
					titleTextStyle : {
						color : '#333'
					}
				},
				vAxis : {
					minValue : 0
				}
			};

			var chart = new google.visualization.AreaChart(document
					.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
	<script>
		$(document).ready(
				function() {
					var exampleBarChartData = {
						"datasets" : {
							"values" : [ 56, 79, 31, 10, 17, 89, 72 ],
							"labels" : [ "Sunday", "Monday", "Tuesday",
									"Wednesday", "Thursday", "Friday",
									"Saturday" ],
							"color" : "blue"
						},
						"title" : "Electric Consuption",
						"height" : "300px",
						"width" : "988px",
						"background" : "#FFFFFF",
						"shadowDepth" : "1"
					};

					MaterialCharts.bar("#bar-chart-div", exampleBarChartData)

				});
	</script>
	<script type="text/javascript">
		var chartData = [];
		var scaterchart = [];
		$('.meter').click(
				function() {
					$('.meter').attr('checked', false);
					$(this).prop('checked', true);
					var marker = new google.maps.Marker({
						position : new google.maps.LatLng(
								address[$(this).val()].latitude,
								address[$(this).val()].longitude),
						map : map
					});
					loadInstantData($(this).val());
				});
		function loadInstantData(param) {
			var csrfParameter = $("meta[name='_csrf_parameter']").attr(
					"content");
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			var url = '<c:url value="/loadinstantdata/'+param+'" />';
			var headers = {};
			headers[csrfHeader] = csrfToken;
			$("#instant_data").html("");
			$
					.ajax({
						url : url,
						type : "POST",
						headers : headers,
						contentType : "application/json; charset=utf-8",
						success : function(data) {
							if (data != null && data != "") {
								var dataString = "<tr><td>" + data.voltage
										+ "(V)</td><td>" + data.current
										+ "(A)</td><td>" + data.powerFactor
										+ "(PF)</td><td>" + data.power
										+ "(W)</td><tr>";
								$("#instant_data").html(dataString);
							} else {
								$("#instant_data")
										.html(
												"<tr><td colspan='4'>No Data Recently Found<td></tr>");
							}
						},
						error : function(res) {
							console.log("Bad thing happend! "
									+ res.responseText);
						}
					});
		}
		function loadChartData(param) {
			var csrfParameter = $("meta[name='_csrf_parameter']").attr(
					"content");
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			var url = '<c:url value="/loaddata/' + $('.meter').val() + '" />';
			var headers = {};
			headers[csrfHeader] = csrfToken;
			$.ajax({
				url : url,
				type : "POST",
				headers : headers,
				data : {
					dataview : param.value
				},
				success : function(data) {
					var labels = [];
					var values = [];
					var message = [];
					if (param.value == 'daily') {
						message.push('Hour');
					} else if (param.value == 'weekly') {
						message.push('Day');
					} else if (param.value = 'Monthly') {
						message.push('Month');
					}
					message.push('Usages');
					scaterchart.push(message);
					if (data != null && data != "") {
						var calulation = 0;
						$.each(data, function(k, v) {
							var arr = [];
							var arrval = 0;
							arr.push(k);
							labels.push(k);
							var value = 0;
							$.each(v, function(i, j) {
								value += j.power / 4000;
								arrval += j.power;
							});
							arr.push(arrval);
							scaterchart.push(arr);
							calulation += value;
							$('#pconsume').empty();
							$('#pconsume').html(calulation + " Kwh");
							$('#tarrif').empty();
							$('#tarrif').html(
									"<i class='fa fa-inr'></i>." + 5.5);
							$('#pconsumetotal').empty();
							$('#pconsumetotal').html(
									"<i class='fa fa-inr'></i>."
											+ (calulation * 5.5));
							values.push(value);
						});
						//console.log(scaterchart);
						// 						MaterialCharts.bar("#bar-chart-div",
						// 								exampleBarChartData);
					} else {
						alert("Nothing to display");
					}
				},
				error : function(res) {
					console.log("Bad thing happend! " + res.responseText);
				}
			});
		}
		google.setOnLoadCallback(drawChart);
	</script>
</body>
</html>