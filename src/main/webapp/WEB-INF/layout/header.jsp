<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>

<nav class="navbar" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand logo" href="/"><img src="/resources/images/logo.png" height="52" width="170"></a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
	        		<li>
		        		<div class="button_cool">
		        			<a href="/login">
		        				<spring:message code="header.authorization"/>
		        			</a>
		        		</div>
	        		</li>
	        	</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown user_buttons">
		                <a href="#" class="dropdown-toggle user_dropmenu" data-toggle="dropdown">
		                    <span><img src="/images/myUser/${authUser.id}${authUser.path}?version=${authUser.version}" class="img-circle" height="50" width="50"></span>
		                    <strong>${authUser.login}</strong>
		                    <span class="glyphicon glyphicon-chevron-down"></span>
		                </a>
		                <ul class="dropdown-menu">	
		                    <li>
								<div class="navbar-login">
									<p class="text-center">
										<span><img src="/images/myUser/${authUser.id}${authUser.path}?version=${authUser.version}" class="img-circle" height="100" width="100"></span>
									</p>
									<p class="text-center"><strong>${authUser.name} ${authUser.surname}</strong></p>
									<p class="text-center small">${authUser.email}</p>
								</div>
							</li>
							<li class="divider"></li>
							<li><a href="/userPanel/${authUser.id}"><spring:message code="header.userMenu.userPanel"/> <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
							<li class="divider"></li>
							<li><a href="/myCollection/"><spring:message code="header.userMenu.myCollections"/> <span class="fa fa-music pull-right"></span></a></li>
							<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
								<li class="divider"></li>
								<li><a href="/admin"><spring:message code="header.userMenu.adminPanel"/> <span class="fa fa-unlock pull-right"></span></a></li>
							</security:authorize>
							<li class="divider"></li>
							<li>
								<div class="row">
									<div class="col-lg-6 col-lg-offset-3">
										<form:form action="/logout" method="post">
											<spring:message code="header.userMenu.logout" var="headerUserMenuLogout"></spring:message>
											<input class="btn btn-default" type="submit" value="${headerUserMenuLogout}">
										</form:form>
									</div>
								</div>
							</li>
		                </ul>
					</li>
				</security:authorize>
	        </ul>
		</div>
	</div>
</nav>
