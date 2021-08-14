<template>
  <div>
    <ComponentNav
      @on-arrow="$router.back()"
      title="퀘스트 참가자"/>
    <div style="margin-top: 60px">
      <FollowUserProfile
          v-for="(following, idx) in questDetail.users" :key="idx"
          :profile="following" />
<!--    <button  id="participants-card">-->
<!--      <img src="@/assets/IMG_7263.jpeg" alt="">-->
<!--      &lt;!&ndash;<img :src="quest.picture" alt="">&ndash;&gt;-->
<!--    </button>-->
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

import FollowUserProfile from "@/components/user/UserProfileCard";
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: "ParticipantsList",
  components: {
    FollowUserProfile,
    ComponentNav
  },
  computed: {
    ...mapState({
      questDetail: state => state.quests.questDetail,
    })
  }
}
</script>

<style scoped>
/*#participants-card {*/
/*  !*border: 1px solid #183a1d;*!*/
/*  height: 150px;*/
/*  width: 120px;*/
/*  margin: 15px 10px 10px;*/
/*  border-radius: 17px;*/
/*  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);*/
/*}*/

/*img {*/
/*  width: 100%;*/
/*  height: 75%;*/
/*  border-top-left-radius: 17px;*/
/*  border-top-right-radius: 17px;*/
/*  object-fit: cover;*/
/*}*/


</style>