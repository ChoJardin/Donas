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
        <div class="notification" >
          <router-link :to="{name:'QuestAlert', params:{nickname:nickname, id:id}}"><span><i class="material-icons" style="font-size: 30px">notifications_none</i></span>
          <span class="badge" ><i  class="fas fa-circle fa-xs" v-if="newQuestAlarm || newCommonAlarm"></i></span></router-link>
        </div>
        <button class="button1 b-text" type="button" @click="onLogout">Logout</button>
      </div>

    </nav>
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import UserApi from "@/api/UserApi";
import cookies from "vue-cookies";

export default {
  name: "navBar",
  // components
  // props
  // data
  // methods
  methods: {
    onLogout() {
      const data = {
        userId: this.id,
        token: cookies.get('alarm-token')
      }
      UserApi.requestLogout(
          data,
          res => {
            this.$store.dispatch('logout')
            this.$router.push('/main')
          },
          err => this.$router.push('/error')
      )
    },
  },
  // computed
  computed: {
    ...mapGetters(['isLoggedIn',]),
    ...mapState({
      nickname: state => state.user.loginUser.nickname,
      id: state => state.user.loginUser.id,
      questAlarms: state => state.user.questAlarms,
      commonAlarms: state => state.user.commonAlarms,
    }),
   newQuestAlarm(){
      return this.questAlarms.some(function(element){ if(element.confirm === 0) {return true}})
      },
    newCommonAlarm(){
      return this.commonAlarms.some(function(element){ if(element.confirm === 1) {return true}})
      },
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
  padding-left: 10px;
  padding-right: 10px;
  position: fixed;
  width: 100%;
  max-width: 425px;
  /*width: 100vw;*/
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 5px;
  border-bottom: 1px solid #e4e6e9;
  box-shadow: 0 -5px 15px -8px #292929;
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
  padding: 2px 3px;
  height: 28px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 0.8em;
  margin: 10px 1px;
  cursor: pointer;
  transition-duration: 0.4s;
  border: 2px #f0a04b solid;
  border-radius: 8px;
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
  top: 5px;
  right: 5px;
  color: red;
}

/*.button1.b-text {*/
/*  color: black;*/
/*}*/
/* nav-bar end*/
</style>