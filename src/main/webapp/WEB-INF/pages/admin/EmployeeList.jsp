<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Employee List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/admin/employee/create.html">Employee create link</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>Employee-ID</td>
	<td>E-Mail</td>
	<td>Name</td>
	<td>Last Name</td>
	<td>Phone</td>
	<td>Address</td>
	<td>Enterprise</td>
	<td>Role</td>
	<td>Active</td>
	<td>Action</td>
</tr>
<c:forEach var="employee" items="${employeeList}">
	<tr>
		<td>${employee.idEmployee}</td>
		<td>${employee.email}</td>
		<td>${employee.firstName}</td>
		<td>${employee.lastName}</td>
		<td>${employee.phone}</td>
		<td>${employee.address}</td>
		<td>${employee.enterprise.name}</td>
		<td>${employee.role}</td>
		<td>${employee.active}</td>		
		<td>
			<a href="${pageContext.request.contextPath}/admin/employee/edit/${employee.idEmployee}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/admin/employee/delete/${employee.idEmployee}.html">Delete</a><br/>	
		</td>
	</tr>
</c:forEach>
</table>
</div>