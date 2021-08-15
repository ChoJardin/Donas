<template>
  <div>
    <componentNav title="팔로우" @on-arrow="$router.push(`/user/profile/${nickname}`)" />

    <div id="follow-wrap">
        <router-link :to="`/follow/${nickname}/follower`" class="follow">
          팔로워&nbsp;&nbsp;{{ followerCnt }}
        </router-link>
        <router-link :to="`/follow/${nickname}/following`" class="follow">
          팔로잉&nbsp;&nbsp;{{ followingCnt }}
        </router-link>
    </div>

    <router-view/>
  </div>
</template>

<script>
import {mapState} from "vuex";
import ComponentNav from "../../components/common/ComponentNav";

export default {
  name: "Follow",
  // components
  components: {
    ComponentNav
  },
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
  margin: 70px 0 10px 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-family: GongGothicBold;
}

#follow-wrap a.router-link-exact-active {
  border-bottom: #f0a04b solid;
}

.follow {
  width: 90%;
  margin-left: 1%;
  margin-right: 1%;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: #183a1d;
  text-align: center;
}


</style>