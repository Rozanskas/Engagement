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
<title>Contact List</title>
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
				<h3>Contact List</h3>
				<table width="100%">
					<tr>
						<td align="right">
							<form action="<spring:url value="/user/contact_search"/>">
								<input type="text" name="freeText" value="${param.freeText}"
									placeholder="Enter contact name" />
								<button>Find</button>
							</form>
						</td>
					</tr>

				</table> <br />

				<form action="<spring:url value="/user/bulk_cdelete"/>">
				
				<button>Delete Selected Records</button>
				</br>
				</br>
				<table border="1" cellpadding="3" width="100%">
					<tr>
						<th>Select</th>
						<th>CID</th>
						<th>Name</th>
						<th>Phone</th>
						<th>Email</th>
						<th>Address</th>
						<th>Remark</th>
						<th>Action</th>
					</tr>
					<c:if test="${empty contactList}">
						<tr>
							<td align="center" colspan="8" class="error">No Records
								Presented</td>
						</tr>
					</c:if>
					<c:forEach var="c" items="${contactList}" varStatus="st">
						<tr>
							<td align="center"><input type="checkbox" name="cid" value="${c.contactId}"></td>
							<td>${st.count}</td>
							<td>${c.name}</td>
							<td>${c.address}</td>
							<td>${c.email}</td>
							<td>${c.phoneNumber}</td>
							<td>${c.remark}</td>
							<spring:url var="url_del" value="/user/del_contact">
								<spring:param name="cid" value="${c.contactId}" />
							</spring:url>
							<spring:url var="url_edit" value="/user/edit_contact">
								<spring:param name="cid" value="${c.contactId}" />
							</spring:url>
							<td><a href="${url_edit}">Edit</a> / <a href="${url_del}">Delete</a></td>
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