import axios from "axios";

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  like: '/like',
  articleCommentList: '/comment',
}

// 게시글 상세 페이지 정보
// 좋아요 리스트/ 댓글 리스트
const requestArticleDetailInfo = async (id) => {
  const articleLikeListPath = URL + ROUTES.like + `/${id}`
  const articleCommentListPath = URL + ROUTES.articleCommentList + `/${id}`

  const commentList = await requestCommentList(articleCommentListPath)
  const likeList = await requestLikeList(articleLikeListPath)

  return {commentList: commentList, likeList: likeList}
}
// 댓글 리스트
const requestCommentList = async (path) => {
  let commentList
  commentList = await axios.get(path)
    .then(res => res.data)
    .catch(err => 'error')
  return commentList

}
// 좋아요 리스트
const requestLikeList = async (path) => {
  let likeList
  likeList = await axios.get(path)
    .then(res => res.data)
    .catch(err => 'error')
  return likeList
}

//좋아요 요청
const requestLike = (isPost, data, callback, errorCallback) => {
  const likePath = URL + ROUTES.like
  if (isPost) {
    axios.post(likePath, data)
      .then(res => callback(res))
      .catch(err => errorCallback(err))
  } else {
    axios.delete(likePath, {params: data})
      .then(res => callback(res))
      .catch(err => errorCallback(err))
  }
}




const ArticlesApi = {
  URL,
  ROUTES,
  requestArticleDetailInfo:(id)=>requestArticleDetailInfo(id),
  requestLike:(isPost, data, callback, errorCallback) => requestLike(isPost, data, callback, errorCallback),
}

export default ArticlesApi