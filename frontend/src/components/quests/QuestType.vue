<template>
  <div>
    <div class="on-going-quests">
      {{title}} 퀘스트
      <span class="material-icons-outlined">chevron_right</span>
    </div>

    <div class="quest-card-wrap">
      <QuestCard @click.native='setQuestId(quest.id)' v-for="quest in quests" :key="quest.id" :quest="quest"></QuestCard>
    </div>





  </div>
</template>

<script>
import QuestCard from "@/components/quests/QuestCard";
import {mapActions} from "vuex";

export default {
  name: "QuestType",
  // components
  components: {
    QuestCard
  },
  // props
  props: {
    title: String,
    quests: Array
  },
  // data
  // methods
  methods: {
    ...mapActions(["setQuestId"]),
  },
  // computed
  // watch
  // lifecycle hook
  // navigation guard
}
</script>

<style scoped>
.quest-card-wrap {
  display: flex;
  overflow-x: scroll;
  /* 스크롤바 숨기기 */
  -ms-overflow-style: none;
  scrollbar-width: none;
}

/* 스크롤바 숨기기 */
.quest-card-wrap::-webkit-scrollbar {
  display: none;
}
</style>