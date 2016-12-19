var path = require("path");
var resourceDir = process.cwd() + "/src/main/resources";
var jquery_path = resourceDir+"/lib/jquery.min";
var underscore_path = resourceDir+"/lib/underscore";
var backbone_path = resourceDir + "/lib/backbone";
var text_plugin_path = resourceDir + "/lib/plugins/text";
var routers_path = resourceDir + "/routers";
var views_path = resourceDir + "/views";
var models_path = resourceDir + "/models";
var appPath = path.join(path.resolve('src/main/resources'));

// webpack.config.js
module.exports = {
  entry: './src/main/resources/main.js',
  output: {
    filename: 'bundle.js',
    path: './src/main/resources'
  },
  modules: {
     loaders: [{ test: /text\!/, loader: 'text-loader' }]
  },
  resolve: {
     alias: {
	     "jquery"      : jquery_path,
	     "underscore"  : underscore_path,
	     "backbone"    : backbone_path,
             "routers"     : routers_path,
             "views"       : views_path,
             "models"      : models_path
     }
  }};
