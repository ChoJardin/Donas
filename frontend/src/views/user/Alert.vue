<template>
  <div>
    <ComponentNav @on-arrow="$router.back()" title="알림"></ComponentNav>

    <div class="branch">
      <router-link :to="`/notification/${loginUser.nickname}`" class="button" replace>퀘스트 알림</router-link>
<!--      <p style="color: #cd4e3e; font-weight: bold; font-size: 1.3em">|</p>-->
      <router-link :to="`/notification/${loginUser.nickname}/common`" class="button" replace>일반 알림</router-link>
    </div>

    <router-view class="router-view"/>

  </div>
</template>

<script>
import {mapState} from "vuex";
import ComponentNav from "@/components/common/ComponentNav";


export default {
  name: "Notification",
  // components
  components: {
    ComponentNav
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      // nickname: state => state.user.loginUser.nickname,
      loginUser: state => state.user.loginUser,
    })
  },
  // watch
  // lifecycle hook
//   created() {
//     console.log('alerts fetched')
//     UserApi.requestQuestAlert(
//         this.loginUser.id,
//         res => {
//           console.log(res)
//           this.$store.dispatch('setQuestAlarms', res.data)
//         },
//         err => {
//           console.log(err)
//         }
//     )
//     UserApi.requestCommonAlert(
//         this.loginUser.id,
//         res => {
//           console.log(res)
//           this.$store.dispatch('setCommonAlarms', res.data)
//         },
//         err => {
//           console.log(err)
//         }
//     )
//   }
}
</script>

<style scoped>
.branch {
  margin-top: 70px;
  height: 30px;
  /*margin: 10px 0;*/
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-family: GongGothicBold;
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