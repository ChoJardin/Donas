<template>
  <div class="overlay">
    <div id="article-detail-arrow">
      <a href="#" @click.prevent="$router.back()">
        <i class="material-icons color292929">arrow_back</i>
      </a>
    </div>
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
  background: white;
}

#article-detail-arrow {
  display: flex;
  margin: 10px 5px 10px 15px;
  color: #183a1d;
}

.material-icons.color292929 {
  color: #183a1d;
}

</style>