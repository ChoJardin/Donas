<template>
  <div class="single-alarm">
    <div style="text-decoration: none; color: black">
      <div class="alarm-wrap" v-if="alarm.confirm !== 0" @click="setQuestId(alarm.questId)">
        <div class="alarm-read"><i class="material-icons-outlined" style="font-size: 0.7em">circle</i></div>
<!--        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 0.7em">circle</i></div>-->
        <div class="alarm-message">
          <div style="font-size: 0.5em">{{dateFormatted}}</div>
          <div style="font-size: 0.8em">{{alarm.content}}</div>
          <div v-if="alarm.confirm === 2">참여 승락</div>
          <div v-else>참여 거절</div>
        </div>
      </div>

      <div class="alarm-wrap" v-else>
        <div class="alarm-read"><i class="material-icons" style="font-size: 0.7em">circle</i></div>
<!--        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 0.7em">circle</i></div>-->
        <div class="alarm-message">
          <div style="font-size: 0.5em">{{dateFormatted}}</div>
          <div style="font-size: 0.8em">{{alarm.content}}</div>
          <div class="decision">
            <button @click="onAccept" class="answer">참여</button>
            <button @click="onDecline" class="answer">거절</button>
          </div>
        </div>
      </div>
    </div>
<!--    <router-link v-else :to="{name:'ArticleDetail', params:{id:alarm.articleId}}" style="text-decoration: none; color: black">-->
<!--      <div class="alarm-wrap">-->
<!--        <div class="alarm-read" v-if="alarm.confirm === '0'"><i class="material-icons" style="font-size: 0.7em">circle</i></div>-->
<!--        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 0.7em">circle</i></div>-->
<!--        <div class="alarm-message">-->
<!--          <div style="font-size: 0.5em">{{dateFormatted}}</div>-->
<!--          <div style="font-size: 1.0em">{{alarm.content}}</div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </router-link>-->
  </div>
</template>

<script>
import moment from 'moment'
import UserApi from "../../api/UserApi";
import {mapActions, mapState} from "vuex";
export default {
  name: "SingleQuestAlarm",
  props: {
    alarm: Object,
  },
  methods: {
    //읽음 상태 변경
    onAccept(){
      let data = {
        alarmId: this.alarm.id,
        questId: this.alarm.questId,
        userId: this.loginUser.id
        }
      // console.log(data)
      let path
      UserApi.acceptAlert(
          data,
          res => {
            console.log(res)
            path = res.data === 'OK' ? `/quests/${this.alarm.questId}` : '/error'
            this.$router.push(path)
          },
          err => {
            console.log(err)
          })
      },
    onDecline() {
      // console.log(data)
      const data = {
        alarmId:this.alarm.id,
        userId:this.loginUser.id
      }
      console.log(data)
      let path
      UserApi.declineAlert(
          data,
          res => {
            console.log(res)
            path = res.data === 'OK' ? `/notificatoin/${this.loginUser.nickname}` : '/error'
            this.$router.push(path)
          },
          err => {
            console.log(err)
          })
      },
    ...mapActions(['setQuestId'])
  },
  computed: {
    dateFormatted: function (){
      return moment(String(this.alarm.sendTime)).format('MM/DD/YYYY hh:mm')
    },
    ...mapState({
      loginUser: state => state.user.loginUser,
    })
  }

}
</script>

<style scoped>

.alarm-wrap {
  /*border-top: rgba(24, 58, 29, 0.50) solid;*/
  border-bottom: rgba(24, 58, 29, 0.3) solid 1px;
  /*box-shadow: 0 0 15px -7px #183a1d;*/
  padding: 5px 0px;
  display: flex;
  align-items: center;
}

.alarm-read {
  width: 10%;
  margin-left: 5px;
  color: #183a1d;

}

.alarm-message {
  width: 90%;
  text-align: left;
  margin-left: 5px;
}

.decision {
  /*float: right;*/
}
.answer{
  border: #f1a64b solid;
  border-radius: 80em;
  padding: 2px 8px;
}
</style>