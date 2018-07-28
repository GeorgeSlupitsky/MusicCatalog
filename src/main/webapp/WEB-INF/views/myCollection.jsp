<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<c:set var="localeCode" value="${pageContext.response.locale}" />
<div class="row button_cool">
	<security:authorize access="isAuthenticated()">
		<form:form class="form-inline" action="/myCollection/" method="post" modelAttribute="collection">
			<div class="form-group">
				<input class="form-control" name="name" placeholder="<spring:message
								code="myCollection.newCollection"/>" />
			</div>
			<div class="form-group">
				<spring:message code="myCollection.create" var="myCollectionСreate"></spring:message>
				<input class="btn button" type="submit" value="${myCollectionСreate}">
			</div>
			<div class="form-group">
				<form:errors path="name"/>
			</div>
		</form:form>
		<c:forEach items="${user.collections}" var="collection"> 
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collection_content">
				<h3>${collection.name}</h3>
				<a href="/myCollection/delete/${collection.id}">
					<spring:message code="myCollection.deleteCollection"/>
				</a>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 album_collection_content">
					<br>
					<c:forEach items="${collection.albums}" var="album">
						<div class="row">
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
								<img src="/images/album/${album.band.name}/${album.name}${album.path}?version=${album.version}" class="img-circle" height="40" width="40"/>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">${album.band.name}</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">${album.name}</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">${album.yearOfRelease}</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
								<c:choose>
									<c:when test="${localeCode=='en' }">
										<p>${album.genre.name}</p>
									</c:when>
									<c:when test="${localeCode=='ru' }">
										<p>${album.genre.nameRU}</p>
									</c:when>
									<c:when test="${localeCode=='ua' }">
										<p>${album.genre.nameUA}</p>
									</c:when>
								</c:choose>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">${album.label.name}</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
								<img src="/images/country/${album.country.id}${album.country.path}?version=${album.country.version}" height="30" width="40"/>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">${album.typeOfRecord.name}</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
								<c:choose>
									<c:when test="${localeCode=='en' }">
										<p>${album.typeOfAlbum.name}</p>
									</c:when>
									<c:when test="${localeCode=='ru' }">
										<p>${album.typeOfAlbum.nameRU}</p>
									</c:when>
									<c:when test="${localeCode=='ua' }">
										<p>${album.typeOfAlbum.nameUA}</p>
									</c:when>
								</c:choose>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
								<a href="/myCollection/delete/${album.id}/${collection.id}">
									<spring:message code="myCollection.deleteFromCollection"/>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:forEach>	
	</security:authorize>
</div>