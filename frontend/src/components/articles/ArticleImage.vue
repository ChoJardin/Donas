<template>
  <!--<router-link :to="{path: '/feed/v', params: {id: `${article.id}`}}">-->
  <router-link :to="{path: '/feed/v', query: {id: article.id}}">
    <div id="wrap">
      <img id="image" src="@/assets/logo3.png" alt="">
    </div>
  </router-link>
</template>

<script>

export default {
  name: "ArticleImage",
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
#wrap {
  height: 100%;
  display: flex;
  align-items: center;
}

#image {
  width: 100%;
}

</style>