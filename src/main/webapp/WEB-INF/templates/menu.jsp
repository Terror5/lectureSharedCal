<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<ul>
		<li><a href="${pageContext.request.contextPath}/secured/home.html">Home</a></li>
	<sec:authorize access="hasRole('ROLE_ADMIN')">	
		<li><a href="${pageContext.request.contextPath}/admin/control.html">Admin Section</a></li>
	</sec:authorize>		
	<sec:authorize access="isAnonymous()"> 
		<li><a href="${pageContext.request.contextPath}/public/register.html">Register</a></li>
		<li><a href="${pageContext.request.contextPath}/public/forgot.html">Forgotten password?</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<li><a href="${pageContext.request.contextPath}/secured/appointmentClassic/list.html">Appointments Classic</a></li>
		<li><a href="${pageContext.request.contextPath}/secured/appointment/list.html" id="appList">Appointments</a></li>
		<script type="text/javascript">
		$('#appList').click(function(event){
		    event.preventDefault();
		    var monday = getMonday(new Date());
		    var appUrl = "${pageContext.request.contextPath}/secured/appointment";
		    $.ajax({
		    	  method: "GET",
		    	  url: appUrl + "/list/" + formatDateURL(monday),
		    	  dataType: "json",
		    	  error: function(jqXHR,textStatus,errorThrown){
		    		  $("#content").html(jqXHR.responseText);
		    	  },
		    	  success: function(data,textStatus,jqXHR){
		    		  setAppointmentBody(data,appUrl,monday);
		    	  },
		    	});    
		});
		</script>				
	</sec:authorize> 
	</ul>
