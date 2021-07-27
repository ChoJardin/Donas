<template>
  <div>
    <h1>SingleArticle</h1>
    게시글 상세
    {{article}}
    <div v-for="n in 100" :key="n">
      {{n}}
    </div>
  </div>
</template>

<script>
export default {
  name: "SingleArticle",
  // components
  // props
  props: {
    article: Object
  },
  // methods
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>

</style>