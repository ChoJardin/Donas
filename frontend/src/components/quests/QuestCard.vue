<template>
  <div>
    <button @click="onClick" id="quest-card">
      <img src="@/assets/IMG_7263.jpeg" alt="">
      <!--<img :src="quest.picture" alt="">-->
      <span>
        <!--퀘스트 이름이 들어갑니다-->
        {{quest.title}}
      </span>
    </button>
  </div>
</template>

<script>
export default {
  name: "QuestCard",
  // components
  // props
  props: {
    quest: Object
  },
  // data
  // methods
  methods: {
    onClick() {
      this.$router.push(`/quest/${this.quest.id}`)
    }
  },
  // computed
  // watch
  // lifecycle hook
  // navigation guard
}
</script>

<style scoped>
#quest-card {
  /*border: 1px solid #183a1d;*/
  height: 150px;
  width: 120px;
  margin: 15px 10px 10px;
  border-radius: 17px;
  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);
}

img {
  width: 100%;
  height: 75%;
  border-top-left-radius: 17px;
  border-top-right-radius: 17px;
  object-fit: cover;
}

span {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 25%;
  font-size: 0.8em;
  text-align: left;

}
</style>