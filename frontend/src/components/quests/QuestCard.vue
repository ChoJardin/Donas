<template>
  <div>
    <div id="quest-card">
      <img src="@/assets/IMG_7263.jpeg" alt="">
      <span>
        퀘스트 이름이 들어갑니다
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuestCard"
  // components
  // props
  // data
  // methods
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
  margin: 10px;
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
  height: 25%;
  font-size: 0.8em;
  text-align: left;
}
</style>