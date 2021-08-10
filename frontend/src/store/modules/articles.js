// import cookies from 'vue-cookies'
// import axios from 'axios'
// import routes from './routes'

const state ={
  // feeds: [
  //   {
  //     id: 0,
  //     createdAt: "",
  //     updatedAt: null,
  //     image: "",
  //     content: "",
  //     type: "",
  //     isLike: false,
  //     heartCnt: 0,
  //     commentCnt: 0,
  //     makerImage: null,
  //     makerName: "",
  //     questId: 0,
  //     questTitle: "",
  //   }
  // ],
  // 테스트용
  feeds: [
    {
      id: 1,
      createdAt: '2021-08-09T13:25:45',
      updatedAt: null,
      image: "image-route",
      content: "내용1입니다",
      type: "G",
      isLike: false,
      heartCnt: 2,
      commentCnt: 3,
      makerImage: null,
      makerName: "cho",
      questId: 1,
      questTitle: "1일1알고",
    },
    {
      id: 2,
      createdAt: '2021-08-09T17:21:45',
      updatedAt: null,
      image: "image-route",
      content: "내용2입니다",
      type: "P",
      isLike: false,
      heartCnt: 1,
      commentCnt: 5,
      makerImage: null,
      makerName: "cho",
      questId: 1,
      questTitle: "1일1알고",
    },
    {
      id: 3,
      createdAt: '2021-04-09T13:25:45',
      updatedAt: null,
      image: "image-route",
      content: "내용3입니다",
      type: "R",
      isLike: true,
      heartCnt: 2,
      commentCnt: 3,
      makerImage: null,
      makerName: "cho",
      questId: 1,
      questTitle: "1일1알고",
    },
  ],
  selectedId: 0,
}

const getters = {
  selectedArticle: state => {
    return state.feeds.find(article => article.id === Number(state.selectedId))
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