// import cookies from 'vue-cookies'
// import axios from 'axios'
// import routes from './routes'
import router from "@/router";

const state ={
  feeds: [
    {
      id: 0,
      createdAt: "",
      updatedAt: null,
      image: "",
      content: "",
      type: "",
      isLike: false,
      heartCnt: 0,
      commentCnt: 0,
      makerImage: null,
      makerName: "",
      questId: 0,
      questTitle: "",
    }
  ],
  // 테스트용
  // feeds: [
  //   {
  //     id: 1,
  //     createdAt: '2021-08-09T13:25:45',
  //     updatedAt: null,
  //     image: "image-route",
  //     content: "내용1입니다",
  //     type: "G",
  //     isLike: false,
  //     heartCnt: 2,
  //     commentCnt: 3,
  //     makerImage: null,
  //     makerName: "cho",
  //     questId: 1,
  //     questTitle: "1일1알고",
  //   },
  //   {
  //     id: 2,
  //     createdAt: '2021-08-09T17:21:45',
  //     updatedAt: null,
  //     image: "image-route",
  //     content: "내용2입니다",
  //     type: "P",
  //     isLike: false,
  //     heartCnt: 1,
  //     commentCnt: 5,
  //     makerImage: null,
  //     makerName: "cho",
  //     questId: 1,
  //     questTitle: "1일1알고",
  //   },
  //   {
  //     id: 3,
  //     createdAt: '2021-04-09T13:25:45',
  //     updatedAt: null,
  //     image: "image-route",
  //     content: "내용3입니다",
  //     type: "R",
  //     isLike: true,
  //     heartCnt: 2,
  //     commentCnt: 3,
  //     makerImage: null,
  //     makerName: "cho",
  //     questId: 1,
  //     questTitle: "1일1알고",
  //   },
  // ],
  selectedId: 0,
  selectedArticle:
    {
      id: 0,
      createdAt: "",
      updatedAt: null,
      image: "",
      content: "",
      type: "",
      isLike: false,
      heartCnt: 0,
      commentCnt: 0,
      makerImage: null,
      makerName: "",
      questId: 0,
      questTitle: "",
    },
  // 상세 페이지에서,
  // 게시글 좋아요 누른 이용자 목록
  likeList: [],
  // 댓글목록
  commentList: [],

}

const getters = {
  isArticleSelected: state => {
    return state.selectedId
  },

  // selectedArticle: state => {
  //   return state.feeds.find(article => article.id === Number(state.selectedId))
  // },
  likeUser(state) {
    if (state.likeList.length)
      return state.likeList[0].nickname
  },
  likeCnt(state) {
    return state.selectedArticle.likeCnt
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
  },
  // 게시물 상세 정보 등록
  SET_SELECTED_ARTICLE(state, article) {
    state.selectedArticle = article
  },
  // 게시물 상세 좋아요 목록
  SET_LIKE_LIST(state, likeList) {
    state.likeList = likeList
  },
  // 게시물 상세 댓글 목록
  SET_COMMENT_LIST(state, commentList) {
    state.commentList = commentList
  },
  // 작성한 게시글 피드에 추가
  ADD_NEW_ARTICLE(state, articles) {
    state.feeds = articles
  },
  // 좋아요
  ADD_LIKE(state, idx) {
    state.feeds[idx].like = true
    state.feeds[idx].heartCnt += 1
  },
  // 좋아요 취소
  DELETE_LIKE(state, idx) {
    state.feeds[idx].like = false
    state.feeds[idx].heartCnt -= 1
  }
}

const actions = {
  // 게시글 전체 정보 저장
  setFeeds({commit}, articles) {
    commit('SET_FEEDS', articles)
  },
  // 게시물 상세보기 선택
  setArticleById({commit, state, dispatch}, articleId) {
    const selectedArticle = state.feeds.find(article =>
      article.id === Number(articleId))
    dispatch('setSelectedArticle', selectedArticle)
    dispatch('setSelectedId', articleId)
  },
  // 상세 정보 업데이트
  setSelectedArticle({commit}, article) {
    commit('SET_SELECTED_ARTICLE', article)
  },
  setSelectedId({commit}, id) {
    commit('SET_SELECTED_ID', id)
  },
  // 상세 페이지 들어갔을 때,
  setArticleDetail({dispatch}, {likeList, commentList}) {
    dispatch('setLikeList', likeList)
    dispatch('setCommentList', commentList)
  },
  setCommentList({commit}, commentList) {
    commit('SET_COMMENT_LIST', commentList)
  },
  setLikeList({commit}, likeList) {
    commit('SET_LIKE_LIST', likeList)
  },
  // 작성한 게시글 피드에 추가
  addNewArticle({commit, state}, article) {
    const articles = [article, ...state.feeds]
    commit('SET_SELECTED_ARTICLE', article)
    commit('ADD_NEW_ARTICLE', articles)
  },
  // 수정한 게시물 교체
  replaceOldArticle({commit, state}, {id, content}) {
    const articles = state.feeds
    articles.forEach(article => {
      if (article['id'] === id)
        article['content'] = content
    }, articles)
    commit('SET_FEEDS', articles)
  },
  // 좋아요
  setLike({commit, state}, {isLike, articleId}) {
    const idx = state.feeds.findIndex(article => article.id === articleId)
    // 좋아요 취소
    if (isLike)
      commit('DELETE_LIKE', idx)
    // 좋아요
    else commit('ADD_LIKE', idx)
  }

}

export default {
    state, getters, mutations, actions
}