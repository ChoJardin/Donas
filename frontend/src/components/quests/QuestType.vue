<template>
  <div>
    <div class="on-going-quests">
      {{title}} 퀘스트
      <span class="material-icons-outlined">chevron_right</span>
    </div>

    <div class="quest-card-wrap">
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>
      <QuestCard></QuestCard>

    </div>





  </div>
</template>

<script>
import QuestCard from "@/components/quests/QuestCard";

export default {
  name: "QuestType",
  // components
  components: {
    QuestCard
  },
  // props
  props: {
    title: String,
  },
  // data
  // methods
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