<template>
  <div class="alert-all">
    <div id="alarm-wrap">
      <div class="single-alarm" v-for="alarm in alarms" :key="alarm.id">
        <SingleQuestAlarm class="inner" :alarm="alarm"/>
      </div>
    </div>
<!--    <h4>{{alarms.content}}</h4>-->
<!--    <h4>{{ alarms.sendTime}}</h4>-->
<!--    <h4>{{alarms.confirm}}</h4>-->
  </div>
</template>

<script>
import {mapState} from "vuex";
import UserApi from "../../api/UserApi";
import SingleQuestAlarm from "./SingleQuestAlarm";

export default {
  name: "QuestAlert",
  components: {
    SingleQuestAlarm
  },
  computed: {
    ...mapState({
      // loginUser: state => state.user.loginUser,
      alarms: state => state.user.questAlarms,
    })
  }
}
</script>

<style scoped>
.alert-all{
    height: 75vh;
    width: 90%;
    margin: 0px 0px 0px 15px;

    /*border: 1px solid #292929;*/
    /*border-radius: 30px;*/
    /*box-shadow: 0 0 15px -7px #292929;*/
    /*padding-top: 5px;*/
    /*border-top: rgba(24, 58, 29, 0.5) solid;*/

}
</style>