<template>
  <div>
<!--    <h1>{{nickname}}'s notification</h1>-->

    <div class="branch">
      <router-link :to="`/notification/${nickname}`" class="button">알림</router-link>
<!--      <p style="color: #cd4e3e; font-weight: bold; font-size: 1.3em">|</p>-->
      <router-link :to="`/notification/${nickname}/messages`" class="button">메세지</router-link>
    </div>

    <router-view class="router-view"/>

  </div>
</template>

<script>
import {mapState} from "vuex";
import UserApi from "../../api/UserApi";

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
  created() {
    // console.log('alerts fetched')
    UserApi.requestAlertList(
        this.$route.params.id,
        res => {
          // console.log(res)
          this.$store.dispatch('setAlarms', res.data)
        },
        err => {
          console.log(err)
        }
    )
  }
}
</script>

<style scoped>
.branch {
  height: 30px;
  margin: 30px 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-weight: bold;
}

.branch a.router-link-exact-active {
  border-bottom: #f0a04b solid;
}

.button {
  width: 90%;
  margin-left: 1%;
  margin-right: 1%;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: black;
  text-align: center;
}

</style>