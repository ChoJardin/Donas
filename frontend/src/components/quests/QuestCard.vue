<template>
  <div>
    <button  id="quest-card">
      <img v-if="quest.picture" :src="quest.picture" alt="">
      <img v-else src="https://donas.s3.ap-northeast-2.amazonaws.com/donuts/donut_flag.png" alt="">
      <span class="quest-card-title">
        <!--퀘스트 이름이 들어갑니다-->
        {{quest.title}}
      </span>
    </button>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "QuestCard",
  // components
  // props
  props: {
    quest: Object
  },
  // data
  // methods
  // methods: {
  //   ...mapActions(["setQuestId"]),
  // },
  // computed
  // watch
  // lifecycle hook
  // navigation guard
}
</script>

<style scoped>
#quest-card {
  /*border: 1px solid #183a1d;*/
  height: 200px;
  width: 140px;
  margin: 15px 8px 10px;
  border-radius: 17px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
  /*background-color: #fefbe9;*/
}

img {
  width: 100%;
  height: 75%;
  border-top-left-radius: 17px;
  border-top-right-radius: 17px;
  padding-bottom: 3px;
  /*border-bottom: 1px solid #e4e6e9;*/
  object-fit: cover;
}

.quest-card-title {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 25%;
  font-size: 0.8em;
  text-align: left;
  font-family: GongGothicLight;
}
</style>