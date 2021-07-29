<template>
  <div>
    <div id="follow-wrap">
      <div class="follow">
        <router-link :to="`/follow/${nickname}/follower`">
          팔로워 {{ followerCnt }}
        <div class="under-line"></div>
        </router-link>
      </div>

      <div class="follow">
        <router-link :to="`/follow/${nickname}/following`">
          팔로잉 {{ followingCnt }}
        <div class="under-line"></div>
        </router-link>
      </div>
    </div>

    <router-view/>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Follow",
  // components
  // props
  // data
  data() {
    return {
      // nickname: 'cho'
    }
  },
  // methods
  // computed
  computed: {
    ...mapState({
      followerCnt: state => state.user.selectedProfile.follower,
      followingCnt: state => state.user.selectedProfile.following,
      nickname: state => state.user.selectedProfile.nickname
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>
#follow-wrap {
  margin: 30px 0px;
  width: 100%;
  display: flex;
  flex: 1 1 0;
  justify-content: space-around;
  font-size: 1.1em;
  font-weight: bold;
}

.follow {
  flex: 1 1 0;
  width: 100%;
  /*justify-content: center;*/
}

.follow a {
  text-decoration: none;
  color: #292929;
}

.follow a.router-link-exact-active .under-line{
  border: 1px solid #292929;
  width: 90%;
  margin-left: 5%;
}


</style>