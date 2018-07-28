<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<c:set var="localeCode" value="${pageContext.response.locale}" />
<div class="row-fluid">
	<ul class="skdslider">
		<c:forEach items="${newses.content}" var="news">
			<li>
				<img src="/images/news/${news.id}${news.path}?version=${news.version}">
				<div>
					<c:choose>
						<c:when test="${localeCode=='en' }">
							<h1>${news.name}</h1>
						</c:when>
						<c:when test="${localeCode=='ru' }">
							<h1>${news.nameRU}</h1>
						</c:when>
						<c:when test="${localeCode=='ua' }">
							<h1>${news.nameUA}</h1>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${localeCode=='en' }">
							<p>${news.content}</p>
						</c:when>
						<c:when test="${localeCode=='ru' }">
							<p>${news.contentRU}</p>
						</c:when>
						<c:when test="${localeCode=='ua' }">
							<p>${news.contentUA}</p>
						</c:when>
					</c:choose>
				</div>	
				<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
					<a class="delete" href="/news/delete/${news.id}"><i class="fa fa-trash-o next"></i></a>
					<a class="update" href="/admin/news/update/${news.id}"><i class="fa fa-pencil update"></i></a>
					<a class="add" href="/admin/news"><i class="fa fa-plus add"></i></a>
				</security:authorize>
			</li>
		</c:forEach>
	</ul>
</div>

	
