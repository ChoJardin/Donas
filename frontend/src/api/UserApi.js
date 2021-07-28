import axios from "axios";
// import store from '@/store/modules/user'

const URL = 'http://localhost:8081'
const ROUTES = {
  signup: '/user/signup',
  login: '/user/signin',
}

// 로그인
const requestLogin = (data,callback,errorCallback) => {
  const path = URL + ROUTES.login
  axios.post(path, data)
    .then(res => {
      callback(res)
    })
    .catch(error => {
      errorCallback(error)
    })
}

// 회원가입
const requestSignup = (data, callback, errorCallback) => {
  const signupPath = URL + ROUTES.signup
  axios.post(signupPath, data)
    .then(res => {
      callback(res)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

const UserApi = {
  URL,
  ROUTES,
  requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
  requestSignup:(data,callback,errorCallback)=>requestSignup(data,callback,errorCallback),
}

export default UserApi