<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="../CSS/styleprofile.css" rel="stylesheet" type="text/css">

	<title>Professional Profile</title>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/css/bootstrap.css">
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
		<form action="/search" method="POST" class="form-inline" role="search">
			<input placeholder="Search users" type="text" class="form-control" name="name">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit" class="btn btn-secondary">Search</button>
		</form>
	    <li class="nav-item">
	      <a class="nav-link" href="/"><c:out value="${currentUser.name}" />'s Profile</a>
	    </li>
	    <li>
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input class="btn btn-secondary" type="submit" value="Logout" />
		    </form>
	    </li>
	  </ul>
	</nav>
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<c:choose>
				<c:when test = "${user_to_render.id == currentUser.id}">
					<h1>My Profile (<span id=""><c:out value="${user_to_render.name}" /></span>)</h1>
					<img src="<c:url value="http://lotr.wikia.com/wiki/Gandalf"/>"/>
				</c:when>
				<c:otherwise>
					<h1><c:out value="${user_to_render.name}" />'s Wall</h1>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test = "${user_to_render.getInvitedUserFriends().contains(currentUser)}">
					<p class="invited_text">Invited <a href="/profile/cancelinvite/${user_to_render.getId()}">Cancel Invite</a></p>
				</c:when>
				<c:when test = "${user_to_render.getFriends().contains(currentUser)}">
					<p class="invited_text">Already Friends</p>
				</c:when>
				<c:when test = "${user_to_render.getUserFriends().contains(currentUser)}">
					<p class="invited_text">Already Friends</p>
				</c:when>						         
				<c:otherwise>
					<a href="/profile/invite/${user_to_render.getId()}">Invite</a>
				</c:otherwise>
			</c:choose>
			<blockquote class="blockquote">
				<p><c:out value="${user_to_render.description}"/></p>
			</blockquote>
		   	<p>Location: <c:out value="${user_to_render.city}" /> <c:out value="${user_to_render.state}" /></p>
		    <div id="network">
				<c:choose>
					<c:when test = "${user_to_render.id == currentUser.id}">
					    <h3>Your Professional network: <c:out value="${them.size() }"/></h3>
					</c:when>
					<c:otherwise>
					    <h3><c:out value="${user_to_render.getName() }"/>'s Professional network: <c:out value="${them.size() }"/></h3>
					</c:otherwise>
				</c:choose>
			   	<div style="height:150px;width:320px;padding: 5px;border:1px solid #B2B2B2;font:16px/26px Georgia, Garamond, Serif;overflow:auto;">
				<c:forEach items="${them}" var="user">
			  		<tr>
			  			<td><a href="/users/${user.getId()}"><c:out value="${user.getName() }"/></a></td>
			  			<hr>
			  		</tr>

			   	</c:forEach>
				</div>
			</div>
			<div id="invites">
				<c:choose>
					<c:when test = "${user_to_render.id == currentUser.id}">
					    <h3>Invitations to you: <c:out value="${invited_me.size() }"/></h3>
					    <table border="3">
					    	<thead>
					    		<tr>
									<th>Name</th>
									<th>Action</th>
								</tr>
								
					    	</thead>
					    	<c:forEach items="${users}" var="user">
					    		<tr>
					    			<td><a href="/users/${user.getId()}"><c:out value="${user.getName() }"/></a></td>
					    			<td><a href="/connect/${user.getId()}">Accept Invitation</a><a href="/deleteinvite/${ user.getId() }">Ignore</a></td>
					    		</tr>
					    	</c:forEach>
					    </table>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
	
		    </div>
			<div id="the_wall">		    	
		    	<div id="status-box">
			    	<div id="whats-on-your-mind">
						<c:choose>
							<c:when test = "${user_to_render.id == currentUser.id}">
								<form:form action="/status/${user_to_render.id}" method="POST" modelAttribute="status">
									<fieldset class="form-group">
										<form:input placeholder="What's on your mind, ${user_to_render.name}?" type="text" class="form-control" id="status" name="status" path="status_body"/>
									</fieldset>
									<button type="submit" class="btn btn-primary">Submit Status</button>
								</form:form>
							</c:when>
							<c:otherwise>
								<form:form action="/status/${user_to_render.id}" method="POST" modelAttribute="status">
									<fieldset class="form-group">
										<form:input placeholder="Comment on ${user_to_render.name}'s wall..." type="text" class="form-control" id="status" name="status" path="status_body"/>
									</fieldset>
									<button type="submit" class="btn btn-primary">Submit</button>
								</form:form>
							</c:otherwise>
						</c:choose>
			    	</div>
					<div id="status-list">
		    		<c:forEach items="${wall_statuses}" var="status">
						<c:choose>
							<c:when test = "${status.getPoster().id == status.getWall_id()}">
								<blockquote class="blockquote">
									<p>
										<span id="post-text"><c:out value="${status.status_body}"/></span><span class="post-date"><c:out value="${status.createdAt}"/></span>	
									</p>
								</blockquote>
							</c:when>
							<c:otherwise>
								<blockquote class="blockquote">
									<p>
										<a href="/users/${status.getPoster().id}"><c:out value="${status.getPoster().name}"/></a>
										<span id="post-text"><c:out value="${status.status_body}"/></span><span class="post-date"><c:out value="${status.createdAt}"/></span>
										<c:choose>
											<c:when test = "${currentUser.getRoles().contains(ADMIN_ROLE_OBJECT)}">
								    			<form method="post" action="/delete/status/${status.id }/${user_to_render.id}" class="inline">
								    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												  <button type="submit" name="submit_param" value="submit_value" class="link-button">
												    ADMIN DELETE
												  </button>
												</form>
											</c:when>
											<c:when test = "${status.getPoster().id == currentUser.id}">
								    			<form method="post" action="/delete/status/${status.id}/${user_to_render.id}" class="inline">
								    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												  <button type="submit" name="submit_param" value="submit_value" class="link-button">
												    Delete
												  </button>
												</form>
											</c:when>
											<c:when test = "${user_to_render.id == currentUser.id}">
								    			<form method="post" action="/delete/status/${status.id }/${user_to_render.id}" class="inline">
								    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												  <button type="submit" name="submit_param" value="submit_value" class="link-button">
												    Delete
												  </button>
												</form>
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>	
									</p>
								</blockquote>
							</c:otherwise>
						</c:choose>


						<form:form method="POST" action="/status/reply/${status.id}/${user_to_render.id}" modelAttribute="statusReply">
					       	<div class="form-group row">
								<div class="col-xs-9">
									<form:input class="form-control" id="statusReplyBody" name="status-reply" path="statusReplyBody" placeholder="Comment on this status "/>
								</div>
							</div>
							<div class="form-group row">
								<div class="offset-xs-3 col-xs-9">
							        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>			
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</div>
						</form:form>
						<div class="status-reply">
							<c:forEach items="${status.getRepliedStatusMessages()}" var="reply">
									<a href="/users/${reply.getUserWhoRepliedToStatus().id}"><c:out value="${reply.getUserWhoRepliedToStatus().name}"/></a>
									:  <span id="reply-text"><c:out value="${reply.statusReplyBody}"/></span> - <span class="post-date"><c:out value="${reply.createdAt}"/></span>
									<c:choose>
										<c:when test = "${currentUser.getRoles().contains(ADMIN_ROLE_OBJECT)}">
							    			<form method="post" action="/status/reply/delete/${reply.id}/${user_to_render.id}" class="inline">
							    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  <button type="submit" name="submit_param" value="submit_value" class="link-button">
											    ADMIN DELETE
											  </button>
											</form>
										</c:when>
										<c:when test = "${user_to_render.id == currentUser.id}">
							    			<form method="post" action="/status/reply/delete/${reply.id}/${user_to_render.id}" class="inline">
							    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  <button type="submit" name="submit_param" value="submit_value" class="link-button">
											    Delete reply 
											  </button>
											</form>
										</c:when>
										<c:when test = "${reply.getUserWhoRepliedToStatus().id == currentUser.id}">
							    			<form method="post" action="/status/reply/delete/${reply.id}/${user_to_render.id}" class="inline">
							    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  <button type="submit" name="submit_param" value="submit_value" class="link-button">
											    Delete
											  </button>
											</form>
										</c:when>
										<c:otherwise>

										</c:otherwise>
									</c:choose>
									<hr>
							</c:forEach>
						</div>
		    		</c:forEach>
					</div>
		    	</div>
			</div>
		</div>
	</div>
	<div>

	</div>

	    <!-- jQuery first, then Bootstrap JS. -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/js/bootstrap.js"></script>
</body>
</html>