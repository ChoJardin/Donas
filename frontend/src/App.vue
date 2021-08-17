<template>
  <div id="app">

    <div id="app-wrap">


    <navBar id="navBar"/>

    <div id="app-content">
        <router-view/>
    </div>

<!--    <div class="home">-->
<!--        토큰: {{ token }}-->
<!--    </div>-->

    <Footer id="footer"/>
    </div>
    </div>
</template>

<script>
import cookies from "vue-cookies";
// import { token } from '@/services/messaging/messaging.ts';

import navBar from "@/components/common/NavBar";
import Footer from "@/components/common/Footer";

// 브라우저 기본 속성 리셋
import ('./assets/style/reset.css')
// transition
import ('./assets/style/transition.css')

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
#app {
  position: relative;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-family: 'GongGothicMedium';
  /* 행간 조정 */
  line-height: 1.2em;
}

#navBar{
  position: fixed;
  width: 100%;
  height: 60px;
}


#app-content {
  position: fixed;
  top: 60px;
  bottom: 50px;
  background-color: #ffffff;
  width: 100%;
  padding: 0 15px 15px;
  overflow: auto;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  box-shadow: 0 5px 15px -9px #292929;


}

#app-content::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}


#footer {
  height: 50px;
  width: 100vw;
  position: fixed;
  bottom: 0;
  background: #e1eedd;
}

  @media screen and (min-width: 425px) {
    #app-content,
    #footer,
    #navBar {
      max-width: 425px;
      left: 50%;
      transform: translateX(-50%);
    }
}


</style>
