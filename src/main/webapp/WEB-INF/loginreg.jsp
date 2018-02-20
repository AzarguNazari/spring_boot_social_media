<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../CSS/stylelogin.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login/Registration Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
		<h1>Sam Lobodiak's Social Network App</h1>
		<h2>Login</h2>
	    <c:if test="${logoutMessage != null}">
       		<p><c:out value="${logoutMessage}" /></p>
	    </c:if>
	    <c:if test="${errorMessage != null}">
	        <p><c:out value="${errorMessage}" /></p>
	    </c:if>
		<form method="POST" action="/login">
			<div class="form-group row">
				<label for="email" class="col-xs-3 col-form-label mr-2">Email</label>
				<div class="col-xs-9">
					<input class="form-control" type="text" id="email" name="email">
				</div>
			</div>
			<div class="form-group row">
				<label for="password" class="col-xs-3 col-form-label mr-2">Password</label>
				<div class="col-xs-9">
					<input type="password" class="form-control" id="password" name="password">
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-xs-3 col-xs-9">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</div>
		</form>
		<hr>
		<h2>Register</h2>
	    <c:if test="${regSuc != null}">
	    	<p class="success"> <c:out value="${regSuc}"/> </p>
	    </c:if>
	    <p><form:errors path="user.*"/></p>

	    <form:form method="POST" action="/registration" modelAttribute="user">
			<div class="form-group row">
				<form:label path="name" class="col-xs-3 col-form-label mr-2">Name:</form:label>
				<div class="col-xs-9">
					<form:input class="form-control" id="name" name="name" path="name"/>
				</div>
			</div>
	       	<div class="form-group row">
				<form:label path="email" class="col-xs-3 col-form-label mr-2">Email</form:label>
				<div class="col-xs-9">
					<form:input class="form-control" id="email" name="email" path="email"/>
				</div>
			</div>
			<div class="form-group row">
				<form:label path="password" class="col-xs-3 col-form-label mr-2">Password</form:label>
				<div class="col-xs-9">
					<form:input class="form-control" id="password" name="password" path="password"/>
				</div>
			</div>
			<div class="form-group row">
				<form:label path="passwordConfirmation" class="col-xs-3 col-form-label mr-2">Password Confirmation:</form:label>
				<div class="col-xs-9">
					<form:input class="form-control" id="passwordConfirmation" name="passwordConfirmation" path="passwordConfirmation"/>
				</div>
			</div>
			<div class="form-group row">
				<form:label path="state" class="col-xs-3 col-form-label mr-2">State</form:label>
				<div class="col-xs-9">
					<form:select class="form-control" id="state"  name="state" path="state">
						<option value="">Select a state...</option>
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
					</form:select>
				</div>
			</div>
			<div class="form-group row">
				<form:label path="city" class="col-xs-3 col-form-label mr-2">City</form:label>
				<div class="col-xs-9">
					<form:input class="form-control" id="city" name="city" path="city"/>
				</div>
			</div>
			<div class="form-group row">
				<form:label path="description" class="col-xs-3 col-form-label mr-2">About you</form:label>
				<div class="col-xs-9">
					<form:textarea class="form-control" id="description" name="description" path="description" rows="2" cols="40"></form:textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-xs-3 col-xs-9">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>			
					<button type="submit" class="btn btn-primary">Register</button>
				</div>
			</div>
	    </form:form>
		</div>
	</div>
	<div class="container">

	</div>
	
	
<%-- DO NOT CHANGE ANYTHING BELOW THIS LINE --%>




	<hr>
	<div class="container-fluid">
		<h1 id="header_text">Professional Network</h1>
		<div id="login">
		    <c:if test="${logoutMessage != null}">
	       		<p><c:out value="${logoutMessage}" /></p>
		    </c:if>
		    <h1>Login</h1>
		    <c:if test="${errorMessage != null}">
		        <p><c:out value="${errorMessage}" /></p>
		    </c:if>
		    <form method="POST" action="/login">
		        <p>
		            <label for="email">Email</label>
		            <input type="text" id="email" name="email"/>
		        </p>
		        <p>
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password"/>
		        </p>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input type="submit" value="Login"/>
		    </form>
		</div>
		<div id="register">
	    <h1>Register!</h1>
	    <c:if test="${regSuc != null}">
	    	<p class="success"> <c:out value="${regSuc}"/> </p>
	    </c:if>
	    <p><form:errors path="user.*"/></p>
	    
	    <form:form method="POST" action="/registration" modelAttribute="user">
	
	        <p>
	            <form:label path="name">Name:</form:label>
	            <form:input path="name"/>
	        </p>
	        <p>
	            <form:label path="email" name="user_email">Email:</form:label>
	            <form:input path="email" name="user_email"/>
	        </p>
	        <p>
	            <form:label path="password">Password:</form:label>
	            <form:password path="password"/>
	        </p>
	        <p>
	            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
	            <form:password path="passwordConfirmation"/>
	        </p>
	        <p>
	            <form:label path="state">State:</form:label>
				<form:select path="state">
					<option value="">Select a state...</option>
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
				</form:select>
	        </p>
	       	<p>
	            <form:label path="city">City: </form:label>
	            <form:input path="city"/>
	        </p>
	        <p>
	            <form:label path="description">About me:</form:label>
	            <form:textarea name="description" path="description" rows="2" cols="40"></form:textarea>
	        </p>
	        <p>
	        	<form method="post" enctype="multipart/form-data" action="/images">
	        		<input type="file" name="file"/>
	        		<input type="submit" value="Upload image">
	        	</form>
	        </p>
	        
	        <input type="submit" value="Register"/>
	    </form:form>
	    </div>
	</div>
</body>
</html>