<template>
  <div class="cashout">
    <div class="cashout-warning">*출금 금액의 80%는 자동으로 기부 됩니다.</div>

    <div class="cashout-amount">
      <div class="cashout-input-label" >출금 금액:</div>
      <div style="display: flex; align-items: center">
        <div class="cashout-currency">₩&nbsp;</div>
        <input type="number" class="cashout-input" v-model="inputAmount">
      </div>
    </div>

    <div class="donation-amount">
      <div v-if="error.inputAmount" class="cashout-warning" style="float: right" >{{error.inputAmount}}</div>
      <div v-else class="cashout-warning" style="text-align: right; color: #183a1d">{{donationAmount}}원이 자동 기부 됩니다.</div>
    </div>

      <div class="cashout-donation">
        <div class="cashout-input-label-md"> 후원 단체 </div>

        <div class="select">
        <div v-if="!charityId" class="placeholder">후원단체를 선택해주세요</div>
        <select v-model="charityId" id="charity-select" class="cashout-charity">
          <option v-for="charity in charityList" :key="charity.id" :value="charity.id">
            {{charity.name}}
          </option>
        </select>
        </div>
      </div>




    <div class="cashout-info">
      <div class="cashout-input-label" style="text-align: left">출금 정보</div>
      <div class="cashout-amount">
        <div class="cashout-input-label-md">이름</div>
        <input type="text" v-model="name" class="cashout-input">
      </div>

      <div class="cashout-donation">
        <div class="cashout-input-label-md"> 이체 은행 </div>

        <div class="select">
        <div v-if="!bank" class="placeholder">은행을 선택해주세요</div>
          <select v-model="bank" class="cashout-charity">
            <option v-for="(bank,idx) in bankList" :key="idx" :value="bank">
              {{bank}}
            </option>
          </select>
        </div>
      </div>

      <div class="cashout-amount">
        <div class="cashout-input-label-md">계좌번호</div>
        <input type="text" v-model="account" class="cashout-input">
      </div>
      <div class="cashout-warning" style="float: right" v-if="error.account">{{error.account}}</div>

    </div>

    <button class="button" :class="{disabled: isDisabled}" @click="confirmPage">확인</button>

  </div>
</template>

<script>
import MileagesApi from "../../api/MileagesApi";
import {mapState} from "vuex";

export default {
  name: "CashOut.vue",
  data:  () => {
    return {
      inputAmount: '',
      name:'',
      bank: '',
      account:'',
      charityId:'',
      error: {
        inputAmount: false,
        name: false,
        account: false,
      }
    }
  },
  //methods
  methods: {
    confirmPage () {
      let donationData = {
        userId: this.loginUser.id,
        userName: this.name,
        amount:parseInt(this.donationAmount),
        charityId: this.charityId
      }
      console.log(donationData)
      MileagesApi.createDonationRequest(
          donationData,
          res => {
            console.log(res)
          },
          err => {
            console.log(err)
          }
      )
      console.log('cashout')
      let data = {
        userId: this.loginUser.id,
        bank: this.bank,
        accountNum: this.account,
        amount:parseInt(this.refundAmount),
        userName: this.name,
        charityId: this.charityId
      }

      MileagesApi.createCashOutRequest(
          data,
          res => {
            console.log(res)
            data.cashout = '1'
            console.log(data)
            this.$router.push({name: 'CashOutResult', params: data})
          },
          err => {
            console.log(err)
          }
      )
    }
  },
  //computed
  computed: {
    donationAmount: function (){

      return Math.round(this.inputAmount * 0.8)
    },
    refundAmount: function (){
      return Math.round(this.inputAmount - this.donationAmount)
    },
    // overLimit: function ()  {
    //   if (this.inputAmount > parseInt(this.loginUser.mileage))
    //     this.inputAmount = 0
    //     return 1
    //
    //
    // },
    ...mapState({
      loginUser: state => state.user.loginUser,
      charityList: state => state.mileages.charityList,
      bankList: state => state.mileages.bankList
    }),
    isDisabled() {
      return !(this.account && this.bank && this.charityId && this.inputAmount && this.name &&
              Object.keys(this.error).every(key => !this.error[key])
      )
    }
  },
  watch: {
    inputAmount: function (v) {
      if (v > this.loginUser.mileage) {
        this.error.inputAmount = '보유 마일리지를 초과하는 금액은 입력하실 수 없습니다'
      } else {
        this.error.inputAmount = false
      }
    },
    account: function(v) {
      if (v.length < 10) {
        this.error.account = '유효한 계좌번호를 입력해주세요'
      } else {
        this.error.account = false
      }
    }
  }

}
</script>

<style scoped>
.cashout{
  margin: 10px 0;
}

.cashout-warning{
  font-size: 0.7em;
  text-align: left;
  margin-bottom: 5px;
  color: #cd4e3e;
  font-family: GongGothicLight;
}

.cashout-amount{
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cashout-donation {
  display: flex;
  justify-content: space-between;
  padding-bottom: 20px;
  margin: 7px 0px 15px 0px;
  border-bottom: rgba(41, 41, 41, 0.40) dotted;
}

.donation-amount{
  text-align: right;
  font-size: 0.8em;
  color: #183a1d;
}

.cashout-charity{
  display: flex;
  border: black solid 1px;
  font-size: 0.8em;
}

.cashout-input-label{
  font-family: GongGothicLight;
  /*display: flex;*/
  /*align-items: center;*/
}

.cashout-input-label-md {
  font-family: GongGothicLight;
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
  font-family: GongGothicLight;
  padding-left: 10px;
  border-radius: 10px;
  height: 30px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.button {
  width: 90%;
  margin-left: 5%;
  margin-top: 25px;
  height: 50px;
  background-color: #183a1d;
  color: #e1eedd;
  border-radius: 25px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
  font-size: 1em;
  cursor: pointer;
}

.button.disabled {
  opacity: 0.6;
  pointer-events: none;
}

/*select box*/
select {
  /* Reset Select */
  appearance: none;
  outline: 0;
  border: 0;
  box-shadow: none;
  /* Personalize */
  flex: 1;
  padding: 0 1em;
  /*border: 1px solid #292929;*/
  /*background-color: #ffffff;*/
  background-image: none;
  cursor: pointer;
}
/* Remove IE arrow */
select::-ms-expand {
  display: none;
}

.placeholder {
  position: absolute;
  z-index: -1;
  font-family: GongGothicLight;
  font-size: 0.8em;
  top: 5px;
  left: 8px;
}

/* Custom Select wrapper */
.select {
  position: relative;
  display: flex;
  width: 60%;
  /*border-radius: 12px;*/
  overflow: hidden;
  height: 30px;
  font-family: GongGothicLight;
  color: #183a1d;
}
.select::before {
  color: #183a1d;
}


/* Arrow */
.select::after {
  content: '▼';
  position: absolute;
  top: 0;
  right: 0;
  padding: 8px;
  color: #e1eedd;
  background-color: #183a1d;
  transition: .25s all ease;
  pointer-events: none;
}
/* Transition */
.select:hover::after {
  background-color: #e1eedd;
  color: #183a1d;
}

/*.charity-select-label {*/
/*  position: ;*/
/*}*/



</style>