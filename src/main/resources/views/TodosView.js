define(['jquery','underscore','backbone','text!views/TodosView.html'],
   function($,_,Backbone, htmlTemplate){
      return Backbone.View.extend({
          initialize: function(){
             return this;
          },
          render: function(){
             this.$el.html(htmlTemplate);
             return this;
          }
      });
});
