<template>
  <div class="cashout">
    <div class="cashout-warning">*출금 금액의 10%는 자동으로 기부 됩니다.</div>

    <div class="cashout-amount">
      <div class="cashout-input-label" >출금 금액:</div>
      <div style="display: flex; align-items: center">
        <div class="cashout-currency">₩</div>
        <input type="number" class="cashout-input" v-model="amount">
      </div>
    </div>

    <div class="donation-amount">
        <div >{{donationAmount}}원이 자동 기부 됩니다.</div>
    </div>


    <div class="cashout-info">
      <div class="cashout-input-label" style="text-align: left">출금 정보</div>
      <div class="cashout-amount">
        <div class="cashout-input-label">이름</div>
        <input type="text" class="cashout-input">
      </div>
      <div class="cashout-amount">
        <div class="cashout-input-label">은행</div>
        <input type="text" class="cashout-input">
      </div>
      <div class="cashout-amount">
        <div class="cashout-input-label">계좌번호</div>
        <input type="text" class="cashout-input">
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "CashOut.vue",
  data:  () => {
    return {
      amount: '',
    }
  },
  computed: {
    donationAmount: function (){

      return Math.round(this.amount * 0.1)
    }
  }

}
</script>

<style scoped>
.cashout{
  margin-top: 20px;
}

.cashout-warning{
  font-size: 0.7em;
  text-align: left;
  margin-bottom: 5px;
  color: #cd4e3e;
}

.cashout-amount{
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.donation-amount{
  text-align: right;
  font-size: 0.8em;
  color: #183a1d;
}

.cashout-input-label{
  /*display: flex;*/
  /*align-items: center;*/
}

.cashout-currency{
  font-family: 'GongGothicMedium';
  padding-right: 2px;
}

.cashout-input {
  border: black solid 1px;
  margin-top: 5px;
  margin-bottom: 10px;
  font-size: 0.8em;
  padding-left: 10px;
  border-radius: 10px;
  height: 30px;
  padding-top: 5px;
  padding-bottom: 5px;
}
</style>