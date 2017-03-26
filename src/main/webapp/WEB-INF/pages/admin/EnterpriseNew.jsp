<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>New Employee page</h1>
<form:form method="POST" commandName="enterprise" action="${pageContext.request.contextPath}/admin/enterprise/create.html" >
<table cellspacing="5">
	<tr>
		<td>E-Mail</td>
		<td><form:input path="email" class="inputf"/></td>
		<td><form:errors path="email" cssStyle="color: red;"/></td>
	</tr>

	<tr>
		<td>Name</td>
		<td><form:input path="name" class="inputf"/></td>
		<td><form:errors path="name" cssStyle="color: red;"/></td>
	</tr>
	
	<tr>
		<td>Address</td>
		<td><form:input path="address" class="inputf"/></td>
		<td><form:errors path="address" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Contact Person</td>
		<td><form:input path="contactPerson" class="inputf"/></td>
		<td><form:errors path="contactPerson" cssStyle="color: red;"/></td>
	</tr>
	
	<tr>
		<td><input type="submit" value="Create" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
</form:form>
