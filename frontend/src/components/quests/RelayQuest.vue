<template>
  <div>
      <QuestSingle class="quest-single" v-for="n in 10" :key="n" @click.native=setQuestId(n) />
  </div>
</template>

<script>
import QuestSingle from "@/components/quests/QuestSingle";
import {mapActions} from "vuex";

export default {
  name: "RelayQuest",
  // components
  components: {
    QuestSingle
  },
  // props
  // data
  // methods
  methods:{
    ...mapActions(["setQuestId"])
  }
  // computed
}
</script>

<style scoped>

</style>