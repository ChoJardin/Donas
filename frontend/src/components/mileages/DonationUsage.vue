<template>
  <div>
    <h1>{{loginUser.nickname}}님, </h1>
    <h4>총  {{currencyString}} 마일리지를 기부하셨습니다!</h4>


    <div class="donation-usage-single" >
      <h3>기부 내역</h3>
      <div  v-for="donation in usageDonation.donationList" :key="donation.id" :v-model="formatDate" v-bind:value="donation.time">
        <div class="donation-usage-item">
          <div class="donation-usage-left">
            <div style="font-size: 1em">{{donation.charityName}}</div>
            <div class="donation-usage-left-bottom">
              <div>
                <div>{{donation.time.split("T")[0]}} | </div></div>
              <div>기부</div>
            </div>
          </div>

          <div class="donation-usage-right">
            <div>{{donation.amount}} 마일리지</div>
          </div>

        </div>
      </div>
    </div>

  </div>

</template>

<script>
import {mapState} from "vuex";

import('@/assets/style/mileages/Usage.css')

export default {
  name: "donationUsage",
  data() {
    return {
      formatDate: ''
    }
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      usageDonation: state => state.mileages.usageDonation,
    }),
    currencyString: function (){
      const amount = this.usageDonation.total
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }
  },
}
</script>

<style scoped>

</style>