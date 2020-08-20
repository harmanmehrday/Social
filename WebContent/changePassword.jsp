<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "tag" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>    
<%@ page import="java.util.ArrayList" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<%
		String status="";
		int posts=0,savedPosts=0,likes=0;
		User user = (User) session.getAttribute("user");
		if(user == null){
			%>
				<script>alert("You are logged off.");
				window.location.replace("index.jsp");
				</script>
			<% 
		}
		else{
			status = "Change Password";
		}
%>
	<h1><%= status %></h1>
	
	<h3><a href="logout">LOG OUT</a></h3>
	
	
	<a href="createPost.jsp">Create Post</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="DisplayPost">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="userPost">My Posts</a>	
	
	<h1>${user.getFname()} ${user.getLname()}</h1>
	<h1>${user.getEmail()}</h1>
	
	<form action="changePassword" method="post">
  		<label for="oldPwd">Old Password :</label>
  		<input type="text" id="oldPwd" name="oldPwd" required><br><br>
  		<label for="newPwd">New Password : </label>
  		<input type="text" id="newPwd" name="newPwd" required><br><br>
  		<input type="submit" value="Submit">
	</form>
	<input type="text" id="result" name="result" value="${result}" style="width:500px; font-size:30px;margin-top:40px;margin-left:30px;"/><br>
</body>
</html>