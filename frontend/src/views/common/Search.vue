<template>
  <div>
    <div class="search-area">
<!--      <input class="search-box" type="text" @input="searchAutoList"  @change="searchResultList">-->
      <input class="search-box" type="text" @input="searchAutoList">
      <button class="search-button"  @click="searchResultList"><i class="material-icons">search</i></button>
    </div>
      <div class="search-result" v-if="searched === 0">
        <div style="padding-bottom: 40px; border-bottom: #292929 dotted 1px">{{loginUser.nickname}}님의 최근 사용자 검색 결과 입니다.</div>
        <div class="search-recent-list" v-for="(recent,idx) in recentSearch" :key="idx" >
          <router-link class="search-recent-text" :to="`/user/profile/${recent.nickname}`">{{recent.nickname}}</router-link>
        </div>
      </div>
      <div class="auto-search" v-else-if="searched === 1" >
        <div  v-if="noResult === 0">
          <div class="auto-text"   v-for="(auto,idx) in autoResult" :key="idx" >
            <div @click="searchResultList">{{auto.nickname}}</div>
          </div>
        </div>
      </div>
      <div class="search-result" v-else-if="searched === 2" >
        <div v-if="noResult === 0">
          <div>{{keyword}}의 사용자 검색 결과 입니다</div>
          <div class="search-result-list" v-for="(user,idx) in searchResult" :key="idx" @click="resultSelected(user.nickname)">
            <div><img class="search-result-img" :src="`${user.picture}`" style="width: 70px" alt=""></div>
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
export default {
  name: "Search",
  data: () => {
    return {
      searched: 0,
      noResult: 0,
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
      this.noResult = 0;
      UserApi.searchAuto(
          data, res => {
            console.log(res.data)
            if (res.data !== 'NO_CONTENT')
              this.$store.dispatch('setAutoResult', res.data);
            else
              this.noResult = 1
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
}
.search-recent-text{
  width: 100%;
  text-decoration: none;
  color: black;

}

.auto-search {
  text-align: left;
  padding-left: 50px;
  padding-top: 10px;
  border-bottom: #292929 solid 1.5px;
  border-left: #292929 solid 1.5px;
  border-right: #292929 solid 1.5px;
  border-radius: 0 0 15px 15px;
  width: 70%;
  margin-left: 8%;
  font-size: 1.1em;
}

.auto-text {
  padding-bottom: 8px;
  border-bottom: #292929 dotted 1px;
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
  padding: 3px;
}
</style>