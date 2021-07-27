<template>
  <div id="app">
    <div id="content">
      <div id="nav">
        <router-link to="/">Main</router-link> |
        <router-link to="/login">Login</router-link> |
        <router-link to="/signup">Signup</router-link>
      </div>
      <router-view/>
    </div>

    <Footer id="footer"/>
  </div>
</template>

<script>
import Footer from "@/components/common/Footer";

export default {
  name: 'App',
  // components
  components: {
    Footer
  },
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
</script>

<style>
#content {
  padding-bottom: 50px;
  margin-left: 10px;
  margin-right: 10px;
}

#footer {
  background-color: rosybrown;
  height: 50px;
  width: 100vw;
  position: fixed;
  bottom: 0;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
