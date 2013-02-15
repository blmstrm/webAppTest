$(document).ready(function() {

  $.getJSON('/webAppTest/getEvent', function (data) {
	  
    var calendar = $('#calendar').fullCalendar({
		header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay',
            ignoreTimezone: false
        },
        selectable: true,
        selectHelper: true,
        editable: true,
        events: [data]
			
    });
 });
});

