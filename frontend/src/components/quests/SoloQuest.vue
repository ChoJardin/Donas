<template>
  <div>
    <div v-for="quest in singleQuest" :key="quest.id" >
      <QuestSingle class="quest-single" :quest="quest" @click.native=setQuestId(quest.id) />
    </div>
  </div>
</template>

<script>
import QuestSingle from "@/components/quests/QuestSingle";
import {mapActions, mapGetters} from "vuex";

export default {
  name: "SoloQuest",
  // components
  components: {
    QuestSingle,

  },
  // props
  // data
  // methods
  methods:{
    ...mapActions(["setQuestId"])
  },
  // computed
  computed: {
    ...mapGetters(['singleQuest'])
  },
}
</script>

<style scoped>

</style>