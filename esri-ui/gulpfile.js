var gulp = require('gulp');
var babel = require('gulp-babel');
var express = require('express'),
  mainBowerFiles = require('main-bower-files');
var less =require('gulp-less');

path = require('path'),
ghPages = require('gulp-gh-pages'),
app = express();
var clean = require('gulp-clean');
var sass = require('gulp-sass');


gulp.task('bower',['clean'], function() {
  return gulp.src(mainBowerFiles(), {
      base: 'bower_components'
    }).pipe(gulp.dest('dist/lib'));
});


gulp.task('sass',['clean'], function () {
  return gulp.src('./scss/**/*.scss')
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest('dist/css'));
});


gulp.task('ext',['clean'], function() {
  return gulp.src([
      'ext/*.jsx', 'ext/**/*.jsx', 'ext/**/**/*.jsx',
      ' /*.js', 'ext/**/*.js', 'ext/**/**/*.js',
      'ext/*.es6', 'ext/**/*.es6', 'ext/**/**/*.es6'
    ])
    .pipe(babel({
      sourceMaps: 'inline',
      presets: ['es2015', 'react', 'stage-0', 'stage-1', 'stage-2', 'stage-3'],
      plugins: ['transform-es2015-modules-amd']
    }))
    .pipe(gulp.dest('dist/ext'));
});



gulp.task('react',['clean'], function() {
  return gulp.src([
      'src/*.jsx', 'src/**/*.jsx', 'src/**/**/*.jsx',
      'src/*.js', 'src/**/*.js', 'src/**/**/*.js',
      'src/*.es6', 'src/**/*.es6', 'src/**/**/*.es6'
    ])
    .pipe(babel({
      sourceMaps: 'inline',
      presets: ['es2015', 'react', 'stage-0', 'stage-1', 'stage-2', 'stage-3'],
      plugins: ['transform-es2015-modules-amd']
    }))
    .pipe(gulp.dest('dist/app'));
});



gulp.task('build', ['clean','react', 'ext', 'bower','sass','copy'], function() {
   console.log("all done")
  
});


gulp.task('watch', ['clean','build','server'], function() {
 return gulp.watch([
    './index.html', '*.js', 'scss/*.*',
    'src/*.jsx', 'src/**/*.jsx', 'src/**/**/*.jsx',
    'src/*.js', 'src/**/*.js', 'src/**/**/*.js'
  ], ['build']);
});


/**
 * Publish dist folder into gh_pages branch
 */
gulp.task('deploy', function() {
  return gulp.src('./dist/**/*')
    .pipe(ghPages());
});


/**
 * Start dev web server
 */
gulp.task('server', function(callback) {

  var publicPath = path.join(path.resolve(__dirname), 'dist');
  console.log(publicPath);
  app.use(express.static(publicPath));

  app.get('/', function(req, res) {
    res.sendFile(path.join(publicPath, 'index.html'));
  });

  app.listen(8085, 'localhost', function(err) {
    if (err) {
      callback(err);
    }
    console.log('Listening at http://localhost:8085');
    callback();
  });

});


gulp.task('copy',['clean'], function(callback) {
  return gulp.src([
    './index.html', './dojoConfig.js', 'conf/settings.json', '*locales/**/*'
  ]).pipe(gulp.dest('dist'))
});


gulp.task('clean', function() {
  return gulp.src([
    'dist'
  ]).pipe(clean())

});



gulp.task('default', ['watch']);