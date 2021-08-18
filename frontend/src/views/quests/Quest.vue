<template>
  <div>
      <ComponentNav @on-arrow="$router.back()" title="퀘스트"></ComponentNav>
    <div class="quest-branch">
      <router-link to="/quests" class="quest-button" replace>전체</router-link>
      <router-link to="/quests/solo" class="quest-button" replace>개인</router-link>
      <router-link to="/quests/group" class="quest-button" replace>공동</router-link>
      <router-link to="/quests/relay" class="quest-button" replace>릴레이</router-link>
    </div>

    <router-view id="quest-router-view"/>

    <v-btn id="explain-modal" @click="showModal = !showModal" class="mx-2 text-decoration-none" fab color="indigo" >?</v-btn>


    <MidModal  v-if="showModal" @close="showModal= !showModal">
      <div slot="header" @click="showModal = false" style="width: 100%">퀘스트 설명</div>
      <div slot="opt1">
        <div style="margin-bottom: 5px">개인 퀘스트</div>
        <div> 개별적으로 참여하는 퀘스트입니다. </div>
        <div>명시된 기간내에 자유롭게 참여 가능하며 성공 인증 조건을 충족하면</div>
        <div style="padding-bottom:5px">종료일 이후 성공 보상 100 마일리지가 부여됩니다.</div>
        <hr>
        <div style="margin:10px 0 5px 0">공동 퀘스트</div>
        <div>서로 팔로우를 하는 유저들 중 선택하여</div>
        <div>함께 하는 퀘스트 입니다.</div>
        <div>생성 혹은 초대로만 참여가 가능하며 </div>
        <div>모든 유저가 각각 85%이상 인증률을 달성하면</div>
        <div>종료일 이후 참여자당 성공보상 150 마일리지가 부여됩니다.</div>
        <hr>
        <div style="margin:10px 0 5px 0">릴레이 퀘스트</div>
        <div>먼저 성공 후 원하는 유저를 선택하여 해당 유저가 이어가는 퀘스트 입니다.</div>
        <div>생성 혹은 초대로만 참여 가능하며 지정기간동안 목표인원에 도달하면</div>
        <div>종료일 이후 참여자당 성공보상 200 마일리지가 부여됩니다.</div>
      </div>
    </MidModal>

     <v-btn id="create-button" class="text-decoration-none" fab color="indigo">
      <router-link to="/quests/create"><i class="fas fa-plus fa-lg" style="color: #f19b36"></i></router-link>
    </v-btn>

  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import QuestApi from "../../api/QuestApi";
import MidModal from "../../components/common/MidModal";
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: 'QuestAll',
  components: {
    MidModal,
    ComponentNav
  },
  data() {
    return {
      showModal: false,
    }
  },
  methods:{
    ...mapActions(['setSelectedType'])
  },
  computed: {
        ...mapGetters(['isLoggedIn']),
  },
  created() {
    // console.log('quests fetched')
    QuestApi.requestAllQuest(
        res => {
          // console.log(res)
          this.$store.dispatch('setQuest', res.data)
        },
        err => {
          console.log(err)
        }
    )}
}
</script>

<style scoped>
.quest-branch {
  height: 40px;
  position: fixed;
  top: 110px;
  padding-top: 10px;
  left: 0;
  right: 0;
  margin: 0 auto;
  /*margin: 70px 0 10px 0;*/
  width: 100%;
  max-width: 425px;
  background-color: #ffffff;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-family: GongGothicBold;

}

.quest-branch a.router-link-exact-active {
  border-bottom: #f19b36 solid;
  height: 100%;
}

.quest-button {
  width: 100%;
  margin-left: 2px;
  margin-right: 2px;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: black;
  text-align: center;
  height: 100%;
}

#explain-modal {
  color: #292929;
  text-decoration-color: transparent;
  position: sticky;
  bottom: 90px;
  float: right;
  display: flex;
  align-content: center;
}

#create-button {
  color: #f0a04b;
  text-decoration-color: transparent;
  position: sticky;
  bottom: 20px;
  float: right;
  /*margin-right: 0;*/
  /*right: 0;*/
  display: flex;
  align-content: center;
}

#quest-router-view {
  margin-top: 110px
}
</style>