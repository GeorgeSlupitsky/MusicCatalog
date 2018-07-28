<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<c:set var="localeCode" value="${pageContext.response.locale}" />
<security:authorize access="isAuthenticated()">	
	<form:form action="/userPanel" method="post" modelAttribute="user" enctype="multipart/form-data">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
				<img src="/images/myUser/${user.id}${user.path}?version=${user.version}" class="img-circle" height="110" width="110">
				<div class="button_cool">
					<p><a href="/myCollection"><spring:message
								code="userPanel.myCollection"/></a></p>
				</div>
			</div> 
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 update_form">
				<div class="input-group input-group-sm">
					<form:errors path="name"/>
				</div>
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-hand-o-right" aria-hidden="true"></i></span>
					<form:input class="form-control" path="name"/>
				</div>
				<div class="input-group input-group-sm">
					<form:errors path="surname"/>
				</div>
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-hand-o-right" aria-hidden="true"></i></span>
					<form:input class="form-control" path="surname"/>
				</div>
				<div class="input-group input-group-sm">
					<form:errors path="login"/>
				</div>
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
					<form:input class="form-control" path="login" />
				</div>
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
					<form:input class="form-control" path="password" type="password"/>
				</div>
				<div class="input-group input-group-sm">
					<form:errors path="email"/>
				</div>
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
					<form:input class="form-control" path="email"/>
				</div>
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i></span>
					<form:select class="form-control" path="country">
						<c:choose>
							<c:when test="${localeCode=='en' }">
								<c:forEach items="${countries}" var="country">
									<c:choose>
										<c:when test="${user.country.id eq country.id}">
											<option value="${country.id}" selected="selected"> ${country.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${country.id}">${country.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when test="${localeCode=='ru' }">
								<c:forEach items="${countries}" var="country">
									<c:choose>
										<c:when test="${user.country.id eq country.id}">
											<option value="${country.id}" selected="selected"> ${country.nameRU}</option>
										</c:when>
										<c:otherwise>
											<option value="${country.id}">${country.nameRU}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when test="${localeCode=='ua' }">
								<c:forEach items="${countries}" var="country">
									<c:choose>
										<c:when test="${user.country.id eq country.id}">
											<option value="${country.id}" selected="selected"> ${country.nameUA}</option>
										</c:when>
										<c:otherwise>
											<option value="${country.id}">${country.nameUA}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
					</form:select>
				</div>
				<div class="form-group btn_lab">
					<label for="files" class="btn_label"><spring:message code="select.image"/></label>
  					<input id="files" style="visibility:hidden;" type="file" name="file">
				</div>
				<div class="form-group">
					<spring:message code="userPanel.submit" var="userPanelSubmit"></spring:message>
					<input class="btn button" type="submit" value="${userPanelSubmit}">
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
		</div>
	</form:form>
</security:authorize>