define(['jquery','underscore','backbone','../views/TodosContainerView'], function($,_,Backbone, TodosContainerView){

    return Backbone.Router.extend({
      initialize: function(){
          var htmlIntro = "<h1 style='text-align:center'>My Todos List</h1><div id='todos-pane'></div>";
          this.mainContent = $("#mainContent");
          this.mainContent.html(htmlIntro);
      },

      routes: {
          "":  "loadToDos"
      },

      loadToDos: function(){
         var todosContainerView = new TodosContainerView();
         var todosEl = $("#todos-pane");
         todosContainerView.setElement(todosEl).render().el;
      }
      
   });
});
