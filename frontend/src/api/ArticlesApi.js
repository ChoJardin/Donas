import axios from "axios";

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081/api'
const ROUTES = {
  articleDetail: '/main',
}

// 메인페이지 초기 정보
const requestMainInfo = (callback, errorCallback) => {
  const path = URL + ROUTES.main
  axios.get(path)
    .then(res => {
      callback(res.data)
    })
    .catch(err => {
      errorCallback(err)
    })
}


const CommonApi = {
  URL,
  ROUTES,
  requestMainInfo:(data, callback, errorCallback)=>requestMainInfo(data, callback, errorCallback),
}

export default ArticlesApi