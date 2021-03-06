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
<spring:url var="url_jqlib" value="/resources/js/jquery-3.3.1.min.js" />
<script src="${url_jqlib}"></script>
<script>
	$(document).ready(function() {
		$("#id_check_avail").click(function() {

			$.ajax({
				url : 'check_avail',
				data : {
					username : $("#id_username").val()
				},
				success : function(data) {
					$("#id_div_res").html(data);
				}
			});
		});
	});
</script>
<title>User Registration</title>
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
			<td height="350px" valign="top">User Registration <c:if
					test="${err != null}">
					<p class="error">${err}</p>
				</c:if> <c:if test="${param.act eq 'lo'}">
					<p class="success">"You have been Logged out successfully"</p>
				</c:if> <spring:url var="url_reg" value="/register"></spring:url> <form:form
					action="${url_reg}" modelAttribute="command">
					<table border="1">

						<tr>
							<td>Name</td>
							<td><form:input path="user.name" /></td>

						</tr>


						<tr>
							<td>Email</td>
							<td><form:input id="id_username" path="user.email" />
								<button type="button" id="id_check_avail">Check
									availability</button>
								<div id="id_div_res" class="error"></div></td>

						</tr>
						<tr>
							<td>Password</td>
							<td><form:password path="user.password" /></td>

						</tr>
						<tr>
							<td><form:checkbox path="user.role" value="1" />Employer<form:checkbox
									path="user.role" value="2" />Student
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<button>Submit</button>
								<br />

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