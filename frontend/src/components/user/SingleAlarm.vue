<template>
  <div class="single-alarm">
    <router-link v-if="alarm.articleId === -1" :to="{name:'Profile', params:{nickname:alarm.sendName}}" style="text-decoration: none; color: black">
      <div class="alarm-wrap">
        <div class="alarm-read" v-if="alarm.confirm === '0'"><i class="material-icons" style="font-size: 0.7em">circle</i></div>
        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 0.7em">circle</i></div>
        <div class="alarm-message">
          <div style="font-size: 0.5em">{{dateFormatted}}</div>
          <div style="font-size: 1.0em">{{alarm.content}}</div>
        </div>
      </div>
    </router-link>
    <router-link v-else :to="{name:'ArticleDetail', params:{id:alarm.articleId}}" style="text-decoration: none; color: black">
      <div class="alarm-wrap">
        <div class="alarm-read" v-if="alarm.confirm === '0'"><i class="material-icons" style="font-size: 0.7em">circle</i></div>
        <div class="alarm-read" v-else><i class="material-icons-outlined" style="font-size: 0.7em">circle</i></div>
        <div class="alarm-message">
          <div style="font-size: 0.5em">{{dateFormatted}}</div>
          <div style="font-size: 1.0em">{{alarm.content}}</div>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  name: "SingleAlarm",
  props: {
    alarm: Object,
  },
  computed: {
    dateFormatted: function (){
      return moment(String(this.alarm.sendTime)).format('MM/DD/YYYY hh:mm')
    }
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

</style>