<template>
  <div>
    <div  id="quest-list" v-for="quest in quests" :key="quest.id">
      <QuestSingle class="quest-single" :quest="quest" @click.native=setQuestId(quest.id) />
    </div>
  </div>
</template>

<script>
import QuestSingle from "@/components/quests/QuestSingle";
import {mapActions, mapState} from 'vuex'
import QuestApi from "../../api/QuestApi";

export default {
  name: "QuestAll",
  // components
  components: {
    QuestSingle
  },
  // props
  // data
  // methods
  methods:{
    ...mapActions(['setQuestId'])
  },
  // computed
  computed: {
    ...mapState({
      quests: state => state.quests.questsList
    }),
  },


}
</script>

<style scoped>

</style>