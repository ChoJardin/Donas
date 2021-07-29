import axios from "axios";
// import store from '@/store/modules/user'

const URL = 'http://localhost:8081'
const ROUTES = {
  login: '/user/signin',
  checkNickname: '/user/nickname',
  checkEmail: '/user/email',
  signup: '/user/signup',
  profileInfo: '/profile/',
  follower: '/profile/follower',
  following: '/profile/following',

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

// 닉네임 중복 확인
const checkNickname = (params, callback, errorCallback) => {
  const checkNicknamePath = URL + ROUTES.checkNickname
  axios.get(checkNicknamePath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 이메일 중복 확인
const checkEmail = (params, callback, errorCallback) => {
  const checkEmailPath = URL + ROUTES.checkEmail
  axios.get(checkEmailPath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 회원가입
const requestSignup = (data, callback, errorCallback) => {
  const signupPath = URL + ROUTES.signup
  axios.post(signupPath, data)
    .then(res => {
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

// 프로필 정보 요청
const requestProfileInfo = (nickname, callback, errorCallback) => {
  const profileInfoPath = URL + ROUTES.profileInfo + nickname
  axios.get(profileInfoPath)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 팔로워 리스트
const requestFollower = (params, callback, errorCallback) => {
  const followerListPath = URL + ROUTES.follower
  axios.get(followerListPath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 팔로잉 리스트
const requestFollowing = (params, callback, errorCallback) => {
  const followingListPath = URL + ROUTES.following
  axios.get(followingListPath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

const UserApi = {
  URL,
  ROUTES,
  requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
  checkNickname:(data,callback,errorCallback)=>checkNickname(data,callback,errorCallback),
  checkEmail:(data,callback,errorCallback)=>checkEmail(data,callback,errorCallback),
  requestSignup:(data,callback,errorCallback)=>requestSignup(data,callback,errorCallback),
  requestProfileInfo:(data,callback,errorCallback)=>requestProfileInfo(data,callback,errorCallback),
  requestFollower:(data,callback,errorCallback)=>requestFollower(data,callback,errorCallback),
  requestFollowing:(data,callback,errorCallback)=>requestFollowing(data,callback,errorCallback),
}

export default UserApi