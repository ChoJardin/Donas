<template>
  <div>
    <h1>피드</h1>

    <!--article start-->
    <div id="article-wrap">
      <div class="article-image" v-for="n in 21" :key="n">
        <ArticleImage class="inner"/>
      </div>
    </div>
    <!--article end-->

  </div>
</template>

<script>
import ArticleImage from "@/components/articles/ArticleImage";

export default {
  name: 'Feed',
  components: {
    ArticleImage
  },
}
</script>

<style scoped>
#article-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.article-image {
  border: 1px solid olivedrab;
  width: 33.3333%;
  /*--width: width;*/
  /*height: var(--width);*/
  position: relative;
}

.article-image:after {
  content: "";
  display: block;
  padding-bottom: 100%;
}

.article-image .inner {
  position: absolute; /* Take your picture out of the flow */
  top: 0;
  bottom: 0;
  left: 0;
  right: 0; /* Make the picture taking the size of it's parent */
  width: 100%; /* This if for the object-fit */
  height: 100%; /* This if for the object-fit */
  object-fit: cover; /* Equivalent of the background-size: cover; of a background-image */
  object-position: center;
}


</style>