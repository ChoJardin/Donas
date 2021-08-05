<template>
  <div class="overlay">
    <component-nav @on-arrow="$router.back()"/>
  </div>
</template>

<script>
import ComponentNav from "../common/ComponentNav";

export default {
  name: "ArticleDetail",
  // components
  components: {
    ComponentNav
  },
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
  top: 70px;
  right: 15px;
  bottom: 0;
  left: 15px;
  overflow: scroll;
  background: white;
}


</style>