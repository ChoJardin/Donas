import axios from "axios";

const URL = 'http://localhost:8081'
const ROUTES = {
  main: '/main',
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

export default CommonApi