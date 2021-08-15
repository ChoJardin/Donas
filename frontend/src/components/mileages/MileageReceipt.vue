<template>
  <div>
  </div>
</template>

<script>
import MileagesApi from "../../api/MileagesApi";
import {mapGetters,mapState} from "vuex";


export default {
  name: "MileageReceipt",
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    }),
  },
  created() {
    // console.log('quests fetched')
    const donation = {type: 'D', userId : this.loginUser.id }
    MileagesApi.requestMileageUsage(
        donation,
        res => {
          console.log(res)
          this.$store.dispatch('setDonationUsage', res.data)
        },
        err => {
          console.log(err)
        }
    );
    const cash = {type: 'C', userId : this.loginUser.id }
    MileagesApi.requestMileageUsage(
        cash,
        res => {
          console.log(res)
          this.$store.dispatch('setCashUsage', res.data)
        },
        err => {
          console.log(err)
        }
    );
  }

}
</script>

<style scoped>

</style>