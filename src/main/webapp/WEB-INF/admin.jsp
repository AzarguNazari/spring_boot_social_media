<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../CSS/styleAdmin.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input id="logoutButton" type="submit" value="Logout!" />
	    </form>
	    <br>
		<form action="/admin/searchByName" method="POST">
			<input id="searchText" type="text" placeholder="Search By Name" name="name" />
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<input class="button" type="submit" value="Search By Name"/>
		</form>
		<br>
		<form action="/admin/searchUserById" method="POST">
			<input id="searchId" type="number" placeholder="Search By User ID" name="id" />
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<input class="button" type="submit" value="Search By User Id"/>
		</form>
		<c:if test="${message != null}">
	    	<p class="success"> <c:out value="${message}"/> </p>
	    </c:if>
	   	<br>
		<table border="3">
	    	<thead>
	    		<tr>
					<th>User ID</th>
					<th>Name</th>
					<th>Location</th>
					<th>Number of Statuses Posted</th>
					<th>Created At</th>
					<th>Action</th>
				</tr>
	    	</thead>
	    	<c:forEach items="${all_users}" var="user">
	    		<tr>
	    			<td><c:out value="${user.id}"/></td>
	    			<td><a href="/users/${user.getId()}"><c:out value="${user.getName() }"/></a></td>
	    			<td><c:out value="${user.city}"/>, <c:out value="${user.state }"/></td>
					<td><c:out value="${user.getStatuses().size()}"/></td>
	    			
	    			<td><c:out value="${user.createdAt}"/></td>
	    			<td>
		    			<form method="post" action="/admin/deleteuser/${user.id}" class="inline">
		    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						  <button type="submit" name="submit_param" value="submit_value" class="link-button">
						    Terminate User
						  </button>
						</form>
	    			</td>
	    		</tr>
	    	</c:forEach>
	    </table>
	</div>
</body>
</html>