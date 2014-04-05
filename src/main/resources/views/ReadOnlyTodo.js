define(['jquery','underscore','backbone','text!views/ReadOnlyTodo.html'], function($,_,Backbone,htmlTemplate){
   return Backbone.View.extend({
      className: "readonly-todo",

      initialize: function(){},

      events: {
         "click .dismiss": "removeTodo"
      },

      removeTodo: function(){
         this.model.destroy({wait: true});
         this.remove();
         this.trigger("removeTodo", this.model);
      },

      render: function(){
         var htmlString = _.template(htmlTemplate, {todo: this.model.toJSON()});
         this.$el.html(htmlString);
         return this;
      }
   });
});
