define(['jquery','underscore','backbone','models/Todo','text!views/Todo.html'], function($,_,Backbone,TodoModel, htmlTemplate){

   return Backbone.View.extend({
      initialize: function(){
         this.model = new TodoModel();
      },

      events: {
         "click #saveTodo": "saveTodo",
         "click #cancelTodo": "cancelTodo"
      },

      saveTodo: function(e){
         e.preventDefault();
         var title = this.$(".todo-title").val();
         var description= this.$(".todo-description").val();
         var self = this;
         this.model.set({title: title, description: description});
         this.model.save({title: title, description: description},{async: false}).done(function(){ 
             self.trigger('todoSaved', self.model);
         });
      },

      cancelTodo: function(e){
         e.preventDefault();
         this.trigger("cancelTodo");
      },

      render: function(){
         var template = _.template(htmlTemplate, {todo: this.model.toJSON()});
         this.$el.html(template);
         return this;
      }
   });
});
