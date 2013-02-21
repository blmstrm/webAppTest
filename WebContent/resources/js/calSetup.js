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
				
			},
			render: function() {
				$(this.el).fullCalendar({
					header: {
						left: 'prev,next today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					selectable: true,
					selectHelper: true,
					editable: true,
					ignoreTimezone: false,
					select: this.select
				});
			},
			addAll: function(){
				$(this.el).fullCalendar('addEventSource',this.collection.toJSON());
			},
			addOne: function(event){
				$(this.el).fullCalendar('renderEvent',event.toJSON());
			},
			select: function(startDate,endDate){
				var eventView = new EventView();
				eventView.collection = this.collection;
				eventView.model = new Event({start: startDate});
				eventView.render();
			}
		});

		var EventView = Backbone.View.extend({
			el: $('#eventDialog'),
			initialize: function() {
				_.bindAll(this);
			},
			render: function(){
				$(this.el).dialog({
					modal: true,
					title: 'New Event',
					buttons: {'Ok': this.save,'Cancel': this.close}
				});
				
				return this;
			},
			save: function(){
				this.model.set({'title':this.$('#title').val()});
				this.collection.create(this.model, {success: this.close});
			},
			close: function(){
				$(this.el).dialog('close');
			}
		});
		
		console.log('Writing to console');
		var events = new Events();
		new EventsView({el: $("#calendar"),collection: events}).render();
		events.fetch();
	});

