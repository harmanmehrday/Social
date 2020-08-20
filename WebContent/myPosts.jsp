<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "tag" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>    
<%@ page import="java.util.ArrayList" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Posts</title>
<style>
table,tr,th,td{
		border : 1px solid black;
	}
td{
	padding: 10px;
}	
</style>
</head>
<body>
	<%
		String status = "",postStatus="";
		User user = (User) session.getAttribute("user");
		if(user == null){
			%>
				<script>alert("You are logged off.");
				window.location.replace("index.jsp");
				</script>
			<% 
		}
		else{
			ArrayList<Post> list = null;
			list =  (ArrayList<Post>) request.getAttribute("list");
				if(list == null || list.isEmpty()){
						postStatus = "No Posts at this time.";
				}
				else{
						postStatus = "Home Feed";
				}
				status = "YOU ARE LOGGED IN"; 
		}
%>
	<h1><%= status %></h1>
	<h1>${user.getFname()} ${user.getLname()}</h1>
	
	<h3><a href="logout">LOG OUT</a></h3>
	
	<a href="createPost.jsp">Create Post</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="DisplayPost">Home</a>
	<h2><%= postStatus %></h2>
	
	<br/><br/>
	<tag:if test="${list != null}">
		<table>
			<tag:forEach var="item" items="${list}" varStatus="loop">
			<tr>
				<td>
					${item.getDate()}
				</td>	
				<td>
				 	${item.getPostContent()}
				</td>
				<td>	
				 	${item.getLikes()}&nbsp;&nbsp;&nbsp;&nbsp; Likes
				</td>
				<td>
					<a href="likePost?page=1&index=${item.getId()}">Like</a>
				</td>
				<td>	
				 	<a href="editPost?page=1&index=${item.getId()}"> Edit</a>
				</td>	 
				<td>	
				 	<a href="deletePost?page=1&index=${item.getId()}"> Delete</a>
				</td>
			</tr>
			</tag:forEach>
		</table>				
	</tag:if>
	
	
</body>
</html>