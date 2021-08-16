<template>
  <div>
    <div class="branch">
      <router-link :to="{name:'DonationUsage'}" class="button">기부 내역</router-link>
<!--      <p style="color: #cd4e3e; font-weight: bold; font-size: 1.3em">|</p>-->
      <router-link :to="{name:'CashUsage'}" class="button">출금 내역</router-link>
    </div>

    <router-view class="router-view"/>
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
          // console.log(res)
          this.$store.dispatch('setDonationUsage', res)
        },
        err => {
          console.log(err)
        }
    );
    const cash = {type: 'C', userId : this.loginUser.id }
    MileagesApi.requestMileageUsage(
        cash,
        res => {
          // console.log(res)
          this.$store.dispatch('setCashUsage', res)
        },
        err => {
          console.log(err)
        }
    );
  }

}
</script>

<style scoped>
.branch {
  height: 30px;
  margin: 10px 0;
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