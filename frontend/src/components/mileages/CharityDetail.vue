<template>
  <div class="charity-all">
    <ComponentNav
    @on-arrow="$router.back()"
    title="기부 단체" button="기부하기" @on-button="scrollToBottom"
    />

    <div class="charity-background">
      <img :src="`${charity.bgPicture}`" style="width: 100%; height: 350px" alt="">
    </div>

    <div id="charity-main">
      <img :src="`${charity.picture}`" class="charity-image" alt="">

      <div class="charity-name">
        <div class="charity-title">{{charity.name}}</div>
        <a :href="`${charity.homepage}`"><i class="material-icons">link</i></a>
      </div>


      <div class="charity-tag">
        <span>{{charity.tag}}</span>
        <span style="margin-left: 3px">{{charity.tag2}}</span>
      </div>
      <div class="charity-description">{{charity.description}}</div>

      <div class="charity-status-label">기부 현황</div>
      <div class="charity-status" >
        <div class="charity-amount" style="margin-bottom:5px">지난 분기 기부 마일리지:</div>
        <div class="charity-mileage">{{currencyString2}}</div>
      </div>

      <div class="charity-status">
        <div class="charity-amount">현재 모금 마일리지:</div>
        <div class="charity-mileage">{{currencyString1}}</div>
      </div>

      <hr class="charity-devide">

      <div id="donation-form-main" v-if="isLoggedIn">
          <div style="font-size: 1.1em; margin-bottom: 3px">{{charity.name}} 후원하기</div>
            <div class="donation-form">
              <div style="font-size: 0.8em; margin-bottom: 15px; font-family: GongGothicLight">후원하시려면 아래의 내역을 작성해 주세요</div>

              <div class="charity-mileage-info">{{loginUser.mileage}} 마일리지 보유</div>
              <div class="donation-amount">
                <div class="donation-input-label" >기부 마일리지:</div>
                <div class="donation-int">
                  <input
                      type="number" class="donation-input" v-model="inputAmount"
                      placeholder="보유 마일리지까지 기부 가능합니다">
                </div>
              </div>
                <div v-if="error.inputAmount" class="charity-mileage-error">{{error.inputAmount}}</div>

              <div class="donation-info">
                <div class="charity-mileage-info">한글 및 영문만 작성 가능합니다</div>
                <div class="donation-amount">
                  <div class="donation-input-label">기부자 명:</div>
                  <div class="donation-int">
                    <input type="text" v-model="name" class="donation-input">
                  </div>
                </div>
                <div v-if="error.name" class="charity-mileage-error">{{error.name}}</div>
              </div>

            <button class="donation-button" :class="{disabled: isDisabled}" @click="confirmPage">확인</button>
          </div>

      </div>
      <div v-else>
        <div>로그인 하시면 더 많은 기능이 보여요!</div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import MileagesApi from "../../api/MileagesApi";
import ComponentNav from "../common/ComponentNav";

export default {
  name: "CharityDetail",
  components:{
    ComponentNav
  },
  data() {
      return {
        charity: Object,
        inputAmount: '',
        name:'',
        // homepage: this.charity.homepage,
        error: {
          inputAmount: false,
          name: false
        }
      }
    },
  methods: {
    confirmPage () {
      let data = {
        userId: this.loginUser.id,
        userName: this.name,
        amount: this.inputAmount,
        charityId: this.charityId
      }
      MileagesApi.createDonationRequest(
          data,
          res => {
            console.log(res)
            data.cashout = '0'
            data.charityName = this.charity.name
            console.log(data)
            this.$router.push({name: 'CashOutResult', params: data})
          },
          err => {
            console.log(err)
          }
      )
    },
    scrollToBottom(){
      if (this.isLoggedIn)
        return document.getElementById('donation-form-main').scrollIntoView();
      else
        this.$router.push(({name: 'Login', params: {history: this.$route.fullPath}}))
    }
  },
  computed: {
    ...mapState({
      charityId: state => state.mileages.charityId,
      loginUser: state => state.user.loginUser,
    }),
    ...mapGetters(['isLoggedIn']),
    isDisabled() {
      return !(this.name && this.inputAmount && Object.keys(this.error).every(key => !this.error[key]))
    },
    currencyString1: function (){
      const amount = this.charity.total
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },
    currencyString2: function (){
      const amount = this.charity.quarter
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  watch: {
    inputAmount: function(v) {
      if (this.inputAmount > this.loginUser.mileage) {
        this.error.inputAmount = '보유하신 마일리지 이하의 금액만 작성 가능합니다!'
      } else if (this.inputAmount < 0) {
        this.error.inputAmount = '음수는 작성하실 수 없습니다'
      } else this.error.inputAmount = false
    },
    name: function(v) {
      let checkEng = /[a-zA-Z]/
      let checkKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/
      if (checkEng.test(this.name)) {
        this.error.name = false
      } else if (checkKor.test(this.name)) {
        this.error.name = false
      } else {
        this.error.name = '유효한 문자를 입력해주세요'
      }
    }
  },
  created() {
    this.charity = this.$store.getters.charityDetail;
  },

  // beforeRouteEnter(to, from, next) {
  //   next(vm => {
  //     console.log('window')
  //     document.getElementById('app').scrollIntoView();
  //   })
  // }

}



</script>
<style scoped>
.charity-background{
  position: fixed;
  top: 80px;
  width: 100%;
  max-width: 425px;
  left: 0;
  right: 0;
  margin: 0 auto;
  height: 150px;
  background: transparent;

}
#charity-main{
  background-color: #ffffff;
  position: absolute;
  z-index: 1;
  top: 350px;
  left: 0;
  width: 100%;
  padding: 30px 15px 0;
  margin-bottom: 20px;
  border-top: rgba(41, 41, 41, 0.3) solid 1px;
  /*height: 600px;*/
  border-radius: 25px 25px 0px 0px;
}

.charity-image {
  height: 100px;
  width: 100px;
  border: 1px solid #183a1d;
  border-radius: 50%;
  margin: 0 auto 20px;
  padding: 2px;
}
.charity-name{
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 0 20px;
}

.charity-title{
  font-family:"GongGothicBold";
  font-size: 1.1em;
}

.material-icons {
  position: absolute;
  top: -20%;
  padding-left: 8px;
}

.charity-tag{
  margin: 10px 0;
}
.charity-tag span{
  border: 1.5px #183a1d solid;
  font-family: GongGothicLight;
  font-size: 0.8em;
  padding: 2px 5px;
  margin: 0 3px;
  border-radius: 50em;
}

.charity-description{
  margin-top: 15px;
  font-family: GongGothicLight;
  font-size: 0.8em;
  background: rgba(241, 166, 75, 0.25);
  padding: 15px;
  border-radius: 25px;
  margin-bottom: 30px;
  text-align: left;
}
#donation-form-main{
  margin-top: 40px;
}

.charity-status-label {
  padding-bottom: 5px;
  /*border-bottom: 1px solid #183a1d;*/
}

.charity-status{
  font-family: GongGothicLight;
  font-size: 0.9em;
  margin: 15px auto;
  display: flex;
  justify-content: space-around;
  width: 100%;
}

.charity-amount {
  width: 50%;
  text-align: left;
}

.charity-mileage {
  font-family: GongGothicMedium;
}

.charity-devide {
  color: rgba(41, 41, 41, 0.50);
  border-style: dotted;
  margin-top: 30px;
  width: 90%;
  margin-left: 5%;
}

.donation-amount{
  display: flex;
  justify-content: space-around;
  align-items: center;
  /*margin-top: 20px;*/
  /*width: 100%;*/
}
/*.donation-int{*/
/*  display: flex;*/
/*  justify-content: flex-end;*/
/*  align-items: center;*/
/*}*/

.donation-input{
  width: 100%;
  border: #292929 solid 1px;
  border-radius: 8px;
  margin-left: 5px;
  padding:3px 5px;
  font-size: 0.8em;
  font-family: GongGothicLight;
}

.donation-input-label {
  font-size: 0.9em;
  text-align: left;
  width: 30%;
}

.charity-mileage-info {
  font-family: GongGothicLight;
  font-size: 0.8em;
  text-align: right;
  margin-right: 25px;
  margin-top: 25px;
  margin-bottom: 5px;
}

.charity-mileage-error {
  font-family: GongGothicLight;
  font-size: 0.8em;
  text-align: right;
  margin-right: 25px;
  margin-top: 5px;
  margin-bottom: 5px;
  color: #cd4e3e;
}

.donation-info{
  margin-top: 8px;
  margin-bottom: 30px;
}

.donation-button{
  width: 90%;
  border: rgba(24, 58, 29, 0.8) solid 1px;
  padding: 5px 0;
  border-radius: 13px;
  background: rgba(24, 58, 29, 0.8);
  color: floralwhite;
  margin-bottom: 20px;
}

.donation-button.disabled {
  opacity: 0.6;
  pointer-events: none;
}

</style>