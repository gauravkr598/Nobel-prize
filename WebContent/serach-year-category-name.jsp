<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ include file="mynavbar.jsp" %>
    <%@page import="org.json.JSONArray"%>
    <%@ page import="java.sql.Connection" %>
    <%@ page import="db.connection.DBConnection" %>
    <%@ page import="search.api.Search" %>
<!DOCTYPE html>
<html>
<head>
<title>Search by year-category-name</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>First Name</th>
					<th>Surname</th>
					<th>Year</th>
					<th>Category</th>
					<th>Id</th>
					<th>Motivation</th>
					<th>OverallMotivation</th>
					<th>Share</th>
				</tr>
			</thead>
			<% 
			 Search searchList=new Search(DBConnection.getConnection());
			 JSONArray jsonList=searchList.getAllWinner();
			 	for(int i = 0; i < jsonList.length(); i++){
			 		JSONObject inObj = jsonList.getJSONObject(i);
			%>
			<tbody>
				<tr>
					<td></td>
					<td><%= inObj.optString("firstname") %></td>
					<td><%= inObj.optString("surname") %></td>
					<td><%= inObj.optString("year") %></td>
					<td><%= inObj.optString("category") %></td>
					<td><%= inObj.optString("id") %></td>
					<td><%= inObj.optString("motivation") %></td>
					<td><%= inObj.optString("overallMotivation") %></td>
					<td><%= inObj.optString("share") %></td>
			</tbody>
			<%
			 	}
			%>
		</table>
	</div>
<script src="javascript/jquery-3.5.1.min.js"></script>
</body>
</html>