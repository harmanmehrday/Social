<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "tag" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>    
<%@ page import="java.util.ArrayList" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Post</title>
</head>
<body>
	<%
		String status = "";
	String postContent ="";
	int id=0;
		User user = (User) session.getAttribute("user");
		if(user == null){
			%>
				<script>alert("You are logged off.");
				window.location.replace("index.jsp");
				</script>
			<% 
		}
		else{
				postContent = (String)request.getAttribute("post");
				id = (int)request.getAttribute("id");
				status = "YOU ARE LOGGED IN"; 
		}
%>

	<h1><%= status %></h1>
	<h1>${user.getFname()} ${user.getLname()}</h1>
	
	<h3><a href="logout">LOG OUT</a></h3>
	<a href="createPost.jsp">Create Post</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="userPost">My Posts</a>	
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="profile">My Profile</a>
	<h3>Edit Post</h3>
	
	<form action="updateEditPost" method="post" name="editpost">
	  		<label for="posttext">Post :</label><br>
	  		<textarea name='posttext' id='posttext' form="editpost" rows="4" cols="50"><tag:out value="<%= postContent %>" /> </textarea>	 
	  		<a href="updateEditPost?id=${id}"><input type="submit" value="Submit"></a>
	  		<br>
	</form>
	
	
	
</body>
</html>