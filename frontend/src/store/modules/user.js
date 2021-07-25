import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'
//

const state ={
    userNickname: ''
}

const getters = {

}

const mutations = {
  SET_USER(state, nickname) {
    state.userNickname = nickname
  },
}

const actions = {
    // 로그인된 유저 정보 저장
    login({commit, dispatch}, res) {
        commit('SET_NICKNAME', res.data.nickname)
        cookies.set('nickname', res.data.nickname, '2d')
    }
}



export default {
    state, getters, mutations, actions
}