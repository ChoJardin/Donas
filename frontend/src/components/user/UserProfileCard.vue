<template>
  <div id="follow-user-profile" :class="{background: isColored}">
    <router-link :to="`/user/profile/${profile.nickname}`" id="follow-profile-wrap">
        <img v-if="profile.picture" class="profile-image card" :src="profile.picture" alt="">
        <img v-else class="profile-image card" src="@/assets/donut_profile.png" alt="">
      <div id="follow-profile-nickname">{{profile.nickname}}</div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "FollowUserProfile",
  props: {
    profile: Object
  },
  computed: {
    isColored() {
      return !(this.$vnode.key % 2)
    }
  }
}
</script>

<style scoped>
#follow-user-profile {
  padding: 5px;
  margin-bottom: 5px;
  border-bottom: 1px solid #e4e6e9;
}

#follow-profile-wrap {
  display: flex;
  text-decoration: none;
}

#follow-profile-nickname {
  align-self: center;
  margin-left: 10px;
  font-size: 0.9em;
  color: #183a1d;
}

.profile-image.card {
  width: 50px;
  height: 50px;
}
</style>