import axios from "axios";

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  like: '/like',
  comment: '/comment',
  article: '/article'
}

// 게시글 상세 페이지 정보
// 좋아요 리스트/ 댓글 리스트
const requestArticleDetailInfo = async (id) => {
  const commentList = await requestCommentList(id)
  const likeList = await requestLikeList(id)

  return {commentList: commentList, likeList: likeList}
}
// 댓글 리스트
const requestCommentList = async (id) => {
  const articleCommentListPath = URL + ROUTES.comment + `/${id}`
  let commentList
  commentList = await axios.get(articleCommentListPath)
    .then(res => res.data)
    .catch(err => 'error')
  return commentList

}
// 좋아요 리스트
const requestLikeList = async (id) => {
  const articleLikeListPath = URL + ROUTES.like + `/${id}`
  let likeList
  likeList = await axios.get(articleLikeListPath)
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

// 댓글 달기
const createComment = async (data) => {
  console.log(data)
  const createCommentPath = URL + ROUTES.comment
  let result
  result = axios.post(createCommentPath, data)
    .then(res => res.data)
    .catch(err => err)
  return result
}

// 댓글 수정
const updateComment = async (data) => {
  console.log(data)
  const updateCommentPath = URL + ROUTES.comment
  let result
  result = axios.patch(updateCommentPath, data)
    .then(res => res.data)
    .catch(err => 'error')
  return result
}

// 댓글 삭제
const deleteComment = async (data) => {
  const deleteCommentPath = URL + ROUTES.comment
  let result
  result = axios.delete(deleteCommentPath, {params: data})
    .then(res => res.data)
    .catch(err => 'error')
  return result
}

// 게시글 작성
const createArticle = async (data, callback, errorCallback) => {
  const createArticlePath = URL + ROUTES.article
  axios.post(createArticlePath, data)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 게시글 수정
const editArticle = async (data, callback, errorCallback) => {
  const editArticlePath = URL + ROUTES.article
  axios.patch(editArticlePath, data)
    .then(res => callback(res))
    .catch(err => errorCallback('error'))
}

const ArticlesApi = {
  URL,
  ROUTES,
  requestArticleDetailInfo:(id)=> requestArticleDetailInfo(id),
  requestCommentList:(id) => requestCommentList(id),
  requestLike:(isPost, data, callback, errorCallback) => requestLike(isPost, data, callback, errorCallback),
  createComment:(data) => createComment(data),
  updateComment:(data) => updateComment(data),
  deleteComment:(data) => deleteComment(data),
  createArticle:(data, callback, errorCallback) => createArticle(data, callback, errorCallback),
  editArticle:(data, callback, errorCallback) => editArticle(data, callback, errorCallback),
}

export default ArticlesApi