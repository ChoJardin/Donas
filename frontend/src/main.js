import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import vuetify from './plugins/vuetify'
import VueKakaoSdkPlugin from "vue-kakao-sdk";

import './registerServiceWorker'


Vue.config.productionTip = false

Vue.use(VueCookies)

const apiKey = "0b36cc6a3820101f75154d47b7851902"
Vue.use(VueKakaoSdkPlugin, {apiKey})


new Vue({
  router,
  store,
  vuetify,
  // firebase,
  render: h => h(App)
}).$mount('#app')
