<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
	<div class="row-fluid">
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_1.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.country"/></h2>
                      <p><spring:message code="adminPanel.toAddCountry"/></p>
				<a href="/admin/country" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_2.png" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.band"/></h2>
                      <p><spring:message code="adminPanel.toAddBand"/></p>
				<a href="/admin/band" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_3.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.genre"/></h2>
                      <p><spring:message code="adminPanel.toAddGenre"/></p>
				<a href="/admin/genre" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>					
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_4.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.typeOfAlbum"/></h2>
                      <p><spring:message code="adminPanel.toAddTypeOfAlbum"/></p>
				<a href="/admin/typeOfAlbum" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_5.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.typeOfRecord"/></h2>
                      <p><spring:message code="adminPanel.toAddTypeOfRecord"/></p>
				<a href="/admin/typeOfRecord" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_6.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.label"/></h2>
                      <p><spring:message code="adminPanel.toAddLabel"/></p>
				<a href="/admin/label" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_7.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.album"/></h2>
                      <p><spring:message code="adminPanel.toAddAlbum"/></p>
				<a href="/admin/album" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_8.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.collection"/></h2>
                      <p><spring:message code="adminPanel.toAddCollection"/></p>
				<a href="/admin/collection" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 view view-third">
			<img src="/resources/images/admin_9.jpg" width="300" height="200">
			<div class="mask">
				<h2><spring:message code="adminPanel.news"/></h2>
                      <p><spring:message code="adminPanel.toAddNews"/></p>
				<a href="/admin/news" class="info">
					<spring:message code="adminPanel.clickToGo"/>
				</a>
			</div>
		</div>
	</div>
</security:authorize>