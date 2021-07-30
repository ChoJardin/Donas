<template>
  <div id="app">
    <navBar/>

    <div id="content">
        <router-view/>
    </div>

    <Footer id="footer"/>
  </div>
</template>

<script>
import cookies from "vue-cookies";

import navBar from "@/components/common/NavBar";
import Footer from "@/components/common/Footer";

export default {
  name: 'App',
  // components
  components: {
    navBar,
    Footer
  },
  // props
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
  created() {
    let user = cookies.get('login-user')
    if (user)
      this.$store.dispatch('login', user)
  }
}

  // components
  // props
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
</script>

<style>

/*footer*/
#footer {
  background-color: white;
  /*background-color: #9d794a;*/
  height: 50px;
  width: 100vw;
  position: fixed;
  bottom: 0;
}
/*footer-end*/


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}


#content {
  margin-top: 80px;
  margin-left: 10px;
  margin-right: 10px;
}

/*!* 회원가입 중복확인 버튼 *!*/
/*.input-with-button.check input {*/
/*  border-top-right-radius: unset;*/
/*  border-bottom-right-radius: unset;*/
/*}*/

</style>
