<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Spark</title>
</head>
<body>
	
	<div style="float:left; margin-left:300px;">
		<form action="CreateUser" method="post">
			<h1>Sign Up</h1>
	  		<label for="fname">First Name :</label><br>
	  		<input type="text" id="fname" name="fname"><br><br>
	  		<label for="lname">Last Name :</label><br>
	  		<input type="text" id="lname" name="lname"><br><br>
	  		<label for="email">E-Mail :</label><br>
	  		<input type="text" id="email" name="email"><br><br>
	  		<label for="password">Password :</label><br>
	  		<input type="text" id="password" name="password"><br><br>
	  		<input type="submit" value="Submit">
	  		<br>
	  	</form>
	</div> 	
  	<div style="float:right; margin-right: 300px;">
	  	<form action="LoginUser" method="post">
			<h1>Login </h1>	
	  		<label for="logemail">E-Mail :</label><br>
	  		<input type="text" id="logemail" name="logemail"><br><br>
	  		<label for="logpassword">Password :</label><br>
	  		<input type="text" id="logpassword" name="logpassword"><br><br>
	  		<input type="submit" value="Submit">
	  		<br><br>
		</form>
	</div>
	<input type="text" id="result" name="result" value="${result}" style="width:500px; font-size:30px;margin-top:140px;margin-left:-30px;"/><br>
</body>
</html>