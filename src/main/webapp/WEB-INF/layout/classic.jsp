<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/resources/css/style.css"/>
<link rel="stylesheet" href="/resources/css/slider.css"/>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/resources/css/style_admin_common.css"/>
<link rel="stylesheet" href="/resources/css/style_admin.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">
<script src = "https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="/resources/js/skdslider.js"></script>
<script src="/resources/js/main.js"></script>
<script type="text/javascript">
$(function() {
	$("body").css({padding:0,margin:0});
	  var f = function() {
	    $(".ndra-container").css({position:"relative"});
	    var h1 = $("body").height();
	    var h2 = $(window).height();
	    var d = h2 - h1;
	    var h = $(".ndra-container").height() + d;    
	    var ruler = $("<div>").appendTo(".ndra-container");       
	    h = Math.max(ruler.position().top,h);
	    ruler.remove();    
	    $(".ndra-container").height(h);
	  };
	  setInterval(f,1000);
	  $(window).resize(f);
	  f();	 
	});
</script>
<script type="text/javascript">
 	var show;
	function hidetxt(type){
		param=document.getElementById(type);
		if(param.style.display == "none") {
		if(show) show.style.display = "none";
			param.style.display = "block";
			show = param;
		}else param.style.display = "none"
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
    $(".bands_nav").on("click","a", function (event) {
        event.preventDefault();
        var id  = $(this).attr('href'),
            top = $(id).offset().top;
        $('body,html').animate({scrollTop: top}, 700);
    });
});
</script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<div class="container ndra-container">
		<tiles:insertAttribute name="body" />
	</div>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>