<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<footer>
<div class="row footer">
	<div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-6 col-xs-offset-2 row">
		<div class="container">
			<ul class="social_menu">
				<li>
					<a href="#">
						<span class="icon fa-stack fa-3x">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="icon fa-stack fa-3x">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-vk  fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="icon fa-stack fa-3x">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-youtube fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="icon fa-stack fa-3x">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-lastfm fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="icon fa-stack fa-3x">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-soundcloud fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="icon fa-stack fa-3x">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-spotify fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				</li>
			</ul>	
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		<br>
		<br>
		<br>
		<form>
			<input name="locale" type="image" value="en" ${localeCode == 'en' ? selected : ''} src="/resources/images/en.jpg" style="height: 25px; width: 30px;">
			<input name="locale" type="image" value="ru" ${localeCode == 'ru' ? selected : ''} src="/resources/images/ru.png" style="height: 25px; width: 30px;">
			<input name="locale" type="image" value="ua" ${localeCode == 'ua' ? selected : ''} src="/resources/images/ua.png" style="height: 25px; width: 30px;">
		</form>
	</div>
</div>
</footer>
