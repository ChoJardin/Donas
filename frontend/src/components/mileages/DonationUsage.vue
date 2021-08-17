<template>
  <div>
    <div v-if="usageDonation === undefined">
      <h1>{{loginUser.nickname}}님, </h1>
      <h4>아직 기부 내역이 없습니다.</h4>
      <h4>도전의 결과를 나눠주세요!</h4>
    </div>

    <div v-else>
      <h1>{{loginUser.nickname}}님, </h1>
      <h4>총  {{currencyString(usageDonation.total)}} 마일리지를 기부하셨습니다!</h4>


      <div class="donation-usage-single" >
        <h3>기부 내역</h3>
        <div  v-for="donation in usageDonation.donationList" :key="donation.id" :v-model="formatDate" v-bind:value="donation.time">
          <div class="donation-usage-item">
            <div class="donation-usage-left">
              <div style="font-size: 1em">{{donation.charityName}}</div>
              <div class="donation-usage-left-bottom">
                <div>{{dateFormatted(donation.time)}} | </div>
                <div style="margin-left: 2px">기부</div>
              </div>
            </div>

            <div class="donation-usage-right">
              <div>{{currencyString(donation.amount)}} 마일리지</div>
            </div>

          </div>
        </div>
      </div>
    </div>

  </div>

</template>

<script>
import {mapState} from "vuex";
import moment from "moment";

import('@/assets/style/mileages/Usage.css')

export default {
  name: "donationUsage",
  data() {
    return {
      formatDate: ''
    }
  },
  methods: {
    currencyString(amount) {
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },
    dateFormatted(date){
      return moment(String(date)).format('YYYY/MM/DD')
    },
  },
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