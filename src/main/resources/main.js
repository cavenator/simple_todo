require.config({
   paths: {
     "jquery"      : "https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min",
     "underscore"  : "lib/underscore",
     "backbone"    : "lib/backbone",
     "text"        : "lib/plugins/text"
   }
});

require(['jquery','underscore', 'router','text'], function($, _, Router){
   new Router()
   Backbone.history.start({pushState:true});
});
