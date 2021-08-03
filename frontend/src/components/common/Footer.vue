<template>
  <footer>
    <hr class="footer-line">
    <div id="flex-container">
      <router-link to="/"><i class="material-icons" style="font-size: 36px">home</i></router-link>
      <router-link to="/quests"><i class="material-icons" style="font-size: 36px">sports_score</i></router-link>
      <router-link to="/feed"><i class="material-icons" style="font-size: 36px">menu</i></router-link>
      <router-link to="/user/mileage"><i class="material-icons" style="font-size: 36px">savings</i></router-link>
      <router-link :to="`/user/profile/${nickname}`"><i class="material-icons" style="font-size: 36px">person</i></router-link>
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

.footer-line{
  opacity: 60%;
}

#flex-container {
  height: 100%;
  display: flex;
  align-items: center;

  }

#flex-container a {
  flex: auto;
  width: 20%;
  padding: 7px;
  color: #da8a11;
  text-align: center;
  font-size: small;
  text-decoration: none;
}

/*#flex-container a:hover{*/
/*  color: #6cb9a2;*/
/*  background: white;*/
/*  opacity: 0.6;*/
/*}*/

#flex-container a.router-link-exact-active {
  color: white ;
  /*color: #9d794a;*/
  background: #da8a11;
  /*margin: 0 0px 2px 0px;*/
  border-radius: 25%;
  padding: 1px;

}


</style>