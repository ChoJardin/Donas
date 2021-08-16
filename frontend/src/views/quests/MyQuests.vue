<template>
  <div>
    <ComponentNav title="나의 퀘스트" @on-arrow="$router.back()"></ComponentNav>

    <div id="follow-wrap">
      <router-link :to="{name: 'CompletedQuests'}" class="follow">
        완료
      </router-link>
      <router-link :to="{name: 'OnGoingQuests'}" class="follow">
        진행중
      </router-link>
      <router-link :to="{name: 'UpcomingQuests'}" class="follow">
        예정
      </router-link>
    </div>

    <router-view/>

  </div>
</template>

<script>
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: "MyQuests",
  components: {
    ComponentNav,
  }

}
</script>

<style scoped>
#follow-wrap {
  height: 30px;
  margin: 70px 0 10px 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-family: GongGothicBold;
}

#follow-wrap a.router-link-exact-active {
  border-bottom: #f0a04b solid;
}

.follow {
  width: 90%;
  margin-left: 1%;
  margin-right: 1%;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: #183a1d;
  text-align: center;
}

</style>