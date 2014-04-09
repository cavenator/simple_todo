define(['jquery','underscore','backbone','models/Todo'], function($,_,Backbone,Todo){
   return Backbone.Collection.extend({
      model: Todo,
      url:  "/todo",
      removeAll: function(){
         var self = this;
         $.ajax({
            url: self.url,
            type: "DELETE"
         });
      }
   });
});
