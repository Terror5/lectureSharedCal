<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Edit Employee page</h1>
<form:form method="POST" commandName="project" action="${pageContext.request.contextPath}/admin/project/edit/${employee.idEmployee}.html" >
<table cellspacing="5">
	<tr>
		<td>Name</td>
		<td><form:input path="name" class="inputf"/></td>
		<td><form:errors path="name" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Date</td>
		<td><form:password path="date" class="inputf"/></td>
		<td><form:errors path="date" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td>Project Leader</td>
		<td><form:input path="ProjectLeader" class="inputf"/></td>
		<td><form:errors path="ProjectLeader" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Edit" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td><form:hidden path="idEmployee"/></td>
	</tr>
</table>
</form:form>