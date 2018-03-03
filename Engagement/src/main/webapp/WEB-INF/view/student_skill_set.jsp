<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='<spring:url value="/resources/css/style.css"/>'
	rel="stylesheet" />
<title>Student Skill set</title>
</head>
<spring:url var="url_bg" value="/images/img01.jpg"></spring:url>
<spring:url var="url_add_skill" value="/student/skill_form"></spring:url>

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
			<td height="350px" valign="top"><h2>Student Skill Set</h2>
			<c:if test="${param.act eq 'sv'}">
					<p class="success">Skill Set was saved successfully</p>
				</c:if>
				<c:if test="${param.act eq 'ed'}">
					<p class="success">Changes have been applied successfully</p>
				</c:if>
				<c:if test="${param.act eq 'del'}">
					<p class="success">Skill set was deleted successfully</p>
				</c:if>
				<table class="table_background" border="1" cellpadding="3" width="100%">
					<tr>

						<th>University</th>
						<th>Course</th>
						<th>Personal Projects</th>
						<th>Grades</th>
						<th>Extra</th>
						<th>Skills</th>
						<th>Action</th>
					</tr>
					<c:choose>
						<c:when test="${empty skillSet}">

							<tr>
								<td align="center" colspan="8" class="error">No Records
									Presented <a href="${url_add_skill}">Add Skill Set</a>
								</td>

							</tr>
						</c:when>
						<c:otherwise>
						
							<tr>


								<td>${skillSet.university}</td>
								<td>${skillSet.course}</td>
								<td>${skillSet.personalProjects}</td>
								<td>${skillSet.grades}</td>
								<td>${skillSet.extra}</td>
								<td>${skillSet.skill}</td>

								<spring:url var="url_del" value="/student/del_skill">
									<spring:param name="cid" value="${skillSet.skillSetId}" />
								</spring:url>
								<spring:url var="url_edit" value="/student/edit_skill">
									<spring:param name="cid" value="${skillSet.skillSetId}" />
								</spring:url>
								<td><a href="${url_edit}">Edit</a> / <a href="${url_del}">Delete</a></td>
							</tr>
							
						</c:otherwise>

					</c:choose>

				</table>

			</td>
		</tr>
		<tr>
			<td height="25px"><jsp:include page="include/footer.jsp" /></td>
		</tr>

	</table>
</body>
</html>