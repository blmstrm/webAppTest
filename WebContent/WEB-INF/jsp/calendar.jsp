<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href="<c:url value="/resources/css/fullcalendar.css" />" />
<script type='text/javascript' src="<c:url value="/resources/js/jquery.js"/>"></script>
<script type='text/javascript' src="<c:url value="/resources/js/fullcalendar.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Calendar test</title>
<script>
$(document).ready(function() {

  $.getJSON('/webAppTest/getEvent', function (data) {
    var calendar = $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        events:[data]
    });
 });
});
</script>
</head>
<body>
<div id='calendar'></div>
</body>
</html>