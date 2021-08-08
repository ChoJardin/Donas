<template>
  <div>
    <nav id="nav">

      <div id="logo">
        <router-link to="/main">
          <img src="@/assets/logo5-2.png"  alt=""/>
        </router-link>
      </div>

      <div v-if="!isLoggedIn" class="group-right" style="float: right">
        <div class="search">
          <router-link to="/search"><i class="material-icons" style="font-size: 30px">search</i></router-link>
        </div>
        <button class="button1" style="float:right"><router-link :to="{name: 'Login', params: {history: $route.fullPath}}">Login</router-link></button>
      </div>

      <div v-else class="group-right" style="float: right">
        <div class="search">
          <router-link to="/search"><i class="material-icons" style="font-size: 30px">search</i></router-link>
        </div>
        <div class="notification">
          <router-link :to="`/notification/${nickname}`"><span><i class="material-icons" style="font-size: 30px">notifications_none</i></span>
          <span class="badge"><i class="fas fa-circle fa-xs"></i></span></router-link>
        </div>
        <button class="button1 b-text" type="button" @click="onLogout">Logout</button>
      </div>

    </nav>
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";

export default {
  name: "navBar",
  // components
  // props
  // data
  // methods
  methods: {
    onLogout() {
      this.$store.dispatch('logout')
      this.$router.push('/main')
    },
  },
  // computed
  computed: {
    ...mapGetters(['isLoggedIn']),
    ...mapState({
      nickname: state => state.user.loginUser.nickname,
    })
  },
  // watch
  // lifecycle hook

}
</script>

<style scoped>
/* nav-bar */
#nav {
  background-color: white;

  top: 0;
  /*왼쪽은 패딩 안 줘도 될 것 같기도 하고..*/
  padding-left: 10px;
  padding-right: 10px;
  position: fixed;
  width: 100vw;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 10px;
}

#logo {
  margin-top: 5px;
}

#logo img {
  height: 40px;
}

.button1 {
  color: black;
  font-family: 'GongGothicLight';
  /*color: white;*/
  padding: 3px;
  height: 28px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 0.8em;
  margin: 10px 1px;
  cursor: pointer;
  transition-duration: 0.4s;
  border: 2px #f0a04b solid;
  border-radius: 10px;
  /*display: inline-block;*/
}

.button1 a {
  color: black;
  text-decoration: none;
  font-size: small;
}

.group-right {
  display: flex;
  align-content: center;
  margin: 5px 1px;

}
.group-right a{
  color:black;
  margin: 5px 1px;

}
.search a{
  display: inline-block;
  padding-right: 5px;
  padding-top: 5px;
  text-decoration: none !important;


}

.notification {
  display: inline-block;
  position: relative;
  text-decoration-color: transparent;
  float: right;
  padding-right: 5px;
  padding-top: 5px;
  margin: 4px 1px;

}
.notification .badge {
  position: absolute;
  top: 7px;
  right: 8px;
  color: red;
}

/*.button1.b-text {*/
/*  color: black;*/
/*}*/
/* nav-bar end*/
</style>