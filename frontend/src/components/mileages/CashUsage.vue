<template>
  <div>
    <div>
    <h1>{{loginUser.nickname}}님, </h1>
    <h4>총  {{currencyString}} 마일리지를 인출하셨습니다.</h4>


    <div class="donation-usage-single" >
      <h3>인출 내역</h3>
      <div  v-for="cash in usageCash.cashList" :key="cash.id" :v-model="formatDate" v-bind:value="cash.time">
        <div class="donation-usage-item">
          <div class="donation-usage-left">
            <div style="font-size: 1em">{{cash.name}}</div>
            <div class="donation-usage-left-bottom">
              <div>
                <div>{{cash.time.split("T")[0]}} | </div></div>
              <div>인출</div>
            </div>
          </div>

          <div class="donation-usage-right">
            <div>{{cash.amount}} 마일리지</div>
          </div>

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
  name: "CashUsage",

  data() {
    return {
      formatDate: ''
    }
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      usageCash: state => state.mileages.usageCash,
    }),
    currencyString: function (){
      const amount = this.usageCash.total
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }
  },
}
</script>

<style scoped>

</style>