define(['jquery','underscore','backbone','text!views/TodoContainerView.html','views/Todo','views/ReadOnlyTodo','models/Todo'],
   function($,_,Backbone, htmlTemplate, TodoView, ReadOnlyTodo, Todo){
      return Backbone.View.extend({
          initialize: function(){
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
             this.collection.reset([]);
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
             return this;
          }
      });
});
