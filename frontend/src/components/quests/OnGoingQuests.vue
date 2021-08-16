<template>
  <div>
    <h1>OnGoingQuests</h1>
  </div>
</template>

<script>
import QuestApi from "@/api/QuestApi";
import {mapState} from "vuex";

export default {
  name: "OnGoingQuests",
  components: {},
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    })
  },
  created() {
    const data = {
      userId: this.loginUser.id,
      status: 'p'
    }
    QuestApi.requestMyQuests(
        data,
        res => {
          // if (res.data !== 'NOT_FOUND') {
          //
          // }
        }
    )
  }
}
</script>

<style scoped>

</style>