<template>
  <div>
    <div class="search-area">
      <input class="search-box" type="text">
      <button class="search-button"><i class="material-icons">search</i></button>
    </div>
  </div>
</template>

<script>

export default {
  name: "Search",

}
</script>

<style scoped>
.search-area{
  display: flex;
  align-items: center;
  border: black solid;
  border-radius: 90em;
  height: 8vh;
}
.search-box {
  padding-left: 5vh;
  border-right: black solid;
  width: 80%
}

.search-button {
  text-align: center;
  width: 20%;
  padding-top: 1vh;
}
</style>