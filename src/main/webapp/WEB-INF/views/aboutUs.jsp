<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>

<div class="row-fluid">
	<div class="about_us_content">
		<p><img src="/resources/images/about_us_g.jpg" class="img-circle"></p>
		<h3><spring:message code="aboutUs.h3"/></h3>
		<h4><spring:message code="aboutUs.h4"/></h4>
	</div>
</div>