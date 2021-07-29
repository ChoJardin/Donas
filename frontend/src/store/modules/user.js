import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
  loginUser: '',
  selectedProfile: {
    id: '',
    nickname:'',
    picture: '', // 이미지 경로
    description: '',
    quests: [
      {
         // 진행중인 퀘스트
      }
    ],
    follower: '',  // 팔로워 수
    following: '',  // 팔로잉 수
    isFollowing: false,  // 팔로워인지 여부
    articles: [
     {
        //해당 유저의 모든 게시물
     }
   ]
  },
}


const getters = {
  isLoggedIn(state) {
    return !!state.loginUser
  }
}

const mutations = {
  // 로그인 유저 정보 저장
  SET_USER(state, loginUser) {
    state.loginUser = loginUser
  },
  // 로그아웃
  LOGOUT(state) {
    state.loginUser = ''
    cookies.remove('login-user')
  },
  // 선택된 유저의 프로필 정보 저장
  SET_USER_PROFILE(state, profile) {
    state.selectedProfile = profile
  }
}


const actions = {
  // 로그인된 유저 정보 저장
  login({commit, dispatch}, {id, nickname, questCnt}) {
    let user = {id: id, nickname: nickname, questCnt: questCnt}
    commit('SET_USER', user)
    cookies.set('login-user', user, '2d')
  },
  // 로그아웃
  logout({commit}) {
    commit('LOGOUT')
  },
  // 선택된 유저의 프로필 정보 저장
  setUserProfile({commit}, profile) {
    delete profile.articles
    commit('SET_USER_PROFILE', profile)
  }


}



export default {
  state, getters, mutations, actions
}