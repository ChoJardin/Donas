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
        <a :href="`${charity.homepage}`"><i class="material-icons" style="padding-top: 4px">link</i></a>
      </div>
      <div class="charity-tag">
        <span>{{charity.tag}}</span>
        <span style="margin-left: 3px">{{charity.tag2}}</span>
      </div>
      <div class="charity-description">{{charity.description}}</div>

      <div class="charity-status-label" style="font-size: 1.2em">기부 현황</div>
      <div class="charity-status" >
        <div class="charity-amount" style="margin-bottom:5px">지난 분기 기부 마일리지: {{currencyString2}}</div>
        <div class="charity-amount">현재 모금 마일리지: {{currencyString1}}</div>
      </div>

      <div id="donation-form-main" v-if="isLoggedIn">
          <div style="font-size: 1.2em; margin-bottom: 3px">{{charity.name}} 후원하기</div>
            <div class="donation-form">
              <div style="font-size: 0.8em; margin-bottom: 15px">후원하시려면 아래의 내역을 작성해 주세요</div>
              <div class="donation-amount">
              <div class="donation-input-label"  >기부 마일리지:</div>
              <div class="donation-int">
      <!--        <div class="donation-currency">₩</div>-->
              <input type="number" class="donation-input" v-model="inputAmount">
            </div>
          </div>
          <div class="donation-info">
            <div class="donation-amount">
              <div class="donation-input-label">기부자 명:</div>
              <div class="donation-int">
                <input type="text" v-model="name" class="donation-input">
              </div>
            </div>
          </div>

          <button class="donation-button" @click="confirmPage">확인</button>
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

    currencyString1: function (){
      const amount = this.charity.total
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },
    currencyString2: function (){
      const amount = this.charity.quarter
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }

  },
  created() {
    this.charity = this.$store.getters.charityDetail;
  },
  // watch: {
  //   '$route.params'(v) {
  //     console.log('erer')
  //     document.scrollTop
  //
  //   },
  // },
//   update() {
//       console.log('update')
//       // document.getElementById('charity-main').scrollIntoView()
//   },
//   beforeRouteLeave((to, from, next) {
//     // $mount()
//     console.log('qweqweqweq')
//     // document.scrollTop
//     next()
// })
  // mounted() {
  //   this.$nextTick(function () {
  //     // 누른 위치로 스크롤
  //     document.getElementById('charity-main').scrollHeight
  //   })
  // }
}



</script>
<style scoped>
.charity-background{
  position: fixed;
  top: 80px;
  left: 0;
  width: 100vw;
  height: 150px;
  background: transparent;

}
#charity-main{
  background-color: #ffffff;
  position: absolute;
  z-index: 1;
  top: 350px;
  left: 0;
  width: 100vw;
  padding: 30px 10px 0;
  margin-bottom: 20px;
  border-top: rgba(41, 41, 41, 0.3) solid 1px;
  height: 600px;
  border-radius: 25px 25px 0px 0px;
}

.charity-image {
    height: 100px;
    width: 110px;
    border: 1px solid #292929;
    border-radius: 50%;
    margin-bottom: 10px;
    padding: 7px;
    /*margin-left: 15px;*/
    /*margin-top: 15px;*/
}
.charity-name{
  display: flex;
  align-items: center;
  justify-content: center;
}

.charity-title{
  font-family:"GongGothicBold";
  font-size: 1.2em;
}
.charity-tag{
  margin: 10px 0;
}
.charity-tag span{
  border: #183a1d solid;
  padding: 2px 5px;
  border-radius: 50em;
}

.charity-description{
  background: rgba(241, 166, 75, 0.25);
  padding: 13px 10px;
  border-radius: 25px;
  margin-bottom: 30px;
}
#donation-form-main{
  margin-top: 30px;
}
.charity-status{
  /*display: flex;*/
  /*justify-content: space-evenly;*/
  margin: 15px 0;
  padding-bottom: 20px;
  border-bottom: rgba(41, 41, 41, 0.50) dotted 1px;
}

.donation-amount{
  display: flex;
  justify-content: space-around;
  align-items: center;
  /*width: 100%;*/
}
.donation-int{
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.donation-input{
  width: 20vh;
  border: #292929 solid 1px;
  border-radius: 8px;
  margin-left: 5px;
  padding:3px 5px;
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
}

</style>