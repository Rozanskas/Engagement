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
<title>Message form</title>
</head>
<spring:url var="url_bg" value="/images/img01.jpg"></spring:url>

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
			<td height="350px" valign="top">Message form <c:if
					test="${err != null}">
					<p class="error">${err}</p>
				</c:if> <c:if test="${param.act eq 'lo'}">
					<p class="success">"You have been Logged out successfully"</p>
				</c:if> <spring:url var="url_send_Message" value="/employer/sendMessage"></spring:url>
				<form:form action="${url_send_Message}" modelAttribute="message">
					<table border="1">

						<tr>
							<td>Header</td>
							<td><form:input path="header" /></td>

						</tr>
						<tr>
							<td>Message</td>
							<td><form:textarea path="message" /></td>

						</tr>
						<tr>
							<td colspan="2" align="right">
								<button>Send</button> <br />

							</td>


						</tr>

					</table>
				</form:form>

			</td>
		</tr>
		<tr>
			<td height="25px"><jsp:include page="include/footer.jsp" /></td>
		</tr>

	</table>
</body>
</html>