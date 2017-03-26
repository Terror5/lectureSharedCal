<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>Registration</h1>
<form:form method="POST" commandName="password" action="${pageContext.request.contextPath}/public/register.html" >
<table cellspacing="5">
	<tr>
		<td>e-Mail:</td>
		<td><form:input path="employee.email" class="inputf"/></td>
		<td><form:errors path="employee.email" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><form:password path="pwString1" class="inputf"/></td>
		<td><form:errors path="pwString1" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td>Re-enter Password:</td>
		<td><form:password path="pwString2" class="inputf"/></td>
		<td><form:errors path="pwString2" cssStyle="color: red;" /></td>
	</tr>
		<tr>
		<td>First Name:</td>
		<td><form:input path="employee.firstName" class="inputf"/></td>
		<td><form:errors path="employee.firstName" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><form:input path="employee.lastName" class="inputf"/></td>
		<td><form:errors path="employee.LastName" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Address:</td>
		<td><form:input path="employee.address" class="inputf"/></td>
		<td><form:errors path="employee.address" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><form:input path="employee.phone" class="inputf"/></td>
		<td><form:errors path="employee.phone" cssStyle="color: red;"/></td>
	</tr>				
	<tr>
		<td><input type="submit" value="Register" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
</form:form>
