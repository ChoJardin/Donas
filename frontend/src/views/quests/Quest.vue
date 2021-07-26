<template>
  <div>
    <div id="router-list">
      <router-link to="/quests">전체</router-link> |
      <router-link to="/quests/solo">개인</router-link> |
      <router-link to="/quests/group">공동</router-link> |
      <router-link to="/quests/relay">릴레이</router-link>
    </div>

    <router-view/>
    <v-btn id="create-button" class="mx-2 text-decoration-none" fab color="indigo">
      <a href="/quests/create"><i class="fas fa-plus fa-lg"></i></a>
    </v-btn>
  </div>
</template>

<script>
export default {
  name: 'QuestAll'
}
</script>

<style scoped>
#router-list {
  margin-bottom: 20px;
}

#create-button {
  position: fixed;
  bottom: 70px;
  right: 15px;
}
</style>