<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "tag" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>    
<%@ page import="java.util.ArrayList" %>    

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
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
	
	<a href="createPost.jsp">Create Post</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="userPost">My Posts</a>	
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="profile">My Profile</a>
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
					${item.getName()}
				</td>	
				<td>
				 	${item.getPostContent()}
				</td>
				<td>	
				 	${item.getLikes()}&nbsp;&nbsp;&nbsp;&nbsp; Likes
				</td>
				<td>
					<a href="likePost?page=2&index=${item.getId()}">Like</a>
				</td>
				<td>	
				 	<a href="savePost?index=${item.getId()}"> Save</a>
				</td>	 
			</tr>
			</tag:forEach>
		</table>				
	</tag:if>

</body>
</html>