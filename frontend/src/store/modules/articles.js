// import cookies from 'vue-cookies'
// import axios from 'axios'
// import routes from './routes'

const state ={
  // feeds: [
  //   {
  //     articleId: 0,
  //     commentCnt: 0,
  //     content: "",
  //     createdAt: "",
  //     image: "",
  //     likeCnt: 0,
  //     makerImage: null,
  //     makerName: "",
  //     questId: 0,
  //     questTitle: "",
  //     type: "",
  //     updatedAt: null
  //   }
  // ],
  // 테스트용
  feeds: [
    {
      articleId: '1',
      image: '',
      content: '123'
    },
    {
      articleId: '2',
      image: '',
      content: '456'
    },
    {
      articleId: '3',
      image: '',
      content: '789'
    },
  ],
  selectedId: 0,
}

const getters = {
  selectedArticle: state => {
    return state.feeds.find(article => article.articleId === Number(state.selectedId))
  }
}

const mutations = {
  // 게시글 전체 정보 저장
  SET_FEEDS(state, articles) {
    state.feeds = articles
  },
  // 게시물 상세보기 선택
  SET_SELECTED_ID(state, articleId) {
    state.selectedId = articleId
  }
}

const actions = {
  // 게시글 전체 정보 저장
  setFeeds({commit}, articles) {
    commit('SET_FEEDS', articles)
  },
  // 게시물 상세보기 선택
  setSelectedId({commit}, articleId) {
    commit('SET_SELECTED_ID', articleId)
  }
}

export default {
    state, getters, mutations, actions
}