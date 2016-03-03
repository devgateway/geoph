var gulp = require('gulp');
var babel = require('gulp-babel');
var express = require('express'),
path = require('path'),
app = express();

gulp.task('react', function () {
  return gulp.src([
    'src/*.jsx', 'src/**/*.jsx', 'src/**/**/*.jsx',
    'src/*.js', 'src/**/*.js', 'src/**/**/*.js'
  ])
  .pipe(babel({
    sourceMaps: 'inline',
    presets: ['es2015', 'react','stage-0','stage-1','stage-2','stage-3'],
    plugins: ['transform-es2015-modules-amd']
  }))
  .pipe(gulp.dest('dist'));
});

gulp.task('watch',["server"], function(){
  gulp.watch([
    'src/*.jsx', 'src/**/*.jsx', 'src/**/**/*.jsx',
    'src/*.js', 'src/**/*.js', 'src/**/**/*.js'
  ], ['react']);
});


/**
 * Start dev web server
 */
 gulp.task("server", function(callback) {

  var publicPath = path.resolve(__dirname);
  app.use(express.static(publicPath));

  app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname , 'index.html'));
  });

  app.listen(8085, 'localhost', function(err) {
    if (err) {
      callback(err);
    }
    console.log('Listening at http://localhost:8085');
    callback();
  });

});


gulp.task('default', ['react']);
