<template>
  <div id="my-quest-info">

    <img v-if="quest.picture" :src="quest.picture" alt="" class="quest-profile-image">
    <img v-else src="@/assets/donut_flag.png" alt="" class="quest-profile-image">

    <div class="quest-info">
      <div class="title">
        <span v-if="quest.type === 'P'" class="quest-type">개인</span>
        <span v-else-if="quest.type === 'G'" class="quest-type">공동</span>
        <span v-else-if="quest.type === 'R'" class="quest-type">릴레이</span>
        {{quest.title}}
      </div>
      <div class="description">
        {{quest.description}}
      </div>
      <div class="duration">
        {{dateFormatted(quest.startAt)}} ~ {{dateFormatted(quest.finishAt)}}
      </div>
    </div>
    <!--{{quest}}-->
  </div>
</template>

<script>
import moment from "moment";
import PageNotFound from "@/views/common/PageNotFound";

export default {
  name: "MyQuestInfo",
  props: ['quest'],
  methods: {
    dateFormatted(date) {
      return moment(String(date)).format('YYYY/MM/DD')
    },

  },
  computed: {
  }
}
</script>

<style scoped>
#my-quest-info {
  /*border: 1px solid #292929;*/
  display: flex;
  margin: 15px 10px;
  border-radius: 20px;
  padding: 5px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
}
.quest-profile-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 50%;
  padding: 2px;
  border: 1px solid #183a1d;
}

#my-quest-info .quest-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-left: 15px;
  /*align-self: flex-start;*/
}

#my-quest-info .quest-info .title {
  text-align: left;
}

#my-quest-info .quest-info .title .quest-type {
  font-family: GongGothicLight;
  background-color: #f6c453;
  font-size: 0.5em;
  padding: 2px;
  border-radius: 5px;
}

#my-quest-info .quest-info .description,
#my-quest-info .quest-info .duration {
  text-align: left;
  font-family: GongGothicLight;
  font-size: 0.8em;
}




</style>