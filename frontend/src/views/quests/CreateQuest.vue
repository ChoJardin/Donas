<template>
  <div>
    <ComponentNav @on-arrow="$router.push('/quests')" title="퀘스트 생성"></ComponentNav>
    <div id="router-list">
      <router-link to="/quests/create/solo" style="margin-left: 5px">개인</router-link>
      <p style="padding: 2px"></p>
      <router-link to="/quests/create/group">공동</router-link>
      <p style="padding: 2px"></p>
      <router-link to="/quests/create/relay">릴레이</router-link>
    </div>
    <br>
    <div v-if="$route.name === 'CreateQuest'" id='open'>
      <img id="open-img" src="../../assets/donut_select.png">
    </div>
    <router-view/>

  </div>
</template>

<script>
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: "CreateQuest",
  components: {
    ComponentNav
  }
}
</script>

<style scoped>
#router-list {
  margin-top: 60px;
  width: 100%;
  display: flex;
  justify-content: space-between;
}

#router-list a {
  flex: 1 1 0;
  border: 1.5px #f1a64b solid;
  padding: 5px 10px 5px 10px;
  /*margin-left: 20px;*/
  border-radius: 99em;
  text-decoration: none;
  text-decoration-color: transparent;
  /*font-family: GongGothicBold;*/
  color: #292929;

}

#router-list a.router-link-exact-active {
  background: rgba(241, 166, 75, 0.6);
  font-family: GongGothicBold;
}

#open{
  margin-top: 70px;
}
#open-img{
  width: 100%;
  margin-top: 10px;
}
</style>