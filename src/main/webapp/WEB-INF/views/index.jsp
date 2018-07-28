<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<div class="row">
	<div class="main_menu">	
		<div class="ib-container" id="ib-container">
			<security:authorize access="isAnonymous()">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 about_us">
					<a href="/aboutUs" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.aboutUs"/>
					</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 news">
					<a href="/news" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.news"/>
					</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bands">
					<a href="/bands" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.bands"/>
					</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 registration">
					<a href="/registration" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.registration"/>
					</a>
				</div>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 about_us">
					<a href="/aboutUs" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.aboutUs"/>
					</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 news">
					<a href="/news?sort=id,desc" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.news"/>
					</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bands">
					<a href="/bands" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.bands"/>
					</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 myCollection">
					<a href="/myCollection" class="menu_link" style="text-decoration: none;">
						<spring:message code="index.myCollection"/>
					</a>
				</div>
			</security:authorize>
		</div>
	</div>
</div>
<script src="js/modernizr.custom.34978.js"></script>
<script type="text/javascript">
$(function() {
	
	var $container	= $('#ib-container'),
		$articles	= $container.children('div'),
		timeout;
	
	$articles.on( 'mouseenter', function( event ) {
			
		var $article	= $(this);
		clearTimeout( timeout );
		timeout = setTimeout( function() {
			
			if( $article.hasClass('active') ) return false;
			
			$articles.not( $article.removeClass('blur').addClass('active') )
					 .removeClass('active')
					 .addClass('blur');
			
		}, 65 );
		
	});
	
	$container.on( 'mouseleave', function( event ) {
		
		clearTimeout( timeout );
		$articles.removeClass('active blur');
		
	});

});
</script>