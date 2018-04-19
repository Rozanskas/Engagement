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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<spring:url var="url_jqlib" value="/resources/js/jquery-3.3.1.min.js" />
<script src="${url_jqlib}"></script>

<title>Employer Messages</title>
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
			<td height="350px" valign="top"><c:if
					test="${err != null}">
					<p class="error">${err}</p>
				</c:if> <c:if test="${param.act eq 'success'}">
					<p class="success">Message was sent successfully</p>
				</c:if>
				<h3>Message History</h3>
				
					<table class="table_background" border="1" cellpadding="3"
						width="100%">
						<tr>
							<th>Number</th>
							<th>Header</th>
							<th>Receiver Email</th>
							<th>Message</th>
							<th>Date</th>
							
						</tr>
						<c:if test="${empty messages}">
							<tr>
								<td align="center" colspan="8" class="error">No Records
									Presented</td>
							</tr>
						</c:if>


						<c:forEach var="c" items="${messages}" varStatus="st">
							<script>var bool=0;</script>
							<tr>	
								<td>${st.count}</td>
								<td>${c.header}</td>
								<td>${c.receiverEmail}</td>
								<td>${c.message}</td>
								<td>${c.date}</td>
								
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
<script>

		
		function engage(userId){
			
			$.ajax({
				url : 'engage',
				data : {
					cid : userId
					
				},
				success : function(data) {
					window.location.reload();
				}
			});
		}
function undo(userId){
			
			$.ajax({
				url : 'undo',
				data : {
					cid : userId
					
				},
				success : function(data) {
					window.location.reload();
				}
			});
		}
	
</script>
</html>