<template>
  <div class="overlay">
    <h1>ArticleDetail</h1>
  </div>
</template>

<script>
export default {
  name: "ArticleDetail",
  // components
  // props
  props: {
    article: Object
  },
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 60px;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: scroll;
  background: #fff;
}
</style>