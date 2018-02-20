<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Search</title>
</head>
<body>
	<div id="container">
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input id="logoutButton" type="submit" value="Logout!" />
	    </form>
	    <h1>Users returned</h1>
	    <h2>List of people table</h2>
		<table border="3">
			<thead>
				<tr>					
					<th>State</th>
					<th>Name</th>
					<th>City</th>
					<th>About</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td><c:out value="${user.state}"/> <c:out value="${single_user.id}"/></td>
						<td><a href="/users/${user.id}"><c:out value="${user.name}"/></a></td>
						<td><c:out value="${user.city}"/></td>
						<td><c:out value="${user.description}"/></td>
						
					</tr>	
				</c:forEach>							
			</tbody>
			</table>
			<hr>
			<h2>Find One By ID Table (can consolidate these two later with Choose When)</h2>
			
			<table border="5">
				<thead>

					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Location</th>
						<th>About</th>
	
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${single_user.id}"/></td>
						<td><a href="/users/${single_user.id}"><c:out value="${single_user.name}"/></a></td>
						<td><c:out value="${single_user.city}"/>, <c:out value="${single_user.state}"/></td>
						<td><c:out value="${single_user.description}"/></td>
					</tr>							
				</tbody>
			</table>
	</div>
</body>
</html>