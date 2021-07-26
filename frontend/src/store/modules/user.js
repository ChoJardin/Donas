import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'
//

const state ={
  loginUser: {
    id: '',
    nickname:'test'
  }
}

const getters = {

}

const mutations = {
  SET_USER(state, loginUser) {
    state.loginUser = loginUser
    // 혹시 나눠서 저장이 필요한 경우
    // state.loginUser.id = loginUser.id
    // state.loginUser.nickname = loginUser.nickname
  },
}

const actions = {
    // 로그인된 유저 정보 저장
    login({commit, dispatch}, res) {
        commit('SET_USER', res.data)
        cookies.set('login-user', res.data, '2d')
    }
}



export default {
    state, getters, mutations, actions
}