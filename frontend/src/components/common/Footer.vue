<template>
  <footer>
    <div id="flex-container">
      <router-link to="/"><i class="fas fa-home fa-2x"></i><br><div>메인</div></router-link>
      <router-link to="/quests"><i class="fas fa-dumbbell fa-2x"></i><br><div>퀘스트</div></router-link>
      <router-link to="/feed"><i class="fas fa-bars fa-2x"></i><br><div>피드</div></router-link>
      <router-link to="/user/mileage"><i class="fas fa-coins fa-2x"></i><br><div>마일리지</div></router-link>
      <router-link :to="`/user/profile/${nickname}`"><i class="fas fa-user-alt fa-2x"></i><br><div>프로필</div></router-link>
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
  }

#flex-container a {
  flex: auto;
  width: 20%;
  padding-top: 7px;
  color: #6cb9a2;
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
  background: #6cb9a2;
  /*margin: 0 0px 2px 0px;*/
  border-radius: 15px ;
}

</style>