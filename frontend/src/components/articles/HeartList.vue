<template>
  <div class="overlay">
    <div id="heart-list">

    <componentNav
        @on-arrow="$emit('on-click')"
        title="좋아요"/>

    <LikeUserProfile class="follow-profile"
        v-for="(likeUser, idx) in likeList" :key="idx"
        :profile="likeUser"/>

    </div>
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
#heart-list {
  position: absolute;
  top: 60px;
  width: 100%;
}

#profile-edit-password {
  margin-top: 25px;
  width: 90%;
  margin-left: 5%;
}

</style>