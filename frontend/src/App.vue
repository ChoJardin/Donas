<template>
  <div id="app">
    <navBar id="navBar"/>

    <div id="app-content">
        <router-view/>
    </div>

    <Footer id="footer"/>
  </div>
</template>

<script>
import cookies from "vue-cookies";

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
#navBar{
  position: fixed;
}

/*footer*/
#footer {
  /*background-color: #9d794a;*/
  height: 50px;
  width: 100vw;
  position: fixed;
  bottom: 0;
  background: #e1eedd;
}
/*footer-end*/


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}


#app-content {
  margin: 70px 10px 50px 10px;
}

</style>
