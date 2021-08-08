<template>
  <div>
    <div class="main-greeting">
      <div class="nickname">
        <span>{{loginUser.nickname}}</span>
        <p>&nbsp;님,</p>
      </div>
      <div class="comment">
        <span>오늘도 준비 되셨나요?</span>
      </div>
    </div>

    <div class="main-contents">
      <div class="on-going-quests">
        진행 중인 퀘스트&nbsp;&nbsp;{{loginUser.questCnt}}
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
import {mapState} from "vuex";
import QuestType from "@/components/quests/QuestType";

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
    })
  },
  // watch
  // lifecycle hook
  // navigation guard
}
</script>

<style scoped>

</style>