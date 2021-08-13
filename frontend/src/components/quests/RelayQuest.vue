<template>
  <div>
    <div v-for="quest in relayQuest" :key="quest.id" >
      <QuestSingle class="quest-single" :quest="quest" @click.native=setQuestId(quest.id) />
    </div>
  </div>
</template>

<script>
import QuestSingle from "@/components/quests/QuestSingle";
import {mapActions, mapGetters} from "vuex";

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
  },
  // computed
  computed: {
    ...mapGetters(['relayQuest'])
  },
}
</script>

<style scoped>

</style>