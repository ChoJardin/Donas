<template>
  <div>
    <h1>Relay Quest</h1>
    <v-app>
        <QuestSingle class="quest-single" v-for="n in 21" :key="n"/>
    </v-app>
  </div>
</template>

<script>
import QuestSingle from "@/components/quests/QuestSingle";

export default {
  name: "RelayQuest",
  // components
  components: {
    QuestSingle
  },
}
</script>

<style scoped>

</style>