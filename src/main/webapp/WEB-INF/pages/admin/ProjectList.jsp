<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Project List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/admin/project/create.html">Project create link</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>Project-ID</td>
	<td>Name</td>
	<td>Date</td>
	<td>Project Leader</td>
</tr>
<c:forEach var="project" items="${projectList}">
	<tr>
		<td>${project.idProject}</td>
		<td>${project.name}</td>
		<td>${project.date}</td>
		<td>${project.projectLeader}</td>	
		<td>
			<a href="${pageContext.request.contextPath}/admin/project/edit/${project.idProject}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/admin/project/delete/${employee.idProject}.html">Delete</a><br/>	
		</td>
	</tr>
</c:forEach>
</table>
</div>