<template>
  <div>
    <!--환영인사-->
    <div v-if="this.isLoggedIn" class="main-greeting">
      <div class="nickname">
        <span>{{loginUser.nickname}}</span>
        <p>&nbsp;님,</p>
      </div>
      <div class="comment">
        <span>오늘도 준비 되셨나요?</span>
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
      <div v-if="this.isLoggedIn" class="on-going-quests">
        진행 중인 퀘스트:&nbsp;&nbsp;{{loginUser.questCnt}}&nbsp;개
        <span class="material-icons-outlined">chevron_right</span>
      </div>

      <div v-else class="on-going-quests">
        전체 퀘스트:&nbsp;&nbsp;[전체 퀘스트 수] &nbsp;개
        <span class="material-icons-outlined">chevron_right</span>
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

      <QuestType title="공동"></QuestType>
      <QuestType title="개인"></QuestType>
      <QuestType title="릴레이"></QuestType>

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
      loginUser: state => state.user.loginUser
    }),
    ...mapGetters(['isLoggedIn'])
  },
  // watch
  created() {
    CommonApi.requestMainInfo(
        res => {
          console.log(res)
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