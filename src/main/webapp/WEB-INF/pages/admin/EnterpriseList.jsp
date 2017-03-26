<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Enterprise List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/admin/enterprise/create.html">Enterprise create link</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>Enterprise-ID</td>
	<td>Name</td>
	<td>Contact Person</td>
	<td>Contact Email</td>
	<td>Address</td>
	<td>Action</td>
</tr>
<c:forEach var="enterprise" items="${enterpriseList}">
	<tr>
		<td>${enterprise.Enterprise}</td>
		<td>${enterprise.name}</td>
		<td>${enterprise.contactPerson}</td>
		<td>${enterprise.email}</td>
		<td>${enterprise.address}</td>			
		<td>
			<a href="${pageContext.request.contextPath}/admin/enterprise/edit/${enterprise.idEnterprise}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/admin/enterprise/delete/${enterprise.idEnterprise}.html">Delete</a><br/>	
		</td>
	</tr>
</c:forEach>
</table>
</div>