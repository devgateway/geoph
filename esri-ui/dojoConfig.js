var locationPath = location.pathname.replace(/\/[^\/]+$/, '');

window.dojoConfig = {
  async: true,
  parseOnLoad: true,
  cacheBust: false,
  deps: ['app/main'],

  packages: [

  {
    name: 'app',
    location: locationPath + 'app',
    main: 'main'
  },

  {
    name: 'react',
    location: locationPath + 'lib/react',
    main: 'react'
  }, 
  
  {
    name: 'react-dom',
    location: locationPath + 'lib/react',
    main: 'react-dom'
  }, 
  
  {
    name: 'react-modal',
    location: locationPath + 'lib/react-modal/dist',
    main: 'react-modal'
  }, 
  
  {
    name: 'react-bootstrap',
    location: locationPath + 'lib/react-bootstrap',
    main: 'react-bootstrap'
  }, 
  
  {
    name: 'react-router',
    location:locationPath + 'lib/react-router',
    main: 'index'
  },

  {
    name: 'redux',
    location:locationPath + 'lib/redux',
    main: 'index'
  },

  {
    name: 'react-redux',
    location:locationPath + 'lib/react-redux',
    main: 'index'
  },

  {
    name: 'redux-thunk',
    location:locationPath + 'lib/redux-thunk',
    main: 'index'
  },

  {
    name: 'i18next',
    location: locationPath + 'lib/i18next/bin',
    main: 'index'
  },

  {
    name: 'i18next-xhr-backend',
    location: locationPath + 'lib/i18next-xhr-backend/bin',
    main: 'index'
  },

  {
    name: 'axios',
    location: locationPath + 'lib/axios/dist',
    main: 'axios'
  },

  {
    name: 'babel-polyfill',
    location: locationPath + 'lib/babel-polyfill',
    main: 'browser-polyfill'
  },

  {
    name: 'es6-promise',
    location: locationPath + 'lib/es6-promise',
    main: 'es6-promise.min'
  },


  {
    name: 'react-redux-router',
    location: locationPath + 'ext/react-redux-router',
    main: 'index'
  },

  ]



};
