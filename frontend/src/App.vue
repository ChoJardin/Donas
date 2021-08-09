<template>
  <div id="app">
    <navBar id="navBar"/>

    <div id="app-content">
        <router-view/>
    </div>

<!--    <div class="home">-->
<!--        토큰: {{ token }}-->
<!--    </div>-->

    <Footer id="footer"/>
  </div>
</template>

<script>
import cookies from "vue-cookies";
// import { token } from '@/services/messaging/messaging.ts';

import navBar from "@/components/common/NavBar";
import Footer from "@/components/common/Footer";

// 브라우저 기본 속성 리셋
import ('./assets/style/reset.css')

export default {
  name: 'App',
  // components
  components: {
    navBar,
    Footer
  },
  // setup() {
  //       return {
  //           token,
  //       };
  //   },
  // props
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
  created() {
    let user = cookies.get('login-user')
    if (user)
      this.$store.dispatch('setLoginUser', user)
  }
}

  // components
  // props
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
  // navigation guard
</script>

<style>
#navBar{
  position: fixed;
}

#app {
  /*font-family: Avenir, Helvetica, Arial, sans-serif;*/
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-family: 'GongGothicMedium';
  /* 행간 조정 */
  line-height: 1.2em;
}

#app-content {
  position: absolute;
  top: 60px;
  bottom: 50px;
  width: 100%;
  padding: 0 15px;
  overflow: auto;
}

#footer {
  height: 50px;
  width: 100vw;
  position: fixed;
  bottom: 0;
  background: #e1eedd;
}

</style>
