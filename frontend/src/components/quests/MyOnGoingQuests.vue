<template>
  <div id="my-on-going-quest-compo">
    <div v-if="onGoing" class="my-on-going-quests-wrap">
        <button @click="setQuestId(onGoing.id)" class="to-quest">
          <span v-if="onGoing.type === 'P'" class="quest-type">개인</span>
          <span v-else-if="onGoing.type === 'G'" class="quest-type">공동</span>
          <span v-else-if="onGoing.type === 'R'" class="quest-type">릴레이</span>
          <span>{{onGoing.title}}</span>
        </button>
        <div @click="$router.push('/quests/mine')" class="to-my-quests">
          <span>외 {{questCnt -1}}개 진행중</span>
        <span class="material-icons-round">navigate_next</span>
        </div>
    </div>

    <div v-else @click="$router.push('/quests')" class="nothing-participated">
      <div>
        현재 진행중인 퀘스트가 없어요!
      </div>
      <div class="lets-go">
        퀘스트에 참여하러 가볼까요?
      </div>
    </div>

  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import moment from "moment";

export default {
  name: "MyOnGoingQuests",
  methods: {
    ...mapActions(['setQuestId'])
  },
  computed: {
    ...mapState({
      quests: state => state.user.selectedProfile.quests,
      questCnt: state => state.user.loginUser.questCnt
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
#my-on-going-quest-compo {
  width: 100%;
  height: 100%;
  background-color: #F9DA94;
  /*border: 2px solid #f6c453;*/
  display: flex;
  padding: 10px;
}

.my-on-going-quests-wrap {
  width: 100%;
  display: flex;
  /*flex-direction: column;*/
    align-items: center;
  justify-content: space-evenly;
}

.quest-routers {
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 60%;
}


.quest-type {
    background-color: #f6c453;
    font-size: 0.5em;
    font-family: GongGothicLight;
    padding: 2px;
    margin: 5px;
    border-radius: 5px;
}

.to-my-quests {
  display: flex;
  align-items: center;
  cursor: pointer;
  text-decoration: underline;
}

.nothing-participated {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 10px;
  width: 100%;
  color: #292929;
}
.nothing-participated div {
  align-self: center;
}
.nothing-participated .lets-go {
  text-decoration: underline;
}
</style>