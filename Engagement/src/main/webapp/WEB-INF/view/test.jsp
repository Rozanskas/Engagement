<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url var="url_jqlib" value="/resources/js/jquery-3.3.1.min.js" />
<script src="${url_jqlib}"></script>
<title>AJAX test page</title>
</head>
<body>
	AJAX Test PAge
	<script>
		$(document).ready(function() {
			//alert('Jquery is ready');
			$("#id_get_time").click(function(){
				
				$.ajax({
					url : 'get_time',
					success : function(data){
						$("#id_time").html(data);
						
					}
				});
			});

		})
	</script>
	<button id="id_get_time">click here for time</button>
	<p id="id_time"></p>

</body>
</html>