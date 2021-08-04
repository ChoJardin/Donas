<template>
  <div>
    <div class="quest-branch">
      <router-link to="/quests" class="quest-button">전체</router-link>
      <router-link to="/quests/solo" class="quest-button">개인</router-link>
      <router-link to="/quests/group" class="quest-button">공동</router-link>
      <router-link to="/quests/relay" class="quest-button">릴레이</router-link>
    </div>

    <router-view id="quest-router-view"/>
    <v-btn id="create-button" class="mx-2 text-decoration-none" fab color="indigo">
      <router-link to="/quests/create"><i class="fas fa-plus fa-lg" style="color: #f19b36"></i></router-link>

    </v-btn>
  </div>
</template>

<script>
export default {
  name: 'QuestAll'
}
</script>

<style scoped>
.quest-branch {
  height: 30px;
  /*margin: 30px 0;*/
  width: calc( 100% - 20px );
  display: flex;
  justify-content: space-around;
  font-size: 1em;
  font-weight: bold;
  position: fixed;
  background: white;
  padding-top: 5px;
  /*padding-left: 10px;*/
  top: 70px;
  padding-bottom: 5px;

}

.quest-branch a.router-link-exact-active {
  border-bottom: #f19b36 solid;
  height: 100%;

}

.quest-button {
  width: 100%;
  margin-left: 2px;
  margin-right: 2px;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: black;
  text-align: center;
  height: 100%;
}

#create-button {
  color: #f0a04b;
  text-decoration-color: transparent;
  position: fixed;
  bottom: 70px;
  right: 15px;
  display: flex;
  align-content: center;
}

#quest-router-view {
  padding-top: 10%;
}
</style>