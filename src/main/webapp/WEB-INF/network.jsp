<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../CSS/stylenetwork.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users you may want to connect with</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <!-- Brand -->
	  <a class="navbar-brand" href="#">Sam Lobodiak's Social Network App</a>
	
	  <!-- Links -->
	  <ul class="navbar-nav">
	
	    <li class="nav-item">
	      <a class="nav-link" href="/users">Add Users</a>
	    </li>
	    <li class="search-bar">
			<form action="/search" method="POST" id="search-users-form">
				<input id="searchText" type="text" placeholder="Search By Name" name="name" />
				<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<input class="button" type="submit" value="Search By Name"/>
			</form>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="/">My Profile</a>
	    </li>
	    <li>
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input id="logoutButton" class="button" type="submit" value="Logout!" />
		    </form>
	    </li>
	  </ul>
	</nav>
	<nav class="navbar navbar-expand-sm navbar-light bg-faded">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav-content" aria-controls="nav-content" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
		</button>
		<!-- Search -->
		<form action="/search" method="POST" class="form-inline" role="search">
			<input placeholder="Search by name" type="text" class="form-control" name="name">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit" class="btn btn-secondary">Search By Name</button>
		</form>
		<!-- Search -->
		<form action="/searchByCity" method="POST" class="form-inline" role="search">
			<input placeholder="Search by city" type="text" class="form-control" name="city">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit" class="btn btn-secondary">Search By City</button>
		</form>
		<!-- Search -->
			<c:if test="${message != null}">
		    	<p class="success"> <c:out value="${message}"/> </p>
		    </c:if>
		<form action="/searchByState" method="POST" class="form-inline" role="search">
			<select name="state">
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
					<option value="CO">Colorado</option>
					<option value="CT">Connecticut</option>
					<option value="DE">Delaware</option>
					<option value="DC">District Of Columbia</option>
					<option value="FL">Florida</option>
					<option value="GA">Georgia</option>
					<option value="HI">Hawaii</option>
					<option value="ID">Idaho</option>
					<option value="IL">Illinois</option>
					<option value="IN">Indiana</option>
					<option value="IA">Iowa</option>
					<option value="KS">Kansas</option>
					<option value="KY">Kentucky</option>
					<option value="LA">Louisiana</option>
					<option value="ME">Maine</option>
					<option value="MD">Maryland</option>
					<option value="MA">Massachusetts</option>
					<option value="MI">Michigan</option>
					<option value="MN">Minnesota</option>
					<option value="MS">Mississippi</option>
					<option value="MO">Missouri</option>
					<option value="MT">Montana</option>
					<option value="NE">Nebraska</option>
					<option value="NV">Nevada</option>
					<option value="NH">New Hampshire</option>
					<option value="NJ">New Jersey</option>
					<option value="NM">New Mexico</option>
					<option value="NY">New York</option>
					<option value="NC">North Carolina</option>
					<option value="ND">North Dakota</option>
					<option value="OH">Ohio</option>
					<option value="OK">Oklahoma</option>
					<option value="OR">Oregon</option>
					<option value="PA">Pennsylvania</option>
					<option value="RI">Rhode Island</option>
					<option value="SC">South Carolina</option>
					<option value="SD">South Dakota</option>
					<option value="TN">Tennessee</option>
					<option value="TX">Texas</option>
					<option value="UT">Utah</option>
					<option value="VT">Vermont</option>
					<option value="VA">Virginia</option>
					<option value="WA">Washington</option>
					<option value="WV">West Virginia</option>
					<option value="WI">Wisconsin</option>
					<option value="WY">Wyoming</option>
				</select>
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit" class="btn btn-secondary">Search By State</button>
		</form>
		<c:if test="${message != null}">
	    	<p class="success"> <c:out value="${message}"/> </p>
	    </c:if>
	</nav>
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1>Users you may want to connect with</h1>
		  <table class="table table-dark table-striped">
		    <thead>
		      <tr>
				<th>State</th>
				<th>Name</th>
				<th>City</th>
				<th>About</th>
				<th>Action</th>
		      </tr>
		    </thead>
		    <tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td><c:out value="${user.state}"/></td>
						<td><a href="/users/${user.id}"><c:out value="${user.name}"/></a></td>
						<td><c:out value="${user.city}"/></td>
						<td><c:out value="${user.description}"/></td>
			   			<td>
					      <c:choose>
					         <c:when test = "${user.getInvitedUserFriends().contains(currentUser)}">
			 						<p class="invited_text">Invited</p>
			 						<a href="/cancelinvite/${user.getId()}">Cancel Invite</a>
					         </c:when>
					         <c:when test = "${user.getFriends().contains(currentUser)}">
			 						<p class="invited_text">Already Friends</p>
					         </c:when>
					         <c:when test = "${user.getUserFriends().contains(currentUser)}">
			 						<p class="invited_text">Already Friends</p>
					         </c:when>						         
					         <c:otherwise>
			 						<a href="/invite/${user.getId()}">Invite</a>
					         </c:otherwise>
					      </c:choose>
			   			</td>
			
					</tr>	
				</c:forEach>	
		    </tbody>
		  </table>
		</div>
	</div>

<%-- DON'T TOUCH ANYTHING BELOW THIS LINE --%>
<hr>
	<div id="container">
		<a href="/">My Profile</a>
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input id="logoutButton" type="submit" value="Logout!" />
	    </form>

		<h1>Users you may want to connect with</h1>
		<br>
		<div id="search-forms-div">
			<form action="/search" method="POST" class="search-forms">
				<input id="searchText" type="text" placeholder="Search By Name" name="name" />
				<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<input class="button" type="submit" value="Search By Name"/>
			</form>
			<br>
			<c:if test="${message != null}">
		    	<p class="success"> <c:out value="${message}"/> </p>
		    </c:if>
		   	<form action="/searchByState" method="POST" class="search-forms">
		   	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		   	
				<select name="state">
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
					<option value="CO">Colorado</option>
					<option value="CT">Connecticut</option>
					<option value="DE">Delaware</option>
					<option value="DC">District Of Columbia</option>
					<option value="FL">Florida</option>
					<option value="GA">Georgia</option>
					<option value="HI">Hawaii</option>
					<option value="ID">Idaho</option>
					<option value="IL">Illinois</option>
					<option value="IN">Indiana</option>
					<option value="IA">Iowa</option>
					<option value="KS">Kansas</option>
					<option value="KY">Kentucky</option>
					<option value="LA">Louisiana</option>
					<option value="ME">Maine</option>
					<option value="MD">Maryland</option>
					<option value="MA">Massachusetts</option>
					<option value="MI">Michigan</option>
					<option value="MN">Minnesota</option>
					<option value="MS">Mississippi</option>
					<option value="MO">Missouri</option>
					<option value="MT">Montana</option>
					<option value="NE">Nebraska</option>
					<option value="NV">Nevada</option>
					<option value="NH">New Hampshire</option>
					<option value="NJ">New Jersey</option>
					<option value="NM">New Mexico</option>
					<option value="NY">New York</option>
					<option value="NC">North Carolina</option>
					<option value="ND">North Dakota</option>
					<option value="OH">Ohio</option>
					<option value="OK">Oklahoma</option>
					<option value="OR">Oregon</option>
					<option value="PA">Pennsylvania</option>
					<option value="RI">Rhode Island</option>
					<option value="SC">South Carolina</option>
					<option value="SD">South Dakota</option>
					<option value="TN">Tennessee</option>
					<option value="TX">Texas</option>
					<option value="UT">Utah</option>
					<option value="VT">Vermont</option>
					<option value="VA">Virginia</option>
					<option value="WA">Washington</option>
					<option value="WV">West Virginia</option>
					<option value="WI">Wisconsin</option>
					<option value="WY">Wyoming</option>
				</select>
				<input class="button" type="submit" value="Search By State"/>			
			</form>
		   	<br>
			<form action="/searchByCity" method="POST" class="search-forms">
				<input id="searchByCity" type="text" placeholder="Search By City" name="city" />
				<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<input class="button" type="submit" value="Search By City"/>
			</form>
		</div>
		<div class="tables">
			<table border="3">
		    	<thead>
		    		<tr>
						<th>Name</th>
						<th>Location</th>
						<th>Action</th>
					</tr>
		    	</thead>
		    	<c:forEach items="${users}" var="user">
		    		<tr>
		    			<td><a href="/users/${user.getId()}"><c:out value="${user.getName() }"/></a></td>
		    			<td><c:out value="${user.city}"/>, <c:out value="${user.state }"/></td>
		    			<td>
					      <c:choose>
					         <c:when test = "${user.getInvitedUserFriends().contains(currentUser)}">
		  						<p class="invited_text">Invited</p>
		  						<a href="/cancelinvite/${user.getId()}">Cancel Invite</a>
					         </c:when>
					         <c:otherwise>
		  						<a href="/invite/${user.getId()}">Invite</a>
					         </c:otherwise>
					      </c:choose>
		    			</td>
		    		</tr>
		    	</c:forEach>
		    </table>
		</div>
	    <div class=tables id="pending_invitations">
			<table border="3" class="tables">
		    	<thead>
		    		<tr>
						<th>Pending Invitations</th>
	
					</tr>
		    	</thead>
		    	<c:forEach items="${i_invited}" var="user">
		    		<tr>
		    			<td><a href="/users/${user.getId()}"><c:out value="${user.getName() }"/></a></td>
		    		</tr>
		    	</c:forEach>
		    </table>
	    </div>

	</div>
</body>
</html>