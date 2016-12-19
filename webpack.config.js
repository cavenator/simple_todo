var path = require("path");

// webpack.config.js
module.exports = {
  entry: './src/main/resources/main.js',
  output: {
    filename: 'bundle.js',
    path: './src/main/resources'
  },
  modules: {
     rules: [{ test: /text\!/, loader: 'text-loader' }]
  },
  resolve: {
     alias: {
	     "jquery"      : "lib/jquery.min",
	     "underscore"  : "lib/underscore",
	     "backbone"    : "lib/backbone"
     },
     modules: [path.resolve(__dirname, "src/main/resources"), "node_modules"]
  }};
