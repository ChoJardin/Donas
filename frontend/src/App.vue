<template>
  <div id="app">
      <nav id="nav">
        <div id="logo" style="float:left"><router-link to="/"><img src="@/assets/logo3.png" height="50" alt=""></router-link></div>
        <div id="options">
          <button class="button1" style="float:right"><router-link to="/login" >Login</router-link></button>
          <button class="button1" style="float:right"><router-link to="/signup" >Signup</router-link></button>
        </div>
      </nav>
<!--      <div id="nav">-->
<!--        <router-link to="/">Main</router-link> |-->
<!--        <router-link to="/login">Login</router-link> |-->
<!--        <router-link to="/signup">Signup</router-link>-->
<!--      </div>-->

<!--    <v-app-bar id="nav" dense color="white">-->
<!--      <v-spacer></v-spacer>-->

<!--      <v-btn icon><v-icon>mdi-heart</v-icon></v-btn>-->
<!--      <v-btn icon><v-icon>mdi-magnify</v-icon></v-btn>-->
<!--    </v-app-bar>-->
  <div id="content">
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
#nav {
  top: 0;
  position: fixed;
  width: 100vw;
  height: 50px;

}

#content {
  margin-top: 60px;
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
}



#options {
  text-decoration: none;
  margin-right: 2px;
}

#logo {
  /*padding-left: 10px;*/
}


.button1 {
  color: white;
  padding: 7px 7px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 1px;
  cursor: pointer;
  transition-duration: 0.4s;
  font-weight: bold;
  border: rosybrown solid;
  border-radius: 10px;
}

.button1 a {
  color: black;
  text-decoration: none;
  font-size: small;

}



</style>
