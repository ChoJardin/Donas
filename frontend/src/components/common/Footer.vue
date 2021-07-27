<template>
  <footer>
    <div id="flex-container">
      <router-link to="/"><i class="fas fa-home fa-2x"></i><br><div>메인</div></router-link>
      <!--<a href="/"><i class="fas fa-home fa-2x"></i><br><div>메인</div></a>-->
      <router-link to="/quests"><i class="fas fa-dumbbell fa-2x"></i><br><div>퀘스트</div></router-link>
      <!--<a href="/quests"><i class="fas fa-dumbbell fa-2x"></i><br><div>퀘스트</div></a>-->
      <router-link to="/feed"><i class="fas fa-bars fa-2x"></i><br><div>피드</div></router-link>
      <!--<a href="/feed"><i class="fas fa-bars fa-2x"></i><br><div>피드</div></a>-->
      <router-link to="/user/mileage"><i class="fas fa-coins fa-2x"></i><br><div>마일리지</div></router-link>
      <!--<a href="/user/mileage"><i class="fas fa-coins fa-2x"></i><br><div>마일리지</div></a>-->
      <router-link :to="`/user/profile/${nickname}`"><i class="fas fa-user-alt fa-2x"></i><br><div>프로필</div></router-link>
      <!--<a :href="`/user/profile/${nickname}`"><i class="fas fa-user-alt fa-2x"></i><br><div>프로필</div></a>-->
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
        nickname: state => state.user.userNickname,
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
  padding-top: 5px;
  color: aliceblue;
  text-align: center;
  font-size: small;
  text-decoration: none;
}

</style>