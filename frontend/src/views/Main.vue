<template>
  <div>
    <!--환영인사-->
    <div v-if="this.isLoggedIn" class="main-greeting">
      <div class="nickname">
        <span>{{loginUser.nickname}}</span>
        <p>&nbsp;님,</p>
      </div>
      <div class="comment">
        <span>오늘도 도전할 준비 되셨나요?</span>
      </div>
    </div>

    <div v-else class="main-greeting">
        <div class="not-logged-in">
          <span>도</span>전과
          <span>나</span>눔을
          위해서는
        </div>
        <div class="not-logged-in">
          <router-link to="/login">
            <span>로그인</span>
          </router-link>
          이 필요합니다
        </div>
    </div>
    <!--환영인사-->



    <div class="main-contents">
      <div v-if="this.isLoggedIn" class="on-going-quests" @click="$router.push('/quests/mine')">
        진행 중인 퀘스트:&nbsp;&nbsp;{{loginUser.questCnt}}&nbsp;개
        <span class="material-icons-outlined">chevron_right</span>
      </div>

      <div v-else class="on-going-quests">
        <button @click="$router.push('/quests')">전체 퀘스트:&nbsp;&nbsp;{{questInfo.questCnt}}&nbsp;개</button>
        <button @click="$router.push('/quests')" class="material-icons-outlined">chevron_right</button>
      </div>


      <div class="mileage">
        저금통
        <div class="progress-bar">
          <div class="percent" style="width: 75%">
            <div class="change"></div>
          </div>
        </div>
        지난 분기 기부내역
        <div class="charity-history"></div>
      </div>

      <QuestType title="공동" :quests="questInfo.questG" type="G"/>
      <QuestType title="개인" :quests="questInfo.questP" type="P"/>
      <QuestType title="릴레이" :quests="questInfo.questR" type="R"/>

    </div>
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import QuestType from "@/components/quests/QuestType";
import CommonApi from "@/api/CommonApi";

import ('@/assets/style/Main.css')

export default {
  name: "Main",
  // components
  components: {
    QuestType
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      questInfo: state => state.quests.mainQuestInfo
    }),
    ...mapGetters(['isLoggedIn'])
  },
  // watch
  created() {
    CommonApi.requestMainInfo(
        res => {
          let questInfo = (({questCnt, questP, questG, questR}) =>
              ({questCnt, questP, questG, questR}))(res)
          let donationInfo = (({donation, quarter}) =>
              ({donation, quarter}))(res)
          this.$store.dispatch('setMainQuestInfo', questInfo)
          this.$store.dispatch('setMainDonationInfo', donationInfo)
        },
        err => {
          console.log(err)
        }
    )
  }
  // lifecycle hook
  // navigation guard
}
</script>

<style scoped>

</style>