<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<div class="row-fluid">
	<nav class="navbar">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav button_cool">
					<li><a href="/admin/country"><spring:message code="adminPanel.country"/></a></li>
					<li><a href="/admin/band"><spring:message code="adminPanel.band"/></a></li>
					<li><a href="/admin/genre"><spring:message code="adminPanel.genre"/></a></li>
					<li><a href="/admin/typeOfAlbum"><spring:message code="adminPanel.typeOfAlbum"/></a></li>
					<li><a href="/admin/typeOfRecord"><spring:message code="adminPanel.typeOfRecord"/></a></li>
					<li><a href="/admin/label"><spring:message code="adminPanel.label"/></a></li>
					<li><a href="/admin/album"><spring:message code="adminPanel.album"/></a></li>
					<li class="active"><a href="/admin/collection"><spring:message code="adminPanel.collection"/></a></li>
					<li><a href="/admin/news"><spring:message code="adminPanel.news"/></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
	<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
		<div class="col-lg-4 col-lg-offset-3 col-md-4 col-md-offset-3 col-sm-4 col-sm-offset-3 col-xs-4 col-xs-offset-3">
			<form:form action="/admin/collection/" method="post" modelAttribute="collection">
				<form:input path="id" type="hidden" />
				<div class="form-group">
					<form:errors path="name"/>
					<input class="form-control" name="name" placeholder="<spring:message code="adminCollection.name"/>" />
				</div>
				<div class="form-group">
					<spring:message code="admin.submit" var="adminSubmit"></spring:message>
					<input class="btn button" type="submit" value="${adminSubmit}">
				</div>
			</form:form>
		</div>
		<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 button_cool">
					<c:forEach items="${collections.content}" var="collection">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
								${collection.name}
							</div>
							<div class="col-lg-3 col-lg-offset-1 col-md-3 col-md-offset-1 col-sm-3 col-sm-offset-1 col-xs-3 col-xs-offset-1">
								<a href="/admin/collection/delete/${collection.id}">
									<spring:message code="admin.delete"/>
								</a>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
								<a href="/admin/collection/update/${collection.id}">
									<spring:message code="admin.update"/>
								</a>
							</div>
						</div>
					</c:forEach>
					<hr>
					<div class="page_buttons">
						<c:if test="${collections.number ne 0}">
							<a class="prev" href="/admin/collection?page=${collections.number}&size=${collections.size}">
								<spring:message code="admin.prevPage"/>
							</a>
						</c:if>
						<c:if test="${collections.number ne collections.totalPages-1}">
							<a class="next" href="/admin/collection?page=${collections.number+2}&size=${collections.size}">
								<spring:message code="admin.nextPage"/>
							</a>
						</c:if>
					</div>
					<hr>
					<div class="page_number_admin">
						<a class="one" href="/admin/collection?page=1&size=1">1</a>
						<a class="five" href="/admin/collection?page=1&size=5">5</a>
						<a class="ten" href="/admin/collection?page=1&size=10">10</a>
						<a class="twenty" href="/admin/collection?page=1&size=20">20</a>
					</div>
					<hr>
					<p><a href="/admin"><spring:message code="admin.back"/></a></p>
				</div>
			</div>	
		</div>
	</security:authorize>
</div>