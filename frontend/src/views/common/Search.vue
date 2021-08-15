<template>
  <div>
    <div class="search-area">
      <input class="search-box" type="text" :value="keyword">
      <button class="search-button" @click="searchResult()"><i class="material-icons">search</i></button>
    </div>

    <div>
      <div v-for="user in recentSearch" :key="user.id">
        {{user.nickname}}
      </div>
    </div>
  </div>
</template>

<script>
import UserApi from "../../api/UserApi";
import {mapState} from "vuex";
export default {
  name: "Search",
  data: () => {
    return {
      keyword: '',
    }
  },
  methods: {
    searchResult () {
      let data ={
        nickname: this.keyword,
        offset: 0
      }
      console.log(data)
      UserApi.searchResult(
          data,
          res => {
            this.$store.dispatch('setSearchResult', res.data)
          },
          err => {
            console.log(err)
          }
      )
    }
  },
  computed: {
    ...mapState({
      // nickname: state => state.user.loginUser.nickname,
      loginUser: state => state.user.loginUser,
      recentSearch: state => state.user.recentSearch,
      searchResult: state => state.user.searchResult,
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
  border: black solid;
  border-radius: 90em;
  height: 8vh;
  margin-top: 20px;
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
</style>