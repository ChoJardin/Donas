<template>
  <div>
    현재 진행 중인 퀘스트
    {{onGoing.type}}
    {{onGoing.title}}
  </div>
</template>

<script>
import {mapState} from "vuex";
import moment from "moment";

export default {
  name: "OnGoingQuests",
  computed: {
    ...mapState({
      quests: state => state.user.selectedProfile.quests
    }),
    onGoing() {
      return this.quests.find(quest => {
        const start = moment(quest.startAt)
        const finish = moment(quest.finishAt)
        return moment().isBetween(start, finish)
      })
    }
  }
}
</script>

<style scoped>

</style>