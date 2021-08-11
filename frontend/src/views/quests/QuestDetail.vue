<template>
  <div>
    <ComponentNav
      @on-arrow="$router.back()"
      title="퀘스트 상세"/>

    <div>
      <div id="flex-container">
        <div id="quest-wrap">
          <img id="quest-image" src="../../assets/donut1.png" alt="">
          <div id="quest-info">
            <h1 id="title">{{ questDetail.title }}</h1>
            <div id="summary">{{ questDetail.description }}</div>
          </div>



      </div>
        <div>
          {{dateFormatted}}에 시작되었습니다.
        </div>
        <div id="description">
          {{ questDetail.certification }}
        </div>

      <!--article start-->
      <div id="article-wrap">
        <div class="article-image" v-for="article in questDetail.articles" :key="article.id">
          <!--article 같이 보내줘야 함...-->
          <ArticleImage class="inner" :article="article"/>
        </div>
      </div>
    <!--article end-->

    </div>
  </div>
  </div>
</template>

<script>
import ArticleImage from "@/components/articles/ArticleImage";
import {mapGetters, mapState} from 'vuex'
import QuestApi from "../../api/QuestApi";
import moment from "moment";
import ComponentNav from "../../components/common/ComponentNav";

export default {
  name: "QuestSingle",
  components: {
    ArticleImage,
    ComponentNav
  },
   // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      questDetail: state => state.quests.questDetail,
      questId: state => state.quests.questId,
      dateFormatted: function () {
        return moment(String(this.questDetail.startAt)).format('MM/DD/YYYY')
      },
    })
  },
    created() {
      QuestApi.requestQuestDetail(
          this.questId,
          res => {
            // console.log(res)
            this.$store.dispatch('setQuestDetail', res.data)
          },
          err => {
            console.log(err)
          }
      )
    }
  }
</script>

<style scoped>
#flex-container {
  display: flex;
  flex-direction: column;
  text-align: start;
  margin: 15px;
}

#quest-wrap {
  display: flex;
}

#quest-image {
  width: 100px;
  flex: 1 1 0;
}

#quest-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex: 2 2 0;
}

#description {
  height: 50px;
  display: flex;
  align-items: center;
}



#article-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.article-image {
  border: 1px solid olivedrab;
  width: 33.3333%;
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