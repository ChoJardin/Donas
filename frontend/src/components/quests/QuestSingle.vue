  <template>
  <div>
    <div id="single-quest-main">
      <div id="single-quest-wrap">

        <!--게시글 작성 정보-->
        <div id="single-quest-profile">
          <img v-if="quest.picture" class="quest-image" :src="quest.picture" alt="">
          <img v-else class="quest-image" src="../../assets/donut1.png">
          <div id="single-quest-text">
            <div id="single-quest-title">
              <div class="quest-status-tag" v-if="isItEnded">종료</div>
              <div class="quest-status-tag" v-else-if="isItBetween">진행중</div>
              <div class="quest-status-tag" v-else>시작예정</div>
              <div>{{ quest.title }}</div>
            </div>
            <div class="single-quest-date">{{startDate}} - {{endDate}}</div>
            <div class="single-quest-description">{{quest.description}}</div>
          </div>
        </div>
        <!--게시글 작성 정보-->
      </div><!--single-article-wrap ends-->
    </div><!--single-article-main ends-->
  </div>
</template>

<script>

import moment from "moment";

export default {
  name: "QuestList",
  props: {
    quest: Object,
    data() {
      return {

      }
    }
  },
  computed: {
    endDate: function (){
      return moment.parseZone(this.quest.finishAt).format('YYYY-MM-DD')
    },
    startDate: function() {
      return moment.parseZone(this.quest.startAt).format('YYYY-MM-DD')
    },
    isItEnded: function () {

      return moment().isAfter(this.endDate)
    },
    isItBetween: function () {
      return moment().isBetween(this.startDate, this.endDate)
    },
  }

}
</script>

<style scoped>
#single-quest-main {
    height: 120px;
    width: 90%;
    margin: 15px 5%;
    display: flex;
    flex-direction: column;
    align-items: center;

    /*border: 1px solid #292929;*/
    border-radius: 30px;
    box-shadow: 0 0 15px -7px #292929;
}

#single-quest-wrap {
  width: 320px;
  display: flex;
  margin-left: 5%;
}

/*게시글 작성 정보*/
#single-quest-profile {
    display: flex;
    padding-top: 10px;
    width: 100%;
}

.quest-image {
    height: 100px;
    width: 110px;
    border: 1px solid #292929;
    border-radius: 50%;
    margin-bottom: 2px;
    /*margin-left: 15px;*/
    /*margin-top: 15px;*/
}

#single-quest-text {
    /*flex-direction: column;*/
    margin-left: 15px;
    justify-content: center;
    margin-top: 13px
}

#single-quest-title {
  display: flex;
  justify-items: start;
  align-items: center;
  font-weight: bold;
  font-size: 1.0em;
}
.single-quest-date{
  font-size: 0.5em;
  display: flex;
  justify-content: left;
}

.quest-status-tag{
    background-color: #f6c453;
    font-size: 0.5em;
    font-family: GongGothicLight;
    padding: 0 2px;
    margin: 5px 5px 5px 0;
    border-radius: 5px;
}

.single-quest-description {
  margin-top: 20px;
  display: flex;
  font-size: 0.8em;

}

</style>