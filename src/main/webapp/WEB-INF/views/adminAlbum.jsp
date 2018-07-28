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
					<li class="active"><a href="/admin/album"><spring:message code="adminPanel.album"/></a></li>
					<li><a href="/admin/collection"><spring:message code="adminPanel.collection"/></a></li>
					<li><a href="/admin/news"><spring:message code="adminPanel.news"/></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class=row-fluid>
	<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
			<c:if test="${filter ne null}">
				<form:form action="/admin/album" method="get" modelAttribute="filter">
					<div class="form-group">
						<h4><spring:message code="adminAlbum.yearOfRelease"/></h4>
					</div>
					<div class="form-group">
						<input name="minYearOfRelease" placeholder="<spring:message code="adminAlbum.minYear"/>" class="form-control"/>
						<br>
						<input name="maxYearOfRelease" placeholder="<spring:message code="adminAlbum.maxYear"/>" class="form-control"/>			
					</div>
					<div class="form-group">
						<h4><spring:message code="adminAlbum.rating"/></h4>
					</div>
					<div class="form-group">
						<input name="minAlbumRating" placeholder="<spring:message code="adminAlbum.minRating"/>" class="form-control"/>
						<br>
						<input name="maxAlbumRating" placeholder="<spring:message code="adminAlbum.maxRating"/>" class="form-control"/>			
					</div>
					<div class="form-group box_down">
						<h4><a onclick="hidetxt('div1'); return false;" href="#" rel="nofollow" class="menu_link" style="text-decoration: none;">
						<spring:message code="adminAlbum.genre"/><span class="glyphicon glyphicon-chevron-down"></span></a></h4>
					</div>
					<div class="form-group dropdown_list" id="div1">
						<c:forEach items="${genres}" var="genre">
							<div class="checkbox">
								<label><form:checkbox path="genreId" value="${genre.id}"/> ${genre.name}</label>
							</div>
						</c:forEach>
					</div>
					<div class="form-group box_down">
						<h4><a onclick="hidetxt('div2'); return false;" href="#" rel="nofollow" class="menu_link" style="text-decoration: none;">
						<spring:message code="adminAlbum.typeOfAlbum"/><span class="glyphicon glyphicon-chevron-down"></span></a></h4>
					</div>
					<div class="form-group dropdown_list" id="div2">
						<c:forEach items="${typeOfAlbums}" var="typeOfAlbum">
							<div class="checkbox">
								<label><form:checkbox path="typeOfAlbumId" value="${typeOfAlbum.id}"/> ${typeOfAlbum.name}</label>
							</div>
						</c:forEach>
					</div>
					<div class="form-group box_down">
						<h4><a onclick="hidetxt('div3'); return false;" href="#" rel="nofollow" class="menu_link" style="text-decoration: none;">
						<spring:message code="adminAlbum.typeOfRecord"/><span class="glyphicon glyphicon-chevron-down"></span></a></h4>
					</div>
					<div class="form-group dropdown_list" id="div3">
						<c:forEach items="${typeOfRecords}" var="typeOfRecord">
							<div class="checkbox">
								<label><form:checkbox path="typeOfRecordId" value="${typeOfRecord.id}"/> ${typeOfRecord.name}</label>
							</div>
						</c:forEach>
					</div>
					<div class="form-group box_down">
						<h4><a onclick="hidetxt('div4'); return false;" href="#" rel="nofollow" class="menu_link" style="text-decoration: none;">
						<spring:message code="adminAlbum.country"/><span class="glyphicon glyphicon-chevron-down"></span></a></h4>
					</div>
					<div class="form-group dropdown_list" id="div4">
						<c:forEach items="${countries}" var="country">
							<div class="checkbox">
								<label><form:checkbox path="countryId" value="${country.id}"/> ${country.name}</label>
							</div>
						</c:forEach>
					</div>
					<div class="form-group box_down">
						<h4><a onclick="hidetxt('div5'); return false;" href="#" rel="nofollow" class="menu_link" style="text-decoration: none;">
						<spring:message code="adminAlbum.label"/><span class="glyphicon glyphicon-chevron-down"></span></a></h4>
					</div>
					<div class="form-group dropdown_list" id="div5">
						<c:forEach items="${labels}" var="label">
							<div class="checkbox">
								<label><form:checkbox path="labelId" value="${label.id}"/> ${label.name}</label>
							</div>
						</c:forEach>
					</div>
					<div class="form-group box_down">
						<h4><a onclick="hidetxt('div6'); return false;" href="#" rel="nofollow" class="menu_link" style="text-decoration: none;">
						<spring:message code="adminAlbum.band"/><span class="glyphicon glyphicon-chevron-down"></span></a></h4>
					</div>
					<div class="form-group dropdown_list" id="div6">
						<c:forEach items="${bands}" var="band">
							<div class="checkbox">
								<label><form:checkbox path="bandId" value="${band.id}"/> ${band.name}</label>
							</div>
						</c:forEach>
					</div>
					<div class="form-group">
						<button type="submit" class="btn button"><spring:message code="admin.filter.submit"/></button>
					</div>
				</form:form>
			</c:if>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<form:form action="/admin/album/" method="post" modelAttribute="album" enctype="multipart/form-data">
				<form:input path="id" type="hidden" />
				<form:hidden path="version"/>
				<form:hidden path="path"/>
				<div class="form-group">
					<form:select class="form-control" path="genre">
						<c:forEach items="${genres}" var="genre">
							<c:choose>
								<c:when test="${album.genre.id eq genre.id}">
									<option value="${genre.id}" selected="selected">
										${genre.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${genre.id}">${genre.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="typeOfAlbum">
						<c:forEach items="${typeOfAlbums}" var="typeOfAlbum">
							<c:choose>
								<c:when test="${album.typeOfAlbum.id eq typeOfAlbum.id}">
									<option value="${typeOfAlbum.id}" selected="selected">
										${typeOfAlbum.name}</option>
									</c:when>
								<c:otherwise>
									<option value="${typeOfAlbum.id}">${typeOfAlbum.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="typeOfRecord">
						<c:forEach items="${typeOfRecords}" var="typeOfRecord">
							<c:choose>
								<c:when test="${album.typeOfRecord.id eq typeOfRecord.id}">
									<option value="${typeOfRecord.id}" selected="selected">
										${typeOfRecord.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${typeOfRecord.id}">${typeOfRecord.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="country">
						<c:forEach items="${countries}" var="country">
							<c:choose>
								<c:when test="${album.country.id eq country.id}">
									<option value="${country.id}" selected="selected">
										${country.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${country.id}">${country.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="label">
						<c:forEach items="${labels}" var="label">
							<c:choose>
								<c:when test="${album.label.id eq label.id}">
									<option value="${label.id}" selected="selected">
										${label.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${label.id}">${label.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="band">
						<c:forEach items="${bands}" var="band">
							<c:choose>
								<c:when test="${album.band.id eq band.id}">
									<option value="${band.id}" selected="selected">
										${band.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${band.id}">${band.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="yearOfRelease" id="year" name="year">
						<script>
						var myDate = new Date();
						var year = myDate.getFullYear();
						for(var i = 1900; i < year+1; i++){
						 document.write('<option value="'+i+'">'+i+'</option>');
						}
						</script>
					</form:select>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="albumRating">
						<script>
						for(var i = 10; i > 0; i--){
						 document.write('<option value="'+i+'">'+i+'</option>');
						}
						</script>
					</form:select>
				</div>
				<div class="form-group">
					<form:errors path="name"/>
					<input class="form-control" name="name" placeholder="<spring:message code="adminAlbum.name"/>" />
				</div>
				<div class="form-group btn_lab">
					<label for="files" class="btn_label"><spring:message code="select.image"/></label>
  					<input id="files" style="width:20px; visibility:hidden;" type="file" name="file">
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
					<c:forEach items="${albums.content}" var="album">
						<div class="row">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
								<img src="/images/album/${album.band.name}/${album.name}${album.path}?version=${album.version}" height="40" width="40"/>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
								${album.name}
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
								<a href="/admin/album/delete/${album.id}">
									<spring:message code="admin.delete"/>
								</a>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
								<a href="/admin/album/update/${album.id}">
									<spring:message code="admin.update"/>
								</a>
							</div>
						</div>
					</c:forEach>
					<hr>
					<div class="page_buttons">
						<c:if test="${albums.number ne 0}">
							<a class="prev" href="/admin/album?page=${albums.number}&size=${albums.size}">
								<spring:message code="admin.prevPage"/>
							</a>
						</c:if>
						<c:if test="${albums.number ne albums.totalPages-1}">
							<a class="next" href="/admin/album?page=${albums.number+2}&size=${albums.size}">
								<spring:message code="admin.nextPage"/>
							</a>
						</c:if>
					</div>
					<hr>
					<div class="page_number_admin">
						<a class="one" href="/admin/album?page=1&size=1">1</a>
						<a class="five" href="/admin/album?page=1&size=5">5</a>
						<a class="ten" href="/admin/album?page=1&size=10">10</a>
						<a class="twenty" href="/admin/album?page=1&size=20">20</a>
					</div>
					<hr>
					<p><a href="/admin"><spring:message code="admin.back"/></a></p>
				</div>
			</div>	
		</div>
	</security:authorize>
</div>