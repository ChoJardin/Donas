<template>
  <div class="overlay">
    <componentNav
        @on-arrow="$emit('on-click')"
        title="좋아요"/>

    <LikeUserProfile class="follow-profile"
        v-for="(likeUser, idx) in likeList" :key="idx"
        :profile="likeUser"/>

  </div>
</template>

<script>
import ComponentNav from "../common/ComponentNav";
import LikeUserProfile from "@/components/user/UserProfileCard";
import {mapState} from "vuex";

export default {
  name: "HeartList",
  // components
  components: {
    ComponentNav,
    LikeUserProfile,
  },
  // props
  // data
  data() {
    return {
    }
  },
  // methods
  methods: {
  },
  // computed
  computed: {
    ...mapState({
      likeList: state => state.articles.likeList
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>

#profile-edit-password {
  margin-top: 25px;
  width: 90%;
  margin-left: 5%;
}

</style>