<template>
  <div>
    <ComponentNav
      @on-arrow="$router.back()"
      title="퀘스트 상세"/>
    <div id="quest-detail-main">
      <div id="quest-detail-head">
        <div id="flex-container">
          <div id="quest-wrap">
            <img v-if="questDetail.picture" class="quest-image" :src="questDetail.picture" alt="">
          <img v-else class="quest-image" src="../../assets/donut_flag.png">
            <div id="quest-info">
              <div v-if="questDetail.type === 'P'" style="font-size: 0.7em">개인 퀘스트</div>
              <div v-else-if="questDetail.type === 'G'" style="font-size: 0.7em">공동 퀘스트</div>
              <div v-else-if="questDetail.type === 'R'" style="font-size: 0.7em">릴레이 퀘스트</div>
              <div id="quest-detail-title" style="margin-bottom: 7px">{{ questDetail.title }}</div>
              <div id="summary" style="padding-bottom:2px">{{ questDetail.description }}</div>
              <div id="quest-detail-date"><span style="font-size:1.0em ">{{dateFormatted}}</span>에 시작</div>
            </div>
          </div>


          <div id="quest-detail-description">
            <div>인증 방법</div>
            <div id="quest-detail-des-text">{{ questDetail.certification }}</div>
          </div>

          <div id="quest-detail-contents">
            <router-link :to="{name:'ParticipantsList'}" class="participants">
              <div>참여 인원:
    <!--          {{questDetail.users}}-->
              <span> {{userCount}}명</span></div>
              <div> <i class="material-icons" style="padding-top: 4px">navigate_next</i> </div>
            </router-link>
          </div>
        <!--article start-->
        <div class="quest-detail-articles">
          <div>인증 개시글</div>
          <div v-if="!isItEnded">
            <div v-if="isItMine">
              <button v-if="questDetail.type !== 'R'"  @click="$router.push('/article/create/')">인증 생성</button>
              <button v-else-if="questDetail.type === 'R' && !isItRelay"  @click="$router.push('/article/create/')">인증 생성</button>
              <div v-else-if="questDetail.type === 'R' && isItRelay">다음 참가자 대기중</div>
              <div></div>
            </div>
  <!--          <button @click="onCreate">인증 생성</button>-->
            <button v-else-if="isItMine === false && questDetail.type==='P'" @click="participateSingle">참여 하기</button>
          </div>
        </div>
        <div id="quest-detail-article-wrap">
          <div class="article-image" v-for="article in articles" :key="article.id">
            <!--article 같이 보내줘야 함...-->
            <ArticleImage class="inner" :article="article"/>
          </div>
        </div>
      <!--article end-->

      </div>
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
  // data() {
  //   return {
  //     isIt: this.participants.some(this.isItMine)
  //   }
  // },
  // methods
  methods: {
    onCreate() {
      this.$store.dispatch('setSelectedId', 0)
      this.$router.push('/article/create/')
    },
    participateSingle() {
      const data = {
        questId : this.questId,
        userId : this.loginUser.id
      }
      QuestApi.participate(
          data,
          res => {
            console.log(res)
            this.$router.push({name: 'OnGoingQuests'})
          },
          err => {
            console.log(err)
          }
      )
    },
  },
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
    isItMine(){
      const me = this.loginUser.id
      return this.participants.some(function(element){ if(element.id === me) {return true}})
      },
    isItEnded: function () {
      const end = moment.parseZone(this.questDetail.finishAt).format('YYYY-MM-DD HH:mm')
      return moment().isAfter(end)
    },
    isItRelay() {
      const me = this.loginUser.nickname
      return this.articles.some(function(element) {if(element.makerName === me) {return true}})
    },


    ...mapState({
      questDetail: state => state.quests.questDetail,
      participants: state => state.quests.questDetail.users,
      questId: state => state.quests.questId,
      loginUser: state => state.user.loginUser,
      isLoggedIn: state => state.user.isLoggedIn,
    }),
      dateFormatted: function () {
        return moment.parseZone(String(this.questDetail.startAt)).format('YYYY/MM/DD')
      },
      userCount: function () {
        return this.questDetail.users.length
      }
  },
    // ...mapGetters(['isItMine']),
    // isItMineCheck: function () {
    //   const username = this.loginUser.nickname
    //   this.questDetail.users.filter(function (user){
    //     if (user.nickname === username)
    //       this.isItMine = '1'
    //   // return this.questDetail.users.filter(function (user){
    //   //   if (user.nickname === username)
    //   //     console.log('yes')
    //   }
    //   )
    //   return 1
    // },
    // mountedAfter() {
    //
    // },
  created() {
      const data = {questid:this.questId, myid: 0}
      if (this.loginUser.id !== 0 && this.loginUser.id !== undefined)
          data.myid = this.loginUser.id
      console.log(data)
      QuestApi.requestQuestDetail(
          data,
          res => {
            // console.log(res)
            this.$store.dispatch('setQuestDetail', res.data)
            this.$store.dispatch('setFeeds', res.data.articles)
          },
          err => {
            console.log(err)
          }
      );
      // const me = this.loginUser.nickname
      // this.questDetail.users.filter(function (user){
      //   if (user.nickname === me)
      //     this.$store.dispatch('setIsItMine', '1')
      // })
    }
  }
</script>

<style scoped>
#quest-detail-main{
  margin-top: 60px;
}

#flex-container {
  display: flex;
  flex-direction: column;
  text-align: start;
  margin: 15px;

}

#quest-wrap {
  display: flex;
  border-bottom: rgba(41, 41, 41, 0.2) solid;
  padding-bottom: 10px;
}

.quest-image {
  width: 100px;
  flex: 1 1 0;
  border: rgba(41, 41, 41, 0.2) solid;
  border-radius: 50%;
  height: 100px;
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



#quest-detail-article-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  margin-top: 10px;
}

.quest-detail-articles{
  display: flex;
  justify-content: space-between;
}

.article-image {
  /*border: 1px solid olivedrab;*/
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

#quest-detail-head {

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
  font-size: 0.8em;

}

#quest-detail-contents{
  height: 100px;
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