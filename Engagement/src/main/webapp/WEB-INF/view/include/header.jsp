<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<c:if test="${not empty user}"><span style="color: white;float:right;margin:20px">Welcome ${user.name} !</span></c:if> 
<h1>Engagement app, Grow your employee!</h1>