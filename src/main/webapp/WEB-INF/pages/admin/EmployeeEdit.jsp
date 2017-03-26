<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Edit Employee page</h1>
<form:form method="POST" commandName="employee" action="${pageContext.request.contextPath}/admin/employee/edit/${employee.idEmployee}.html" >
<table cellspacing="5">
	<tr>
		<td>E-Mail</td>
		<td><form:input path="email" class="inputf"/></td>
		<td><form:errors path="email" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><form:password path="pwHash" class="inputf"/></td>
		<td><form:errors path="pwHash" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td>First Name</td>
		<td><form:input path="firstName" class="inputf"/></td>
		<td><form:errors path="firstName" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Last name</td>
		<td><form:input path="lastName" class="inputf"/></td>
		<td><form:errors path="lastName" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><form:input path="address" class="inputf"/></td>
		<td><form:errors path="address" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Phone</td>
		<td><form:input path="phone" class="inputf"/></td>
		<td><form:errors path="phone" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Active</td>
		<td><form:checkbox path="active" /></td>
		<td><form:errors path="active" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Role</td>
		<td>
			<form:select path="role" >
			<form:option value="USER" label="User" /> 
			<form:option value="ADMIN" label="Admin" />
			</form:select>
		</td>
		<td><form:errors path="role" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Enterprise</td>
		<td>
			<form:select path="enterprise" >
				<form:option value="-" label="-- Select enterprise --" /> 
                <form:options items="${enterpriseList}" itemValue="idEnterprise" itemLabel="name"/>             
            </form:select>  
		</td>
		<td><form:errors path="enterprise" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Edit" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td><form:hidden path="idEmployee"/></td>
	</tr>
</table>
</form:form>
