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
<spring:url var="url_jqlib" value="/resources/js/jquery-3.3.1.min.js" />
<script src="${url_jqlib}"></script>
<title>Users</title>
<script>
	function changeStatus(userId,loginStatus){
		
		$.ajax({
			
			url:'change_status',
			data:{userId:userId,loginStatus:loginStatus},
			success : function(data){
				
				alert(data);
			}
		});
	}
</script>
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
			<td height="350px" valign="top">
				<h3>Users</h3>
				<table border="1">
					<tr>
						<th>SR</th>
						<th>User ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Login name</th>
						<th>Login status</th>
					</tr>
					<c:forEach var="u" items="${userList}" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${u.user_id}</td>
							<td>${u.name}</td>
							<td>${u.email}</td>
							<td>${u.name}</td>
							<td><select id="id_${u.user_id}" onchange="changeStatus(${u.user_id},$(this).val())">
									<option value="1">Active</option>
									<option value="2">Blocked</option>
							</select> <script>
							$('#id_${u.user_id}').val('${u.loginStatus}');
							</script></td>
						</tr>
					</c:forEach>

				</table>
			</td>

		</tr>
		<tr>
			<td height="25px"><jsp:include page="include/footer.jsp" /></td>
		</tr>

	</table>
</body>
</html>