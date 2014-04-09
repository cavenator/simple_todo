define(['jquery','underscore','backbone','text!views/TodoContainerView.html','views/Todo','views/ReadOnlyTodo','models/TodoCollection','models/Todo'],
   function($,_,Backbone, htmlTemplate, TodoView, ReadOnlyTodo, TodoCollection,Todo){
      return Backbone.View.extend({
          initialize: function(){
             this.collection = new TodoCollection()
             return this;
          },

          events: {
             "click #add" : "addTodo",
             "click #clearAll": "clearAllTodos"
          },

          addTodo: function(e){
             var pendingTodo = new TodoView();
             pendingTodo.on("todoSaved", this.addToQueue, this);
             pendingTodo.on("cancelTodo", this.cancelTodo, this);
             this.$(".todo-draft").html(pendingTodo.render().el);
             this.disableButtons(true);
          },

          addToQueue: function(todo){
             this.collection.add(todo);
             this.addReadOnlyView(todo);
             this.cancelTodo();
          },

          addReadOnlyView: function(todoModel){
             var readOnlyView = new ReadOnlyTodo({model: todoModel});
             readOnlyView.on("removeTodo", this.removeTodo, this);
             this.$(".todos").append(readOnlyView.render().el);
          },

          removeTodo: function(todo){
             this.collection.remove(todo);
          },

          clearAllTodos:  function(){
             this.$(".todos").empty();
             var self = this;
             this.collection.removeAll().done(function(){
                 self.collection.reset([]);
             });
          },

          cancelTodo: function(){
             this.$(".todo-draft").empty();
             this.enableButtons();
          },

          disableButtons: function(){
             this.$("#add").prop("disabled", true);
             this.$("#clearAll").prop("disabled", true);
          },

          enableButtons: function(){
             this.$("#add").prop("disabled", false);
             this.$("#clearAll").prop("disabled", false);
          },

          render: function(){
             this.$el.html(htmlTemplate);
             var self = this;
             this.collection.fetch().done(function(collection){
                _.each(collection, function(todo){
                   var model = new Todo(todo);
                   self.addReadOnlyView(model);
                });
             });
             return this;
          }
      });
});
