require(['jquery','underscore', 'backbone','routers/router'], function($, _, Backbone, Router){
   new Router()
   Backbone.history.start({pushState:true});
});
