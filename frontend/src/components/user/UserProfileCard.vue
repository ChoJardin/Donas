<template>
  <div  @click="onClick" id="follow-user-profile" :class="{background: isColored}">
    <div id="follow-profile-wrap">
        <img v-if="profile.picture" class="profile-image card" :src="profile.picture" alt="">
        <img v-else class="profile-image card" src="@/assets/donut_profile.png" alt="">
      <div id="follow-profile-nickname">{{profile.nickname}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FollowUserProfile",
  props: {
    profile: {
        type: Object
    },
    click: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    onClick() {
      if (this.click)
        this.$emit('on-click')
      else
        this.$router.push(`/user/profile/${this.profile.nickname}`)
    }
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