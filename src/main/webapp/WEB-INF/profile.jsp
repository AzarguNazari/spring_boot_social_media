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
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<c:choose>
				<c:when test = "${user_to_render.id == currentUser.id}">
					<h1>My Profile (<span id=""><c:out value="${user_to_render.name}" /></span>)</h1>
				</c:when>
				<c:otherwise>
					<h1><c:out value="${user_to_render.name}" />'s Wall</h1>
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
										<form:input placeholder="What's on your mind" type="text" class="form-control" id="status" name="status" path="status_body"/>
									</fieldset>
									<button type="submit" class="btn btn-primary">Submit Status</button>
								</form:form>
							</c:when>
							<c:otherwise>
								<form:form action="/status/${user_to_render.id}" method="POST" modelAttribute="status">
									<fieldset class="form-group">
										<form:input placeholder="Comment on wall" type="text" class="form-control" id="status" name="status" path="status_body"/>
									</fieldset>
									<button type="submit" class="btn btn-primary">Submit</button>
								</form:form>
							</c:otherwise>
						</c:choose>
			    	</div>
					<div id="status-list">
		    		<c:forEach items="${wall_statuses}" var="status">
						<blockquote class="blockquote">
							<p><c:out value="${status.getPoster().name}"/> said: <c:out value="${status.status_body}"/></p>
						</blockquote>
						<c:choose>
							<c:when test = "${user_to_render.id == currentUser.id}">
				    			<form method="post" action="/delete/status/${status.id }" class="inline">
				    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								  <button type="submit" name="submit_param" value="submit_value" class="link-button">
								    Delete
								  </button>
								</form>
							</c:when>
							<c:otherwise>
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
				    		<h5>Status Replies:</h5>
							<c:forEach items="${status.getRepliedStatusMessages()}" var="reply">
									<a href="/users/${reply.getUserWhoRepliedToStatus().id}"><c:out value="${reply.getUserWhoRepliedToStatus().name}"/></a>
									:  "<c:out value="${reply.statusReplyBody}"/>" - <span class="post-date"><c:out value="${reply.createdAt}"/></span>
									<c:choose>
										<c:when test = "${reply.getUserWhoRepliedToStatus().id == currentUser.id}">
							    			<form method="post" action="/status/reply/delete/${reply.id}/${user_to_render.id}" class="inline">
							    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  <button type="submit" name="submit_param" value="submit_value" class="link-button">
											    Delete Status Reply
											  </button>
											</form>
										</c:when>
										<c:otherwise>

										</c:otherwise>
									</c:choose>
									<hr>
							</c:forEach>
						</div>
						<hr>
		    		</c:forEach>
					</div>
		    	</div>
			</div>
		</div>
	</div>
	<div>
	<div id="messages">
	<h5>Messages:</h5>
			<c:choose>
				<c:when test = "${user_to_render.id != currentUser.id}">
			   		<form:form method="POST" action="/message" modelAttribute="message">
			   			<input type="hidden" name="user_to_render_id" value="${user_to_render.getId()}"/>
			   			<form:textarea type="text" columns="30" name="message" path="message_body" placeholder="Post a message"/>
			       		<input type="submit" value="Submit Message"/>
			   		</form:form>
				</c:when>
				<c:otherwise>						
				</c:otherwise>
			</c:choose>	
  		<c:forEach items="${wall_messages}" var="message">
  			<p><a href="/users/${message.getMessagePoster().id}"><c:out value="${message.getMessagePoster().name}"/></a>: "<c:out value="${message.message_body}"/>" - <span class="post-date"><c:out value="${message.createdAt}"/></span>
			<c:choose>
				<c:when test = "${message.getMessagePoster().id == currentUser.id}">
	    			<form method="post" action="/delete/message/${message.id}/${user_to_render.id}" class="inline">
	    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					  <button type="submit" name="submit_param" value="submit_value" class="link-button">
					    Delete Message
					  </button>
					</form>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>		    			

		</p>
		<div class="message-replies">
			<form:form method="POST" action="/message/reply/${message.id}/${user_to_render.id}" modelAttribute="message_reply">
		
			<h5>Message Replies:</h5>
    		<form:textarea type="text" columns="50" name="messageReply" path="messageReplyBody" placeholder="Reply to this message"/>
        		<input type="submit" value="Reply"/>
    		</form:form>
			<c:forEach items="${message.getRepliedMessageMessages()}" var="reply">
				<p>
					<a href="/users/${reply.getUserWhoRepliedToMessage().id}"><c:out value="${reply.getUserWhoRepliedToMessage().name}"/></a>
					:  "<c:out value="${reply.messageReplyBody}"/>" - <span class="post-date"><c:out value="${reply.createdAt}"/></span>
					<c:choose>
						<c:when test = "${reply.getUserWhoRepliedToMessage().id == currentUser.id}">
			    			<form method="post" action="/message/reply/delete/${reply.id}/${user_to_render.id}" class="inline">
			    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							  <button type="submit" name="submit_param" value="submit_value" class="link-button">
							    Delete Reply
							  </button>
							</form>
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
					</c:choose>
					<hr>
				</p>
			</c:forEach>
		</div>
  			<br>
  		</c:forEach>
  		</div>
	</div>

	    <!-- jQuery first, then Bootstrap JS. -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/js/bootstrap.js"></script>
</body>
</html>