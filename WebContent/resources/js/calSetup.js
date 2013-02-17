$(document).ready(function() {
	
//TODO Get JSON data and show in calendar
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

	});
});

