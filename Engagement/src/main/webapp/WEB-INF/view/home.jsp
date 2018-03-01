<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BORAJI.COM</title>

<link href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
<script type="text/javascript" src='<spring:url value="/resources/js/app.js"/>'></script>

</head>
<body>
   <h1 id="title" class="error">Spring MVC- Static Resource Mapping Example</h1>

   <button onclick="changeColor()">Change Color</button>
   <hr />
   
   <img alt="boraji.com" src="<spring:url value="/images/img01.jpg"/>" width="200">
</body>
</html>