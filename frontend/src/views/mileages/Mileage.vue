<template>
  <div>
    <div id="flex-container">
      <div id="profile-wrap">
        <img id="profile-image" src="../../assets/donut1.png" alt="">
        <div id="profile-info">
          <h1 id="username">{{loginUser.nickname}}님의 마일리지</h1>
          <div id="mileage-total">{{mileage}}원</div>
        </div>
      </div>
    </div>

    <div id="alert">
      <i class="fas fa-star" style="color: #fcb131"></i>
      <a id='title' href="">2021 1분기 기부 내역</a>
    </div>
    <div class="branch">
      <router-link to="/user/mileage" class="button">기부</router-link>
<!--      <p style="color: #cd4e3e; font-weight: bold; font-size: 1.3em">|</p>-->
      <router-link to="/user/mileage/cashout" class="button">출금</router-link>
    </div>


    <router-view class="router-view"/>
  </div>
</template>

<script>
// import CashOut from "@/components/mileages/CashOut";
// import Donation from "@/components/mileages/Donation";
import {mapState} from "vuex";
import UserApi from "../../api/UserApi";
import MileagesApi from "../../api/MileagesApi";
export default {
  name: 'Mileage',
  components: {
    // CashOut,
    // Donation
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      mileage: state => state.user.userMileage
    })
  },
  created() {
      UserApi.requestLoginUser(
          this.loginUser.id,
          res => {
            // console.log(res)
            this.$store.dispatch('setMileage', res.data.mileage)
          },
          err => {
            console.log(err)
          }
      )
    MileagesApi.requestCharityList(
        res => {
          console.log(res)
          this.$store.dispatch('setCharityList', res)
        },
        err => {
          console.log(err)
        }
    )
    }
}
</script>

<style scoped>
#flex-container {
  display: flex;
  flex-direction: column;
  text-align: start;
  margin: 15px;
}

#profile-wrap {
  display: flex;
}

#profile-image {
  width: 100px;
  flex: 1 1 0;
}

#profile-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 1.9em;
  flex: 2 2 0;
  padding-left: 15px;
}

#mileage-total{
  font-size: larger;
  font-weight: bold;
}

#alert{
  margin-bottom: 10px;
  padding:3px 0px 3px 10px;
  border-top: solid #05a05b 2px;
  border-bottom: solid #05a05b 2px;
  text-align: left;
}

#title{
  padding-left: 10px;
  text-decoration: none;
  color:black;
  font-size: 0.8em;
}
.branch {
  height: 20px;
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
  font-size: 1.0em;
}


</style>