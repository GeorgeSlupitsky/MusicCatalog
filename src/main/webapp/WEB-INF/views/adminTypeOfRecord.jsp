<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
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
					<li class="active"><a href="/admin/typeOfRecord"><spring:message code="adminPanel.typeOfRecord"/></a></li>
					<li><a href="/admin/label"><spring:message code="adminPanel.label"/></a></li>
					<li><a href="/admin/album"><spring:message code="adminPanel.album"/></a></li>
					<li><a href="/admin/collection"><spring:message code="adminPanel.collection"/></a></li>
					<li><a href="/admin/news"><spring:message code="adminPanel.news"/></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
	<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
			<c:if test="${filter ne null}">
				<form:form action="/admin/typeOfRecord" method="get"
					modelAttribute="filter">
					<div class="form-group">
						<input class="form-control" name="name" placeholder="<spring:message code="adminTypeOfRecords.search"/>"/>
					</div>
					<div class="form-group">
						<button type="submit" class="btn button">
							<spring:message code="admin.filter.submit"/>
						</button>
					</div>
				</form:form>
			</c:if>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<form:form action="/admin/typeOfRecord/" method="post" modelAttribute="typeOfRecord">
				<form:input path="id" type="hidden"/>
				<div class="form-group">
					<form:errors path="name"/>
					<input class="form-control" name="name" placeholder="<spring:message code="adminTypeOfRecords.name"/>"/>
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
					<c:forEach items="${typeOfRecords.content}" var="typeOfRecord">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
								${typeOfRecord.name}
							</div>
							<div class="col-lg-3 col-lg-offset-2 col-md-3 col-md-offset-2 col-sm-3 col-sm-offset-2 col-xs-3 col-xs-offset-2">
								<a href="/admin/typeOfRecord/delete/${typeOfRecord.id}">
									<spring:message code="admin.delete"/>
								</a>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
								<a href="/admin/typeOfRecord/update/${typeOfRecord.id}">
									<spring:message code="admin.update"/>
								</a>
							</div>
						</div>
					</c:forEach>
					<hr>
					<div class="page_buttons">
						<c:if test="${typeOfRecords.number ne 0}">
							<a class="prev" href="/admin/typeOfRecord?page=${typeOfRecords.number}&size=${typeOfRecords.size}">
								<spring:message code="admin.prevPage"/>
							</a>
						</c:if>
						<c:if test="${typeOfRecords.number ne typeOfRecords.totalPages-1}">
							<a class="next" href="/admin/typeOfRecord?page=${typeOfRecords.number+2}&size=${typeOfRecords.size}">
								<spring:message code="admin.nextPage"/>
							</a>
						</c:if>
					</div>
					<hr>
					<div class="page_number_admin">
						<a class="one" href="/admin/typeOfRecord?page=1&size=1">1</a>
						<a class="five" href="/admin/typeOfRecord?page=1&size=5">5</a>
						<a class="ten" href="/admin/typeOfRecord?page=1&size=10">10</a>
						<a class="twenty" href="/admin/typeOfRecord?page=1&size=20">20</a>
					</div>
					<hr>
					<p><a href="/admin"><spring:message code="admin.back"/></a></p>
				</div>
			</div>	
		</div>
	</security:authorize>
</div>