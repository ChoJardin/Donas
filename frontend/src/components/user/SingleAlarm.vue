<template>
  <div class="single-alarm">
    <div v-if="alarm.articleId === -1"  style="text-decoration: none; color: black">
      <div class="alarm-wrap">
        <div class="alarm-read" @click="statusChange" v-if="alarm.confirm === 1"><i class="material-icons" style="font-size: 1em">markunread</i></div>
        <div class="alarm-read"  v-else><i class="material-icons-outlined" style="font-size: 1em">drafts</i></div>
        <div class="alarm-message" @click="toSendPoint">
          <div class="alarm-date-time" >{{dateFormatted}}</div>
          <div class="alarm-content">{{alarm.content}}</div>
        </div>
      </div>
    </div>
    <div v-else-if="alarm.articleId === -2" style="text-decoration: none; color: black">
      <div class="alarm-wrap">
        <div class="alarm-read"  v-if="alarm.confirm === 1" @click="statusChange"><i class="material-icons" style="font-size: 1em">markunread</i></div>
        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 1em">drafts</i></div>
        <div class="alarm-message"  @click="toSendPoint">
          <div class="alarm-date-time" >{{dateFormatted}}</div>
          <div class="alarm-content">{{alarm.content}}</div>
        </div>
      </div>
    </div>
    <div v-else-if="alarm.articleId === -3" style="text-decoration: none; color: black">
      <div class="alarm-wrap">
        <div class="alarm-read" v-if="alarm.confirm === 1" @click="statusChange"><i class="material-icons" style="font-size: 1em">markunread</i></div>
        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 1em">drafts</i></div>
        <div class="alarm-message" @click="toSendPoint">
          <div class="alarm-date-time" >{{dateFormatted}}</div>
          <div class="alarm-content">{{alarm.content}}</div>
        </div>
      </div>
    </div>

    <div v-else style="text-decoration: none; color: black">
      <div class="alarm-wrap">
        <div class="alarm-read" v-if="alarm.confirm === 1" @click="statusChange"><i class="material-icons" style="font-size: 1em">markunread</i></div>
        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 1em">drafts</i></div>
        <div class="alarm-message" @click="toArticle">
          <div class="alarm-date-time" >{{dateFormatted}}</div>
          <div class="alarm-content">{{alarm.content}}</div>
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
export default {
  name: "SingleAlarm",
  props: {
    alarm: Object,
  },
  methods: {
    //읽음 상태 변경
    statusChange() {
      // console.log('change')
      let data = {
        id: this.alarm.id,
        confirm: 0
      }
      // console.log(data)
      let path
      UserApi.updateAlertStatus(
          data,
          res => {
            // console.log(res)
            path = res.data === 'OK' ? `/user/profile/${this.alarm.sendName}` : '/error'
            this.alarm.confirm = 0
          },
          err => {
            console.log(err)
          })
    },
    toSendPoint() {
      // console.log('change')
      let data = {
        id: this.alarm.id,
        confirm: 0
      }
      // console.log(data)
      let path
      UserApi.updateAlertStatus(
          data,
          res => {
            // console.log(res)
            path = res.data === 'OK' ? `/user/profile/${this.alarm.sendName}` : '/error'
            this.$router.push(path)
          },
          err => {
            console.log(err)
          })
    },
    toArticle() {
      let data = {
        id: this.alarm.id,
        confirm: 0
      }
      // console.log(data)
      let path
      UserApi.updateAlertStatus(
          data,
          res => {
            // console.log(res)
            // path = res.data === 'OK' ? `/article/${this.alarm.articleId}` : '/error'
            path = res.data === 'OK' ? `/article/${this.alarm.articleId}` : '/error'
            this.$router.push(path)
          },
          err => {
            console.log(err)
          })
    }
  },
  computed: {
    dateFormatted: function (){
      return moment(String(this.alarm.sendTime)).format('YYYY/MM/DD HH:mm')
    }
  },


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
  padding-top: 8px

}

.alarm-message {
  width: 90%;
  text-align: left;
  margin-left: 5px;
}

.alarm-date-time {
  font-family: GongGothicLight;
  font-size: 0.8em;
}

.alarm-content {
  /*font-size: 0.9em;*/
  /*font-family: GongGothicLight;*/
}

</style>