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
<title>Search students</title>
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
			<td height="350px" valign="top">Contact List <c:if
					test="${err != null}">
					<p class="error">${err}</p>
				</c:if> <c:if test="${param.act eq 'sv'}">
					<p class="success">Contact was saved successfully</p>
				</c:if> <c:if test="${param.act eq 'del'}">
					<p class="success">Contact was deleted successfully</p>
				</c:if> <c:if test="${param.act eq 'ed'}">
					<p class="success">Contact was updated successfully</p>
				</c:if>
				<h3>Search students</h3>
				<table width="100%">
					<tr>
						<td >
							<form
								action="<spring:url value="/employer/student_search"/>">
								<input size="19" type="text" name="uni" value="${param.uni}"
									placeholder="Enter University to search" />
									<input size="19" type="text" name="course" value="${param.course}"
									placeholder="Enter Course to search" />
									<input size="19" type="text" name="pp" value="${param.pp}"
									placeholder="Search in personal projects" />
									<input size="19" type="text" name="grade" value="${param.grade}"
									placeholder="Enter grade to search" />
									<input size="19" type="text" name="skill" value="${param.skill}"
									placeholder="Enter skill to search" />
									<input size="19" type="text" name="extra" value="${param.extra}"
									placeholder="Search in extra" />
								<input alt="Submit" type="submit" id="find" value="Find!">
							</form>
						</td>
					</tr>

				</table> <br />

				<form action="<spring:url value="/user/bulk_cdelete"/>">

					<button>Delete Selected Records</button>
					</br> </br>
					<table class="table_background" border="1" cellpadding="3" width="100%">
						<tr>
							<th></th>
							<th>No</th>
							<th>Name</th>
							<th>Email</th>
							<th>University</th>
							<th>Course</th>
							<th>Personal projects</th>
							<th>Grades</th>
							<th>Skills</th>
							<th>Extra</th>
							<th>Action</th>
						</tr>
						<c:if test="${empty studentList}">
							<tr>
								<td align="center" colspan="8" class="error">No Records
									Presented</td>
							</tr>
						</c:if>
						<c:forEach var="c" items="${studentList}" varStatus="st">
							<tr>
								<td align="center"><input type="checkbox" name="cid"
									value="${c.user_id}"></td>
								<td>${st.count}</td>
								<td>${c.name}</td>
								<td>${c.email}</td>
								<td>${c.skillSet.university}</td>
								<td>${c.skillSet.course}</td>
								<td>${c.skillSet.personalProjects}</td>
								<td>${c.skillSet.grades}</td>
								<td>${c.skillSet.skill}</td>
								<td>${c.skillSet.extra}</td>

								<spring:url var="url_engage" value="/employer/engage">
									<spring:param name="cid" value="${c.user_id}" />
								</spring:url>
								<spring:url var="url_undo" value="/employer/undo">
									<spring:param name="cid" value="${c.user_id}" />
								</spring:url>
								
								<td><a href="${url_engage}">Engage</a>/<a href="${url_undo}">Undo</a></td>
							</tr>
						</c:forEach>
					</table>

				</form>

			</td>
		</tr>
		<tr>
			<td height="25px"><jsp:include page="include/footer.jsp" /></td>
		</tr>

	</table>
</body>
</html>