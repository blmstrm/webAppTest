$(function(){
	var Event = Backbone.Model.extend();

	var Events = Backbone.Collection.extend({
		model: Event,
		url:'events'
	});

	var EventsView = Backbone.View.extend({
		initialize: function(){
			_.bindAll(this);

			this.collection.bind('reset',this.addAll);
			this.collection.bind('add',this.addOne); 
			this.collection.bind('change',this.change);
			this.collection.bind('destroy',this.destroy);

			this.eventView = new EventView();
		},
		render: function() {
			$(this.el).fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				axisFormat: 'H:mm{ - H:mm}',
				timeFormat: 'H(:mm)',
				firstDay:1,
				ignoreTimezone: false,
				weekNumbers:true,
				dayNames: ['Söndag', 'Måndag', 'Tisdag', 'Onsdag',
				           'Torsdag', 'Fredag', 'Lördag'],
				           dayNamesShort:['Sön', 'Mån', 'Tis', 'Ons', 'Tor', 'Fre', 'Lör'],
				           monthNames:['Januari', 'Februari', 'Mars', 'April', 'Maj', 'Juni', 'Juli',
				                       'Augusti', 'September', 'Oktober', 'November', 'December'],
				                       monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'Maj', 'Jun',
				                                        'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dec'],
				                                        buttonText:{
				                                        	prev:     '&lsaquo;', // <
				                                        	next:     '&rsaquo;', // >
				                                        	prevYear: '&laquo;',  // <<
				                                        	nextYear: '&raquo;',  // >>
				                                        	today:    'Idag',
				                                        	month:    'Månad',
				                                        	week:     'Vecka',
				                                        	day:      'Dag'
				                                        },
				                                        weekNumberTitle:"V",
				                                        allDayDefault:false,
				                                        viewDisplay: function(view){
				                                        	$(this.el).fullCalendar('refetchEvents');
				                                        },
				                                        selectable: true,
				                                        selectHelper: true,
				                                        editable: true,
				                                        select: this.select,
				                                        eventClick: this.eventClick,
				                                        eventDrop: this.eventDropOrResize,
				                                        eventResize: this.eventDropOrResize
				                                        

			});
		},
		addAll: function(){
			$(this.el).fullCalendar('addEventSource',this.collection.toJSON());
		},
		addOne: function(event){
			$(this.el).fullCalendar('renderEvent',event.toJSON());
		},
		select: function(startDate,endDate){
			this.eventView.collection = this.collection;
			this.eventView.model = new Event({start: startDate,end:endDate,editable:true});
			this.eventView.render();
		}, 
		eventClick: function(fcEvent){
			this.eventView.model = this.collection.get(fcEvent.id);
			this.eventView.render();
		},
		change: function(event){
			var fcEvent = $(this.el).fullCalendar('clientEvents',event.get('id'))[0];
			fcEvent.title = event.get('title');
			fcEvent.start = event.get('start');
			fcEvent.end =  event.get('end');
			$(this.el).fullCalendar('updateEvent',fcEvent);
		},
		eventDropOrResize: function(fcEvent){
			this.collection.get(fcEvent.id).save({start: fcEvent.start, end: fcEvent.end});
		},
		destroy: function(event){
			$(this.el).fullCalendar('removeEvents',event.id);
		}
	});

	var EventView = Backbone.View.extend({
		el: $('#eventDialog'),
		initialize: function() {

			$("#allDay").click(function() {
				if($(this).is(":checked"))
				{
					$('#from').attr('disabled',true);
					$('#to').attr('disabled',true);
				}else{
					$('#from').attr('disabled',false);
					$('#to').attr('disabled',false);
				}
			});

			_.bindAll(this);
		},
		render: function(){

			
			
			if (this.model.isNew()){
				this.$('#allDay').attr('checked',false);
			}else{

				if(this.$('#allDay').is(':checked')){
					this.$('#from').attr('disabled',true);
					this.$('#to').attr('disabled',true);
				}else{
					this.$('#from').attr('disabled',false);
					this.$('#to').attr('disabled',false);
				}
			}

			var buttons = {'Ok': this.save};

			if(!this.model.isNew()){
				/*Parse event date to show in dialog box.*/
				_.extend(buttons, {'Delete':this.destroy});
				var startDate = $.fullCalendar.parseDate(this.model.get('start'));
				var endDate = $.fullCalendar.parseDate(this.model.get('end'));

				/*Adjust time to timezone*/
				var offset = (new Date()).getTimezoneOffset();
				var sTime = startDate.getTime();
				var eTime = endDate.getTime();

				startDate = new Date(sTime-(offset*60000));
				endDate = new Date(eTime-(offset*60000));

				this.$('#from').val(
						$.fullCalendar.formatDate(startDate,'HH:mm'));
				this.$('#to').val(
						$.fullCalendar.formatDate(endDate,'HH:mm'));
			}else{
				this.$('#from').val('');
				this.$('#to').val('');
			}
			_.extend(buttons, {'Cancel':this.close});

			$(this.el).dialog({
				modal: true,
				title: (this.model.isNew() ? 'New': 'Edit')+' Event',
				buttons: buttons,
				open: this.open
			});
			return this;
		},
		open: function(){
			$('.datepicker').timepicker();
			$('.datepicker').timepicker();
			this.$('#title').val(this.model.get('title'));
		},
		save: function(){
		
			if(this.$('#allDay').is(':checked')){
				this.model.set({'allDay':true});
			}else{
				this.model.set({'allDay':false});
			}
			this.model.set({'title':this.$('#title').val()});
			
			/*Convert from string to date to be able to change*/
			var sDate = $.fullCalendar.parseDate(this.model.get('start'));
			var eDate = $.fullCalendar.parseDate(this.model.get('end'));

			/*If you have from value*/
			if(!(this.$('#from').val().length == 0)){
				var splitStart = this.$('#from').val().split(':');
				sDate.setHours(splitStart[0],splitStart[1]);
				this.model.set({'start':sDate});
			}

			/*If you have to value*/
			if(!(this.$('#to').val() == 0)){
				var splitEnd = this.$('#to').val().split(':');
				eDate.setHours(splitEnd[0],splitEnd[1]);
				this.model.set({'end':eDate});

			}

			if (this.model.isNew()){
				this.collection.create(this.model, {wait: true, success: this.close});
			}else {
				this.model.save({}, {wait: true, success:  this.close});
			}
		},
		close: function(){
			$(this.el).dialog('close');
		},
		destroy: function(){
			this.model.destroy({success: this.close});
		}		
	});
	$(function(){
		
		console.log('Infun');
		
	});

	var events =  new Events();
	new EventsView({el: $("#calendar"),collection: events}).render();
	events.fetch();

});
