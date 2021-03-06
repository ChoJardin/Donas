import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import vuetify from './plugins/vuetify'

import './registerServiceWorker'


Vue.config.productionTip = false

Vue.use(VueCookies)


new Vue({
  router,
  store,
  vuetify,
  // firebase,
  render: h => h(App)
}).$mount('#app')
