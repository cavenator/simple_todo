define(['backbone'], function(Backbone){
   return Backbone.Model.extend({
       defaults: {
          "title": null,
          "description": null
       },
       urlRoot: function(){
         return "/todo";
       }
   });
});
