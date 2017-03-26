<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sec:authorize access="hasRole('ROLE_USER')">
		Current user: <sec:authentication property="principal.username"/>
		<br>
		<form action="<c:url value="/logout"/>" method="post">
			<input type="submit" class="btn" value="Log out" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
		</form>
</sec:authorize>


