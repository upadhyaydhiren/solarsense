<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style>
body {
	padding-top: 120px;
	padding-bottom: 40px;
	background-color: #eee;
}

.btn {
	outline: 0;
	border: none;
	border-top: none;
	border-bottom: none;
	border-left: none;
	border-right: none;
	box-shadow: inset 2px -3px rgba(0, 0, 0, 0.15);
}

.btn:focus {
	outline: 0;
	-webkit-outline: 0;
	-moz-outline: 0;
}

.fullscreen_bg {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background-size: cover;
	background-position: 50% 50%;
	background-image:
		url('http://solarcitiescanada.com/wp-content/uploads/solarcity.jpg');
	background-repeat: repeat;
}

.form-signin {
	max-width: 280px;
	padding: 15px;
	margin: 0 auto;
	margin-top: 50px;
}

.form-signin .form-signin-heading, .form-signin {
	margin-bottom: 10px;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: none;
	border-left-style: solid;
	border-color: #000;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-color: rgb(0, 0, 0);
	border-top: 1px solid rgba(0, 0, 0, 0.08);
}

.form-signin-heading {
	color: #fff;
	text-align: center;
	text-shadow: 0 4px 4px rgba(0, 0, 0, 0.5);
}
</style>

</head>
<body>
	<div id="fullscreen_bg" class="fullscreen_bg">

		<div class="container">

			<form:form cssClass="form-signin" modelAttribute="user" method="post"
				action="j_spring_security_check">
				<h1 class="form-signin-heading text-muted">Sign In</h1>
				<form:input cssClass="form-control" placeholder="Email address"
					autofocus="autofocus" required="required" path="userName" />
				<form:password cssClass="form-control" placeholder="Password"
					required="required" path="password" />
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">
					Sign In</form:button>
			</form:form>
		</div>
	</div>
</body>
</html>
