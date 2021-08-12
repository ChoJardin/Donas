<template>
  <div>
    <ComponentNav
      @on-arrow="$router.back()"
      title="퀘스트 상세"/>

    <div id="quest-detail-head">
      <div id="flex-container">
        <div id="quest-wrap">
          <img id="quest-image" src="../../assets/donut1.png" alt="">
          <div id="quest-info">
            <div id="quest-detail-title">{{ questDetail.title }}</div>
            <div id="summary">{{ questDetail.description }}</div>
            <div id="quest-detail-date"><span style="font-size:1.0em ">{{dateFormatted}}</span>에 시작</div>
          </div>
      </div>


        <div id="quest-detail-description">
          <div>인증 방법</div>
          <div id="quest-detail-des-text">{{ questDetail.certification }}</div>
        </div>

        <router-link :to="{name:'ParticipantsList'}" class="participants">
          <div>참여 인원:
<!--          {{questDetail.users}}-->
          <span> {{userCount}}명</span></div>
          <div> <i class="material-icons" style="padding-top: 4px">navigate_next</i> </div>
        </router-link>
      <!--article start-->
      <div id="article-wrap">
        <div class="article-image" v-for="article in articles" :key="article.id">
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
    articles() {
      const articles = this.$store.state.quests.questDetail.articles
      articles.forEach(article => {
        article['questTitle'] = this.questDetail.title
        article['questId'] = this.questDetail.id
      }, articles)

      return articles
    },
    ...mapState({
      questDetail: state => state.quests.questDetail,
      questId: state => state.quests.questId,
      dateFormatted: function () {
        return moment(String(this.questDetail.startAt)).format('YYYY/MM/DD')
      },
      userCount: function () {
        return this.questDetail.users.length
      }
    })
  },
    created() {
      QuestApi.requestQuestDetail(
          this.questId,
          res => {
            // console.log(res)
            this.$store.dispatch('setQuestDetail', res.data)
            this.$store.dispatch('setFeeds', res.data.articles)
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
  margin-left: 8px;
}

#quest-detail-description {
  height: 50px;
  /*display: flex;*/
  /*align-items: center;*/
  margin-top: 15px;
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


#quest-detail-title {
  font-family: GongGothicBold;
  font-size: 1.3em;
}

#quest-detail-date{
  font-size: 0.8em;
}
#quest-detail-description{
  height: 70px;
  margin-bottom: 20px;
}
#quest-detail-des-text {
  /*border: #f1a64b solid;*/
  background: rgba(241, 166, 75, .3);
  border-radius: 10px;
  height: 50px;
  padding-top: 7px;
  padding-left: 7px;

}

.participants {
  color: black;
  text-decoration: none;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1em;
}
</style>