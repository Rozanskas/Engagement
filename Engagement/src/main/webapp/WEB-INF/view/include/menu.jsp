<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>

<s:url var="url_logout" value="/logout"/>
<s:url var="url_reg" value="/reg_form"/>
<s:url var="url_contact_form" value="/user/contact_form"/>
<s:url var="url_student_home" value="/student/dashboard"/>
<s:url var="url_admin_home" value="/admin/dashboard"/>
<s:url var="url_emp_home" value="/employer/dashboard"/>
<s:url var="url_clist" value="/user/c_list"/>
<s:url var="url_index" value="/"/>
<s:url var="url_users" value="/admin/users"/>
<s:url var="url_students" value="/employer/student_list"/>
<s:url var="url_student_skill" value="/student/skill_set"/>
<s:url var="url_eng_students" value="/employer/engaging_candidates"/>
<s:url var="url_emp_messages" value="/employer/messages"/>
<s:url var="url_emp_update" value="/employer/updateProfile"/>
<s:url var="url_engaging_employers" value="/student/engaging_employers" />
<s:url var="url_student_messages" value="/student/messages" />
<s:url var="url_about" value="/about" />




<c:if test="${sessionScope.userId==null}">
<a href="${url_index}" >Home</a> <a href="${url_index}" >Login</a> <a href="${url_reg}" >Register</a> <a href="${url_about}" >About</a>
</c:if>
<c:if test="${sessionScope.userId!=null && sessionScope.role== 1}">
<a href="${url_emp_home}" > Home </a> <a href="${url_emp_update}" >Update profile </a> <a href="${url_students}" >Search Candidates </a> <a href="${url_eng_students}" > Engaging candidates </a> <a href="${url_emp_messages}" > Messages </a>  <a href="${url_logout}" >Log Out</a> 
</c:if>
<c:if test="${sessionScope.userId!=null && sessionScope.role== 3}">
<a href="${url_admin_home}" >Home</a> <a href="${url_users}" >User List</a> <a href="" >About</a> <a href="${url_logout}" >Log Out</a>
</c:if>
<c:if test="${sessionScope.userId!=null && sessionScope.role== 2}">
<a href="${url_student_home}" >Home</a> <a href="${url_student_skill}" >Skill Set</a> <a href="${url_engaging_employers}" >Engaging Employers</a> <a href="${url_student_messages}" >Messages</a> <a href="${url_logout}" >Log Out</a> 
</c:if>