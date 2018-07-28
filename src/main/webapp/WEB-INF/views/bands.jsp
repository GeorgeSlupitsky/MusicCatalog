<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<c:set var="localeCode" value="${pageContext.response.locale}" />
<div class="row-fluid">
 	<security:authorize access="isAuthenticated()">
 		<c:if test="${filter ne null}">
 			<form:form class="form-inline" action="/bands" method="get"
 				modelAttribute="filter">
 				<div class="form-group">
 					<input class="form-control" name="name" placeholder="<spring:message
								code="bands.filter.search"/>"/>
 				</div>
 				<div class="form-group">
 					<button type="submit" class="btn button">
						<spring:message code="bands.filter.submitSearch"/>
					</button>
 				</div>
 			</form:form>
 		</c:if>
 		<hr>
 		<c:forEach items="${bands.content}" var="band">
 			<nav class="bands_nav">
	 			<ul class="col-lg-2 col-md-2 col-sm-2 col-xs-2 gallery">
	 				<li class="band_content">
	 					<a onclick="hidetxt('${band.id}'); return false;"  href="#alb${band.id}" rel="nofollow">
							<img src="/images/band/${band.name}1${band.path}?version=${band.version}" class="img-rounded grey" height="150" width="150"/>
						</a>
						<img src="/images/band/${band.name}2${band.path2}?version=${band.version2}" class="img-rounded color" height="150" width="150"/>
	 					<p class="name">${band.name}</p>
	 					<c:choose>
							<c:when test="${localeCode=='en' }">
								<p>${band.country.name}</p>
							</c:when>
							<c:when test="${localeCode=='ru' }">
								<p>${band.country.nameRU}</p>
							</c:when>
							<c:when test="${localeCode=='ua' }">
								<p>${band.country.nameUA}</p>
							</c:when>
						</c:choose>
	 					<p>${band.foundationYear}</p>
	 				</li>
	 			</ul>	
 			</nav>
 		</c:forEach>	
		<c:forEach items="${bands.content}" var="band">
 			<section id="alb${band.id}">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 album_content dropdown_list button_cool" id="${band.id}">
						<hr>
						<c:forEach items="${band.albums}" var="album">
							<div class="row">
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
									<img src="/images/album/${album.band.name}/${album.name}${album.path}?version=${album.version}" class="img-circle" height="50" width="50"/>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">${album.name}</div>
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
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">${album.yearOfRelease}</div>
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">${album.albumRating}</div>
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">	
									<p><img src="/images/label/${album.label.name}${album.label.path}?version=${album.label.version}" height="30" width="30" alt="${album.label.name}"/></p>
									<p>${album.label.name}</p>
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
									<img src="/images/country/${album.country.id}${album.country.path}?version=${album.country.version}" height="30" width="40" alt="${album.country.abbreviation}"/>
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
									<p>${album.typeOfRecord.name}</p>
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
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
									<form>
										<select id="select${album.id}" class="form-control">
											<option disabled selected="selected">
												<spring:message code="bands.collection"/>
											</option>
											<c:forEach items="${user.collections}" var="collection">
												<option value="${collection.id}/${album.id}">
														${collection.name}</option>
												<script type="text/javascript">
												   document.getElementById("select${album.id}").addEventListener("change", function(){
												     document.getElementById('href${album.id}')
												     .innerHTML = '<a href="/bands/add/'+this.value+'/"><spring:message code="bands.add"/></a>';   
												   });
												</script>
											</c:forEach>
										</select>
									</form>
								</div>
								<div id="href${album.id}" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
									<a class="disabled" href="/bands/add/">
										<spring:message code="bands.add"/>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:forEach>
		<hr>
		<div class="page_buttons button_cool">
			<c:if test="${bands.number ne 0}">
				<a class="prev" href="/bands?page=${bands.number}&size=${bands.size}">
					<spring:message code="bands.prevPage"/>
				</a>
			</c:if>
			<c:if test="${bands.number ne bands.totalPages-1}">
				<a class="next" href="/bands?page=${bands.number+2}&size=${bands.size}">
					<spring:message code="bands.nextPage"/>
				</a>
			</c:if>
		</div>
	</security:authorize>
</div>