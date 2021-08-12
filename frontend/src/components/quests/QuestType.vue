<template>
  <div>
    <div class="on-going-quests">
      {{title}} 퀘스트
      <span @click="$router.push(`/quests/${questType}`)" class="material-icons-outlined">chevron_right</span>
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
    quests: Array,
    type: String,
  },
  // data
  // methods
  methods: {
    ...mapActions(["setQuestId"]),
  },
  // computed
  computed: {
    questType() {
      if (this.type === 'G') {
        return 'group'
      } else if (this.type === 'P') {
        return 'solo'
      } else return 'relay'
    }
  }
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