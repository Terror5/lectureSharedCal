/**
 * 
 */

var url = "";
var weekdays = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
var weekdayCount = [0,0,0,0,0,0,0];
var day = 24 * 3600 * 1000;
var thisMonday;
var currMaxRow;

function setAppointmentBody(appointments, appUrl, monday)
{			
	url = appUrl;
	thisMonday = monday;
	currMaxRow = 0;
	weekdayCount = [0,0,0,0,0,0,0];
	initializeAppointmentBody(monday);	
	initializeAppointmentView(appointments);
	createAppointmentForm();
	
}

function initializeAppointmentBody(monday)
{	
	$("#content").html("<h1>Appointments</h1>");
	setButtonsChangeWeek(monday);
	
	var currDate = new Date();
	currDate.setTime(monday.getTime());
		
	$("#content").append('<div class="tables" align="center" id="tableDiv"></div>');
	$("#tableDiv").append('<table id="appTable"></table>');
	$("#appTable").append('<tr id="headRow"></tr>');
	
	for(var i = 0; i < 7; i++)
	{
		var dateString = formatDate(currDate);		
		$("#headRow").append('<td id="'+weekdays[i]+'">'+weekdays[i]+'<br/>'+dateString+'</td>');
		currDate.setTime(currDate.getTime() + day);		
	}
}

function setButtonsChangeWeek(monday)
{

	$("#content").append('<button id="previous" class="btn">Previous</button>');
	$("#previous").click(function() {
			var currDate = new Date();		
			currDate.setTime(thisMonday.getTime() - 7 * day);
			callAjax('/list/',formatDate(currDate))
		});	
	
	$("#content").append('<button id="next" class="btn">Next</button>');
	$("#next").click(function() {
			var currDate = new Date();		
			currDate.setTime(thisMonday.getTime() + 7 * day);
			callAjax('/list/',formatDate(currDate))
		});
}

function initializeAppointmentView(appointments)
{
	var i;
	for (i = 0; i < appointments.length; ++i)
	{				
		insertAppointmentTd(appointments[i]);
	}	
}

function insertAppointmentTd(appointment)
{
	var dayOfWeek = getDay(parseDate(appointment.date).getDay());
	var day = weekdays[dayOfWeek];
	var row = getMaxRow(dayOfWeek);
	
	if(weekdayCount[dayOfWeek] > currMaxRow){
		initializeTableRow(row);
		$("#"+day+row).append(convert2Http(appointment));
	}else{
		$("#"+day+row).append(convert2Http(appointment));
	}
}

function convert2Http(appointment)
{
	var httpString = '<p id="category'+appointment.idAppointment+'">Category: '+appointment.category+'</p>';
	httpString += '<p id="name'+appointment.idAppointment+'">Name: '+appointment.name+'</p>';
	httpString += '<p id="date'+appointment.idAppointment+'">Date: '+appointment.date+'</p>';
	httpString += '<p id="employee'+appointment.idAppointment+'" empId="idEmployee'+appointment.employee.idEmployee+'">Creator: '
					+appointment.employee.firstName+' '+appointment.employee.lastName+'</p>';
	return httpString;
}


function initializeTableRow(row){
	currMaxRow += 1;
	$("#appTable tbody").append('<tr id="row'+row+'"></tr>');
	for(var i = 0; i < 7; i++)
	{	
		$("#appTable tbody").append('<td id="'+weekdays[i]+row+'"></td>');	
	}
}

function callAjax(actionUrl,dateString)
{
	var monday = parseDate(dateString);
	var dateUrl = formatDateURL(monday);
    $.ajax({
  	  method: "GET",
  	  url: url + actionUrl + dateUrl,
  	  dataType: "json",
  	  error: function(jqXHR,textStatus,errorThrown){
  		  alert(errorThrown);
  	  },
  	  success: function(data,textStatus,jqXHR){
  		  setAppointmentBody(data, url, monday)
  	  },
  	});  	
}

function postAjaxApp(actionUrl)
{
	var jsonData = getAppointmentForm();
    $.ajax({
  	  method: "POST",
  	  url: url + actionUrl,
      headers: { 
          'Accept': 'application/json',
          'Content-Type': 'application/json' 
      },
  	  dataType: "json",
  	  beforeSend: function(xhr) {
        xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
  	  },
  	  data: jsonData,
  	  error: function(jqXHR,textStatus,errorThrown){
  		  alert(errorThrown);
  	  },
  	  success: function(data,textStatus,jqXHR){
  		newAppointment(data);
  	  },
  	});  	
}

function formatDate(date)
{
	var dd = date.getDate();
	var mm = date.getMonth() + 1;
	var yyyy = date.getFullYear();
	return (dd + '.' + mm + '.' + yyyy);
}

function formatDateURL(date)
{
	var dd = date.getDate();
	var mm = date.getMonth() + 1;
	var yyyy = date.getFullYear();
	return (dd + '-' + mm + '-' + yyyy);
}

function parseDate(dateString)
{
	var parts = dateString.split(".");
	date = new Date();
	date.setFullYear(parts[2], parts[1] - 1, parts[0]);
	return date;
}

function getMonday(today)
{
	monday = new Date();
	if(today.getDay() > 0){
		monday.setDate(today.getDate() - today.getDay() + 1)
	}else {
		monday.setDate(today.getDate() - 6)
	}	
	return monday;
}

function getDay(dayOfWeek)
{
	if(dayOfWeek > 0){
		return dayOfWeek - 1;
	} else {
		return 6;
	}		
}

function getMaxRow(col)
{
	weekdayCount[col] += 1;
	return weekdayCount[col];
}

function newAppointment(appointment)
{
	if(!(appointment.idAppointment == 0 || appointment == undefined))
	{
		insertAppointmentTd(appointment);
		$("#formCategory").val(""); 
		$("#formName").val("");
		$("#formDate").val("");
	}
}

function getAppointmentForm()
{	
	var appointment = {
			idAppointment:0,
			category:$("#formCategory").val(),
			name:$("#formName").val(),
			date:$("#formDate").val(),
			employee:{
				firstName:"",
				idEmployee:0,
				lastName:""}
	};		
	return JSON.stringify(appointment);
}

function createAppointmentForm()
{
	$("#content").append('<form id="form" method="POST" action="'+url+'/create.html"></form>');
	$("#form").append('<table id="formTable" cellspacing="5"></table>');
	$("#formTable").append('<tr><td>Name:</td><td><input id="formName" type="text" class"inputf"></td></tr>');
	$("#formTable").append('<tr><td>Category:</td><td><input id="formCategory" type="text" class"inputf"></td></tr>');
	$("#formTable").append('<tr><td>Date:</td><td><input id="formDate" type="text" class"inputf"></td></tr>');
	$("#formTable").append('<tr><td><input id="submit" type="submit" value="Create" class="btn"></td><td></td></tr>');
	$("#submit").click(function(event) {
		event.preventDefault();		
		postAjaxApp('/create');
	});
	new JsDatePick({
	    useMode:2,
	    target:"formDate",        
	    cellColorScheme:"ocean_blue",
	    dateFormat:"%d.%m.%Y",
	});
}
