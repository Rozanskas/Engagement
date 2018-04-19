<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='<spring:url value="/resources/css/style.css"/>'
	rel="stylesheet" />
<title>User Login</title>
</head>
<spring:url var="url_bg" value="/images/img01.jpg"></spring:url>
<spring:url var="url_reg" value="/reg_form" />

<body background="${url_bg}">
	<table border="1" width="80%" align="center">
		<tr>
			<td height="80px"><jsp:include page="include/header.jsp" /></td>
		</tr>
		<tr>
			<td height="25px">
				<h3 class="error"><jsp:include page="include/menu.jsp" /></h3>

			</td>
		</tr>
		<tr>
			<td height="350px" valign="top"><c:if test="${err != null}">
					<p class="error">${err}</p>
				</c:if> <c:if test="${param.act eq 'lo'}">
					<p class="success">You have been Logged out successfully</p>
				</c:if> <c:if test="${param.act eq 'reg'}">
					<p class="success">User Registered successfully. Please Login</p>
				</c:if> <spring:url var="url_login" value="/login"></spring:url>
				<h2>About the App</h2>
				<p>Software graduates nowadays are living in stressful
					environment, because they all thinking about the same thing- how to
					find first job. Not rarely their skillset is not the biggest
					capacity, it is either of their own effort putted into studies or
					the actual material was not related to current situation in
					software industry. During job interview candidates often get to
					solve technical tests and if the knowledge foundation is restricted
					to a university level probability to get hired is small. Some
					students are learning outside the university hours to increase
					their capabilities and gain new knowledge, however it is difficult
					for employer to distinguish these candidates among others because
					one interview is not sufficient to understand what the candidate is
					capable of. Novel approach is needed which would show the actual
					knowledge and real achievements which would come together with
					related experience. Proposed system is aiming to solve this problem
					and provide new way of how students and employers engage in job
					market. System would allow employers to follow chosen students and
					provide learning material with occasional tests on it. This way
					employers would be sure about students industrial knowledge, grades
					and interest about certain area.</p></td>
		</tr>
		<tr>
			<td height="25px"><jsp:include page="include/footer.jsp" /></td>
		</tr>

	</table>
</body>
</html>