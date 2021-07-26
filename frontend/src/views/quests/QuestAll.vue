<template>
  <div>
    <div>
      <h1>전체 퀘스트</h1>
      <router-link to="/quests">개인 퀘스트</router-link> |
      <router-link to="/quests/group">공동 퀘스트</router-link> |
      <router-link to="/quests/relay">릴레이 퀘스트</router-link>

    </div>

    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'QuestAll'
}
</script>

<style scoped>

</style>