<template>
  <div>
    <div id="follow-wrap">
        <router-link :to="`/follow/${nickname}/follower`" class="follow">
          팔로워 {{ followerCnt }}
        </router-link>
        <router-link :to="`/follow/${nickname}/following`" class="follow">
          팔로잉 {{ followingCnt }}
        </router-link>
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
  height: 30px;
  margin: 30px 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-weight: bold;
}

#follow-wrap a.router-link-exact-active {
  border-bottom: #6cb9a2 solid;
}

.follow {
  width: 90%;
  margin-left: 1%;
  margin-right: 1%;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: black;
  text-align: center;
}


</style>