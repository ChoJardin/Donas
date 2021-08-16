<template>
  <div>
    <h1>{{loginUser.nickname}}님, </h1>
    <h4>총 {{usageDonation.total}} 마일리지를 기부하셨습니다!</h4>


    <div class="donation-usage-single">
      <h3>기부 내역</h3>
      <div v-for="donation in usageDonation.donationList" :key="donation.id" >
        <div class="donation-usage-item">
          <div class="donation-usage-left">
            <div style="font-size: 1em">{{donation.charityName}}</div>
            <div class="donation-usage-left-bottom">
              <div>{{donation.time}} |</div>
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

<!--    <div class="cashout-donation">-->
<!--        <div> 이체 은행 </div>-->
<!--        <select v-model="bank" class="cashout-charity">-->
<!--          <option v-for="(bank,idx) in bankList" :key="idx" :value="bank">-->
<!--            {{bank}}-->
<!--          </option>-->
<!--        </select>-->
<!--      </div>-->
</template>

<script>
import {mapState} from "vuex";

import('@/assets/style/mileages/Usage.css')

export default {
  name: "donationUsage",
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      usageDonation: state => state.mileages.usageDonation,
    }),
  },
}
</script>

<style scoped>

</style>