<template>
  <footer>
<!--    <hr class="footer-line">-->
    <div id="flex-container">
      <router-link to="/main"><i class="material-icons" style="font-size: 30px">home</i></router-link>
      <router-link to="/quests"><i class="material-icons" style="font-size: 30px">sports_score</i></router-link>
      <router-link to="/feed"><i class="material-icons" style="font-size: 30px">menu</i></router-link>
      <router-link to="/user/mileage"><i class="material-icons" style="font-size: 30px">savings</i></router-link>
      <router-link :to="`/user/profile/${nickname}`"><i class="material-icons" style="font-size: 30px">person</i></router-link>
    </div>
  </footer>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "Footer",
  // components
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
        nickname: state => state.user.loginUser.nickname,
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>

#flex-container {
  height: 100%;
  display: flex;
  align-items: center;
  box-shadow: 0 0 20px -9px rgba(0, 0, 0, 0.55);

  }

#flex-container a {
  flex: auto;
  width: 20%;
  padding: 7px;
  color: #a0a0a0;
  text-align: center;
  font-size: small;
  text-decoration: none;
}

/*#flex-container a:hover{*/
/*  color: #6cb9a2;*/
/*  background: white;*/
/*  opacity: 0.6;*/
/*}*/

#flex-container a.router-link-active {
  color: #183a1d ;
  /*color: #9d794a;*/
  /*background: #da8a11;*/
  /*margin: 0 0px 2px 0px;*/
  border-radius: 25%;
  padding: 1px;

}


</style>