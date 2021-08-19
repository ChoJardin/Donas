<template>
  <div class="chat-room-info">
    <img v-if="info.otherPicture" :src="info.otherPicture" alt="" class="chat-profile-img">
    <img v-else src="https://donas.s3.ap-northeast-2.amazonaws.com/donuts/donut_profile.png" alt="" class="chat-profile-img">


    <div class="chat-description">
      <div class="chat-name-time">
        <span class="chat-profile-name">
          {{info.otherName}}
        </span>

        <span class="chat-profile-time">
          {{dateParsed}}
        </span>
      </div>

      <div class="chat-profile-content">
        {{info.content}}
      </div>
    </div>


    <!--{{info}}-->
    <!--<h1>EachRoom</h1>-->
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "EachRoom",
  props: ['info'],
  computed: {
    dateParsed() {
      return moment(String(this.info.lastTime)).format('MM/DD HH:mm')
    }
  }
}
</script>

<style scoped>
.chat-room-info {
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  border-bottom: 1px solid #e4e6e9;
  margin: 5px 0;
}


.chat-profile-img {
  width: 60px;
  height: 60px;
  object-fit: cover;
  border: 1px solid #183a1d;
  border-radius: 50%;
  padding: 2px;
}

.chat-name-time {
  display: flex;
  justify-content: space-between;
}

.chat-profile-name {
  font-family: GongGothicBold;
  /*float: left;*/
}

.chat-profile-time {
  font-family: GongGothicLight;
  font-size: 0.8em;
  /*float: right;*/
  position: absolute;
  right: 20px;
}

.chat-profile-content {
  font-family: GongGothicLight;
  font-size: 0.9em;
  text-align: left;
  width: 80%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;  /* 말줄임 적용 */
}

.chat-description {
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: space-around;
  margin-left: 5%;
}

</style>