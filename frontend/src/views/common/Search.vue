<template>
  <div>
    <div class="search-area">
<!--      <input class="search-box" type="text" @input="searchAutoList"  @change="searchResultList">-->
      <input class="search-box" @keyup.enter="searchResultList" type="text" @input="searchAutoList">
      <button class="search-button"   @click="searchResultList"><i class="material-icons">search</i></button>
    </div>
      <div class="search-result" v-if="searched === 0">
        <div style="padding-bottom: 40px; border-bottom: #292929 dotted 1px">{{loginUser.nickname}}님의 최근 사용자 검색 결과 입니다.</div>
        <div class="search-recent-list" v-for="(recent,idx) in recentSearch" :key="idx" >
          <router-link class="search-recent-text" :to="`/user/profile/${recent.nickname}`">
            <i class="material-icons" style="font-size: 1.1em; margin-right: 20px">find_in_page</i>
            <div>{{recent.nickname}}</div>
          </router-link>
        </div>
      </div>
      <transition-group class="search-transition-group" v-else-if="searched === 1"
        name="staggered-fade"
          tag="ul"
          v-bind:css="false"
          v-on:before-enter="beforeEnter"
          v-on:enter="enter"
          v-on:leave="leave">
        <div class="auto-search" v-for="(auto,idx) in autoResult" :key="idx" @click="resultSelected(user.nickname)">
          <div class="auto-text" v-if="noResult==0">
            <div style="display: flex; align-items: center; justify-content: space-between; width: 20%">
              <i class="material-icons" style="font-size: 1.2em; margin-right: 5px">find_in_page</i>
              <div>{{auto.nickname}}</div>
            </div>
              <i class="material-icons" style="font-size: 1.2em">keyboard_arrow_right</i>
          </div>
        </div>
      </transition-group>

<!--      <div class="auto-search" v-else-if="searched === 1" >-->
<!--        <div  v-if="noResult === 0">-->
<!--          <div class="auto-text"   v-for="(auto,idx) in autoResult" :key="idx" >-->
<!--            <div @click="searchResultList">{{auto.nickname}}</div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
      <div class="search-result" v-else-if="searched === 2" >
        <div v-if="noResult === 0">
          <div>{{keyword}}의 사용자 검색 결과 입니다</div>
          <div class="search-result-list" v-for="(user,idx) in searchResult" :key="idx" @click="resultSelected(user.nickname)">
            <img v-if="user.picture" class="search-result-img" :src="`${user.picture}`" style="width: 70px" alt="">
            <img v-else src="../../assets/donut_profile.png" class="search-result-img" style="width: 70px" alt="">
            <div>{{user.nickname}}</div>
          </div>
        </div>
        <div v-else>
          <div>{{ keyword }}의 검색 결과가 없습니다.</div>
        </div>
      </div>
  </div>
</template>


<script>
import UserApi from "../../api/UserApi";
import {mapState} from "vuex";
import Velocity from 'velocity-animate'

export default {
  name: "Search",
  data: () => {
    return {
      searched: 0,
      noResult: 1,
      keyword: '',
    }
  },
  methods: {
    searchAutoList() {
      this.keyword = event.target.value
      let data = {
        nickname: this.keyword
      }
      this.searched = 1
      this.noResult = 1
      UserApi.searchAuto(
          data, res => {
            console.log(res.data)
            if (res.data !== 'NO_CONTENT')
              this.noResult = 0
              this.$store.dispatch('setAutoResult', res.data);
          },
          err => {console.log(err)}
      )
    },
    searchResultList () {
      let data ={
        nickname: this.keyword,
        offset: 0
      }
      // console.log(data)
      this.noResult = 0;
      UserApi.searchResult(
          data,
          res => {
            console.log(res)
            if (res.data !== 'NO_CONTENT')
              this.$store.dispatch('setSearchResult', res.data);
            else
              this.noResult = 1
              console.log(this.noResult)
          },
          err => {
            console.log(err)
          }
      )
      this.searched = 2
    },
    resultSelected (selectedNickname) {
      let data ={
        nickname : selectedNickname,
        id : this.loginUser.id
      }
      console.log(data)
      UserApi.saveSearch(
          data,
      res => {
            console.log(res)
            this.$router.push(`user/profile/${selectedNickname}`)

      },
          err => {
            console.log(err)
          }
      )

    },
    beforeEnter: function (el) {
      el.style.opacity = 0
      el.style.height = 0
    },
    enter: function (el, done) {
      const delay = el.dataset.index * 150
      setTimeout(function () {
        Velocity(
          el,
          { opacity: 1, height: '1.6em' },
          { complete: done }
        )
      }, delay)
    },
    leave: function (el, done) {
      const delay = el.dataset.index * 150
      setTimeout(function () {
        Velocity(
          el,
          { opacity: 0, height: 0 },
          { complete: done }
        )
      }, delay)
    }
  },
  computed: {
    ...mapState({
      // nickname: state => state.user.loginUser.nickname,
      loginUser: state => state.user.loginUser,
      recentSearch: state => state.user.recentSearch,
      searchResult: state => state.user.searchResult,
      autoResult: state => state.user.autoResult,
    })
  },

  created() {
    console.log('Let\'s search')
    UserApi.recentSearch(
        this.loginUser.id,
        res => {
          console.log(res)
          this.$store.dispatch('setRecentSearch', res.data)
        },
        err => {
          console.log(err)
        }
    )
    // if (!this.isLoggedIn) {
    //   this.$router.push({name: 'Login', params: {history: this.$route.fullPath}})
    //   return
    // }
    // // 페이지 로딩시 초기 정보 요청
    // // 비회원의 경우 0으로 요청 --> 백 확인 필요
    // this.setProfile()
    // console.log('생성인가 ')
  },


}
</script>

<style scoped>
.search-area{
  display: flex;
  align-items: center;
  border: black solid 1px;
  border-radius: 90em;
  height: 8vh;
  margin-top: 20px;
  width: 90%;
  margin-left: 5%;
}
.search-box {
  padding-left: 5vh;
  border-right: black solid;
  width: 80%
}

.search-button {
  text-align: center;
  width: 20%;
  padding-top: 1vh;
}

.search-result{
  margin-top: 50px;
}

.search-recent-list{
  text-decoration: none;
  color: black;
  /*text-align: left;*/
  margin-top: 10px;
  padding-bottom: 10px;
  border-bottom: #292929 dotted 1px;
  display: flex;
}
.search-recent-text{
  width: 100%;
  text-decoration: none;
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1em;
  font-family: GongGothiclight;

}
.search-transition-group{
  box-shadow: 0 5px 10px -5px rgba(0, 0, 0, 0.55);
  width: 60%;
  margin-left: 15%;
}

.auto-text {
  font-family: GongGothiclight;
  display: flex;
  /*padding-left: 5%;*/
  /*padding-top: 10px;*/
  padding: 2% 5%;
  justify-content: space-between;
  font-size: 0.8em;
  align-items: center;
  color: rgba(41, 41, 41, 0.8);
  width: 100%;
  /*padding-bottom: 5px;*/

}

.search-result-list{
  display: flex;
  justify-content: left;
  align-items: center;
  border-top: #f1a64b solid 1px;
  border-bottom: #f1a64b solid 1px;
  margin: 10px 5px;
  padding: 10px 0;

}

.search-result-img{
  border: rgba(41, 41, 41, 0.60) solid 1px;
  border-radius: 40px;
  padding: 3px 1px;
  margin-right: 3px;
}
</style>