  <template>
  <div id="quest-single">

    <!--<div id="single-quest-main">-->
    <!--  <div id="single-quest-wrap">-->

        <!--게시글 작성 정보-->
        <!--<div id="single-quest-profile">-->
          <img v-if="quest.picture" class="quest-image" :src="quest.picture" alt="">
          <img v-else class="quest-image" src="../../assets/donut1.png">

          <div id="single-quest-info">
              <div id="single-quest-title">
                <span class="quest-status-tag" v-if="isItEnded">종료</span>
                <span class="quest-status-tag" v-else-if="isItBetween">진행중</span>
                <span class="quest-status-tag" v-else>시작예정</span>
                {{ quest.title }}
              </div>
              <div class="single-quest-date">{{startDate}} - {{endDate}}</div>
            <div class="single-quest-description" >{{quest.description}}</div>
            </div>
        </div>
        <!--게시글 작성 정보-->
      <!--</div>&lt;!&ndash;single-article-wrap ends&ndash;&gt;-->
    <!--</div>&lt;!&ndash;single-article-main ends&ndash;&gt;-->
  <!--</div>-->
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

#quest-single  {
  position: relative;
  display: flex;
  margin: 15px 10px;
  border-radius: 20px;
  padding: 5px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
}


.quest-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 50%;
  padding: 2px;
  border: 1px solid #292929;
}


#single-quest-title {
  text-align: left;
  width: 96%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;

}
.single-quest-date,
.single-quest-description{
  text-align: left;
  font-family: GongGothicLight;
  font-size: 0.8em;
  width: 95%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.quest-status-tag{
  font-family: GongGothicLight;
  background-color: #f6c453;
  font-size: 0.5em;
  padding: 2px;
  border-radius: 5px;
}

#single-quest-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-left: 15px;
}

</style>