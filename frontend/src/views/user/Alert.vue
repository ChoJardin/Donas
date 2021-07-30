<template>
  <div>
<!--    <h1>{{nickname}}'s notification</h1>-->

    <div class="branch">
      <router-link :to="`/notification/${nickname}`" class="button1">알림</router-link>
<!--      <p style="color: #cd4e3e; font-weight: bold; font-size: 1.3em">|</p>-->
      <router-link :to="`/notification/${nickname}/messages`" class="button2">메세지</router-link>
    </div>

    <router-view class="router-view"/>

  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Notification",
  // components
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      nickname: state => state.user.loginUser.nickname,
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>
.branch {
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: space-between;
  padding-bottom: 10px;
}

.branch a.router-link-exact-active {
  border-bottom: #cd4e3e solid;
}

.button1 {
  width: 100%;
  border-bottom: lightgray solid 2px;
  /*border-top: #cd4e3e dotted;*/
  text-decoration: none;
  color: black;
  font-size: 1.3em;
  font-weight: bold;
  text-align: center;
  margin-right: 3px;
  /*border-radius: 0 10px 10px 25px;*/
}

.button2 {
  width: 100%;
  border-bottom: lightgray solid 2px;
  /*border-top: #cd4e3e dotted;*/
  text-decoration: none;
  color: black;
  font-size: 1.3em;
  font-weight: bold;
  text-align: center;
  margin-left: 3px;
}
</style>