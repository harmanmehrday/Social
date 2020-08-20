<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Post</title>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
		if(user == null){
			%>
				<script>alert("You are logged off.");
				window.location.replace("index.jsp");</script>
			<% 
		}
	%>
	<h1>${user.getFname()} ${user.getLname()}</h1><h3><a href="logout?for=1">LOG OUT</a></h3>
	
	<a href="DisplayPost">Home</a>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="userPost">My Posts</a>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="profile">My Profile</a>
	
	<form action="CreatePost" method="post" name="createpost">
		<h1>Create Post</h1>
	  		<label for="posttext">Post :</label><br>
	  		<textarea  id="posttext" name="posttext" form="createpost" rows="4" cols="50"></textarea><br><br>
	  		<input type="submit" value="Submit">
	  		<br>
	</form>
</body>
</html>