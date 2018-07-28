<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<form:form action="/login" method="post">
	<div class="row">
		<div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-6 col-xs-offset-2 login_form">
			<br>
			<br>
			<br>
			<br>
			<div class="form-group">
				<c:if test="${param.fail eq true}">
					<spring:message code="login.failToAuthorize"/>
				</c:if>	
			</div>
			<div class="form-group input-group input-group-sm">
				<span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
				<input class="form-control" name="username" placeholder="<spring:message
								code="login.username"/>">
			</div>
			<div class="form-group input-group input-group-sm">
				<span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
				<input class="form-control" name="password" type="password" placeholder="<spring:message
								code="login.password"/>">
			</div>
			<div class="form-group">
				<spring:message code="login.submit" var="loginSubmit"></spring:message>
				<input class="btn button" type="submit" value="${loginSubmit}">
			</div>
			<div class="form-group button_cool">
				<a href="/registration"><spring:message code="login.registration"/></a>
			</div>
		</div>
	</div>
</form:form>
