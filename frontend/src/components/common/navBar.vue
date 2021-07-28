<template>
  <div>
    <nav id="nav">

      <div id="logo">
        <router-link to="/">
          <img src="@/assets/logo3.png"  alt=""/>
        </router-link>
      </div>

      <div v-if="!isLoggedIn" id="options">
        <button class="button1" style="float:right"><router-link to="/login">Login</router-link></button>
        <button class="button1" style="float:right"><router-link to="/signup">Signup</router-link></button>
      </div>

      <div v-else>
        <button class="button1 b-text" type="button" @click="onLogout">Logout</button>
      </div>

    </nav>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "navBar",
  // components
  // props
  // data
  // methods
  methods: {
    onLogout() {
      this.$store.dispatch('logout')
    }
  },
  // computed
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  // watch
  // lifecycle hook

}
</script>

<style scoped>
/* nav-bar */
#nav {
  /*background-color: olivedrab;*/

  top: 0;
  /*왼쪽은 패딩 안 줘도 될 것 같기도 하고..*/
  padding-left: 5px;
  padding-right: 10px;
  position: fixed;
  width: 100vw;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#options {
  text-decoration: none;
  margin-right: 2px;
}

#logo {
  margin-top: 5px;
}

#logo img {
  height: 50px;
}

.button1 {
  color: black;
  /*color: white;*/
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

/*.button1.b-text {*/
/*  color: black;*/
/*}*/
/* nav-bar end*/
</style>