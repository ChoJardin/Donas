// import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
    // feeds: [{
    //     id: '',
    //     image: '',
    //     content: ''
    // }]
    // 테스트용
    feeds: [
      {
        id: '1',
        image: '',
        content: '123'
      },
      {
        id: '2',
        image: '',
        content: '456'
      },
      {
        id: '3',
        image: '',
        content: '789'
      },
    ],
    selectedArticle: {}
}

const getters = {
  // getArticle(state) {
  //   if (this.$route.)
  // }
}

const mutations = {
  // 게시글 전체 정보 저장
  SET_FEEDS(state, articles) {
    state.feeds = articles
  }
}

const actions = {
  // 게시글 전체 정보 저장
  setFeeds({commit}, articles) {
    commit('SET_FEEDS', articles)
  }
}

export default {
    state, getters, mutations, actions
}