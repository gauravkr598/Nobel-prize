<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="mynavbar.jsp" %>
    <%@page import="org.json.JSONObject"%>
    <%@page import="org.json.JSONArray"%>
    <%@ page import="java.sql.Connection" %>
    <%@ page import="java.sql.*"%>
    <%@ page import="db.connection.DBConnection" %>
    <%@ page import="search.api.Search" %>
<!DOCTYPE html>
<html>
<head>
<title>Search by year and category</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<script type="text/javascript">
function search(){
	if($("#year").val() !="" || $("#category").val() != ""){
		$('#loderButton').html("<span style='color:red'>Please wait.. </span><img src='loader/loading.gif' title='Please your request in processing' />");
		$.post("SearchByYearandCategory","year="+$("#year").val()+"&category="+$("#category").val(), function(data) {
			console.log(data);
			if(data.status == "success") {
				var dataList = data.result_list;
				var str ="<table class=\"table\">"
					str+="<thead><tr><th>S.No.</th><th>First Name</th><th>Surname</th><th>Year</th><th>Category</th><th>Id</th><th>Motivation</th><th>OverallMotivation</th><th>Share</th></tr></thead><tbody>"
						for(var key in dataList) {
							str += "<tr><td>"+(parseInt(key)+1)+"</td><td>"+dataList[key].firstname+"</td><td>"+dataList[key].surname+"</td><td>"+dataList[key].year+"</td><td>"+dataList[key].category+"</td><td>"+dataList[key].id+"</td><td>"+dataList[key].motivation+"</td><td>"+dataList[key].overallMotivation+"</td><td>"+dataList[key].share+"</td>"	
						}
				str+="</tbody></table>";
				$('#loderButton').html("<button type=\"button\" onClick=\"search()\" class=\"btn btn-primary\">Search</button>");
				$('#ResponseResult').html(str);
			} else {
				$('#ResponseResult').html("<span style='color:red'>"+data.message+"</span>");
			}
		});
	}
}
</script>
<body>
	<div class="container">
		<div class="d-flex justify-content-center p-2">
		    <div class="p-5 rounded border border-primary" style="background-color: #ffffff;">
				<form  method="post" class="registrationForm"> 
					<h1>Search By Year And Category</h1>
					<div class="form-group">
				    <label>Year</label>
				    <input type="text" class="form-control" name="year" id="year" placeholder="Enter Year">
				    </div>
				    <div class="form-group">
				     <label>Category</label>
				     <br>
				    <select class="form-control" id="category" name="category">
        			<option selected disabled>---Select Category---</option>
				    <%
				    Search searchList=new Search(DBConnection.getConnection());
				    JSONArray jsonList=searchList.getAllCategory();
				    		for(int i = 0; i < jsonList.length(); i++){
				    			 
				    %>
				    			 <option value="<%= jsonList.get(i) %>"><%= jsonList.get(i) %></option> 
				    <%
				    		}
				    %>
				    </select>
				    </div>
				    <div class="form-group" id="loderButton">
				    <button type="button" onClick="search()" class="btn btn-primary">Search</button>
				    </div>
				    <div> <small id="ResponseResult" class="form-text text-muted"></small></div>
		    	</form>
		 	</div>
		</div>
	</div>
<script src="javascript/jquery-3.5.1.min.js"></script>
</body>
</html>