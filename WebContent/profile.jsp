<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "tag" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>    
<%@ page import="java.util.ArrayList" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
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
			posts = (int)request.getAttribute("posts");
			savedPosts = (int)request.getAttribute("savedposts");
			likes = (int)request.getAttribute("likes");
			status = "Welcome to Social Spark"; 
		}
%>
	<h1><%= status %></h1>
	
	<h3><a href="logout">LOG OUT</a></h3>
	
	
	<a href="createPost.jsp">Create Post</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="DisplayPost">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="userPost">My Posts</a>	
	
	<h1>${user.getFname()} ${user.getLname()}</h1>
	<h1>${user.getEmail()}</h1>
	
	<h2>Total Posts : <%= posts %></h2>
	<h2>Total Saved Posts : <%= savedPosts %></h2>
	<h2>Total Likes Given : <%= likes %></h2>
	
	<a href="clearData"><button>Clear User Data</button></a>
	<br/><br/>
	<a href="deleteUser"><button>Delete Profile</button></a>
</body>
</html>