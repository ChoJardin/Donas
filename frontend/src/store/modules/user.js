import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
  loginUser: ''
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
  }


}



export default {
  state, getters, mutations, actions
}