<template>
  <div>
    <nav id="nav">

      <div id="logo">
        <router-link to="/">
          <img src="@/assets/logo3.png"  alt=""/>
        </router-link>
      </div>

      <div v-if="!isLoggedIn" class="groupr-right" style="float: right">
        <div class="search">
          <router-link to="/search"><v-icon large style="color: black" class="search">search</v-icon></router-link>
        </div>
        <button class="button1" style="float:right"><router-link to="/login">Login</router-link></button>
        <button class="button1" style="float:right"><router-link to="/signup">Signup</router-link></button>
      </div>

      <div v-else class="groupr-right" style="float: right">
        <div class="search">
          <router-link to="/search">
            <v-icon large style="color: black" class="search">search</v-icon>
          </router-link>
        </div>
        <div class="notification">
          <router-link :to="`/notification/${nickname}`"><span><i class="fas fa-bell fa-2x" style="color: #6cb9a2"></i></span>
          <span class="badge"><i class="fas fa-circle fa-sm"></i></span></router-link>
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
      // if(this.$route.path!=='/') this.$router.push({name: 'Main'})
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
  padding-left: 5px;
  padding-right: 10px;
  position: fixed;
  width: 100vw;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
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
  /*display: inline-block;*/
}

.button1 a {
  color: black;
  text-decoration: none;
  font-size: small;
}

.groupr-right {
  display: flex;
  align-content: center;
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

}
.notification .badge {
  position: absolute;
  top: 1px;
  right: 2px;
  color: red;
}

/*.button1.b-text {*/
/*  color: black;*/
/*}*/
/* nav-bar end*/
</style>