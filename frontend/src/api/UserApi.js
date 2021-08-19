import axios from "axios";
// import store from '@/store/modules/user'

// const URL = 'http://localhost:8081/api'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  logout: '/user/signout',
  login: '/user/signin',
  loginUser: '/user/mypage/',
  checkNickname: '/user/nickname',
  checkEmail: '/user/email',
  signup: '/user/signup',
  profileInfo: '/profile/',
  follow: '/profile/follow',
  followers: '/profile/followers',
  followings: '/profile/followings',
  updateProfile: '/user/profile/',
  updatePassword: '/user/password',

  commonAlerts: '/alarm/',
  questAlerts: '/alarm/quest/',
  alertStatus: '/alarm',
  answerAlert: '/alarm/group',
  // declineAlert: '/alarm/group',

  recentSearch: '/search/recent/',
  searchAuto: '/search/auto',
  searchResult: '/search/result',
  saveSearch: '/search/add'

}
// 로그아웃
const requestLogout = (data, callback, errorCallback) => {
  const logoutPath = URL + ROUTES.logout
  axios.delete(logoutPath, {data: data})
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 로그인
const requestLogin = (data, callback, errorCallback) => {
  const path = URL + ROUTES.login
  axios.post(path, data)
    .then(res => callback(res))
    .catch(error => errorCallback(error))
}

// 로그인 사용자 정보 업데이트
// const requestLoginUser = (id, callback, errorcallback) => {
//   const requestLoginUserPath = URL + ROUTES.loginUser + id
//   // console.log(requestLoginUserPath)
//   axios.get(requestLoginUserPath)
//     .then(res => callback(res))
//     .catch(err => errorcallback(err))

// }

const requestLoginUser = (id, callback, errorCallback) => {
  const requestLoginUserPath = URL + ROUTES.loginUser + id
  axios.get(requestLoginUserPath)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 닉네임 중복 확인
const checkNickname = (params, callback, errorCallback) => {
  const checkNicknamePath = URL + ROUTES.checkNickname
  axios.get(checkNicknamePath, { params: params })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 이메일 중복 확인
const checkEmail = (params, callback, errorCallback) => {
  const checkEmailPath = URL + ROUTES.checkEmail
  axios.get(checkEmailPath, { params: params })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 회원가입
const requestSignup = (data, callback, errorCallback) => {
  const signupPath = URL + ROUTES.signup
  axios.post(signupPath, data)
    .then(res => callback(res.data))
    .catch(error => errorCallback(error.response.data))
}

// 프로필 정보 요청
const requestProfileInfo = (nickname, params, callback, errorCallback) => {
  const profileInfoPath = URL + ROUTES.profileInfo + nickname
  axios.get(profileInfoPath, { params: params })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}


// 프로필 정보 수정
const updateProfile = (id, data, callback, errorcallback) => {
  const updateProfilePath = URL + ROUTES.updateProfile + id
  axios.patch(updateProfilePath, data)
    .then(res => callback(res))
    .catch(err => errorcallback(err))
}

// 비밀번호 변경
const updatePassword = (data, callback, errorcallback) => {
  const updatePasswordPath = URL + ROUTES.updatePassword
  axios.post(updatePasswordPath, data)
    .then(res => callback(res))
    .catch(err => errorcallback(err))
}

// 팔로워 리스트
const requestFollowers = (params, callback, errorCallback) => {
  const followerListPath = URL + ROUTES.followers
  axios.get(followerListPath, { params: params })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 팔로잉 리스트
const requestFollowings = (params, callback, errorCallback) => {
  const followingListPath = URL + ROUTES.followings
  axios.get(followingListPath, { params: params })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 팔로우 요청
const requestFollow = (isPost, data, callback, errorCallback) => {
  const followPath = URL + ROUTES.follow
  if (isPost) {
    axios.post(followPath, data)
      .then(res => callback(res))
      .catch(err => errorCallback(err))
  } else {
    axios.delete(followPath, { params: data })
      .then(res => callback(res))
      .catch(err => errorCallback(err))
  }
}

//알림 리스트 불러오기
const requestCommonAlert = (id, callback, errorCallback) => {
  const CommonAlertPath = URL + ROUTES.commonAlerts + id
  axios.get(CommonAlertPath)
    .then(res => callback(res))
    .catch(err => errorCallback(err))

}

const requestQuestAlert = (id, callback, errorCallback) => {
  const QuestAlertPath = URL + ROUTES.questAlerts + id
  axios.get(QuestAlertPath)
    .then(res => callback(res))
    .catch(err => errorCallback(err))

}

const updateAlertStatus = (data, callback, errorcallback) => {
  const updateAlertPath = URL + ROUTES.alertStatus
  axios.patch(updateAlertPath, data)
    .then(res => callback(res))
    .catch(err => errorcallback(err))
}

const acceptAlert = (data, callback, errorCallback) => {
  const acceptAlertPath = URL + ROUTES.answerAlert
  axios.post(acceptAlertPath, data)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

const declineAlert = (data, callback, errorCallback) => {
  const declineAlertPath = URL + ROUTES.answerAlert
  axios.patch(declineAlertPath, null, { params: data })
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

const kakaoLogin = async (code) => {
  const Host = "https://kauth.kakao.com"
  const tokenRequestPath = '/oauth/token'
  const REST_API_KEY = '67852745621896a093fa2abcffbd1275'
  // const REDIRECT_URI = 'https://i5a603.p.ssafy.io/login'

  const header = {
    'Authorization': '406aeac2c2a0e7bcd7940764bc4af504',
    'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
  }

  const data = {
    grant_type: 'authorization_code',
    client_id: REST_API_KEY,
    redirect_uri: 'http://localhost:8080/login',
    code: code
  }
  const queryString = Object.keys(data)
    .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
    .join('&')
  console.log(queryString)
  const result = await axios.post(Host + tokenRequestPath, queryString, { headers: header })
  // .then(res=>console.log(res))
  // .catch(err => console.log(err))
  console.log(result)



}

const recentSearch = (id, callback, errorCallback) => {
  const recentSearchPath = URL + ROUTES.recentSearch + id
  axios.get(recentSearchPath)
    .then(res => { callback(res) })
    .catch(err => { errorCallback(err) })
}

const searchAuto = (data, callback, errorCallback) => {
  const searchAutoPath = URL + ROUTES.searchAuto
  axios.get(searchAutoPath, {params: data})
    
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}


const searchResult = (data,callback, errorCallback) => {
  const searchResultPath = URL + ROUTES.searchResult
  console.log(searchResultPath)
  axios.get(searchResultPath,  {params: data})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

const saveSearch = (data, callback, errorCallback) => {
  const saveSearchPath = URL + ROUTES.saveSearch
  axios.post(saveSearchPath, data)
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
  requestLogout: (data, callback, errorCallback) => requestLogout(data, callback, errorCallback),
  requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
  checkNickname: (data, callback, errorCallback) => checkNickname(data, callback, errorCallback),
  checkEmail: (data, callback, errorCallback) => checkEmail(data, callback, errorCallback),
  requestSignup: (data, callback, errorCallback) => requestSignup(data, callback, errorCallback),
  requestProfileInfo: (data, params, callback, errorCallback) => requestProfileInfo(data, params, callback, errorCallback),
  requestFollowers: (data, callback, errorCallback) => requestFollowers(data, callback, errorCallback),
  requestFollowings: (data, callback, errorCallback) => requestFollowings(data, callback, errorCallback),
  requestFollow: (isPost, data, callback, errorCallback) => requestFollow(isPost, data, callback, errorCallback),
  requestLoginUser: (id, callback, errorCallback) => requestLoginUser(id, callback, errorCallback),
  // requestLoginUser:(id)=>requestLoginUser(id),
  updateProfile: (id, data, callback, errorCallback) => updateProfile(id, data, callback, errorCallback),
  updatePassword: (data, callback, errorCallback) => updatePassword(data, callback, errorCallback),

  requestCommonAlert: (data, callback, errorCallback) => requestCommonAlert(data, callback, errorCallback),
  requestQuestAlert: (data, callback, errorCallback) => requestQuestAlert(data, callback, errorCallback),
  updateAlertStatus: (data, callback, errorCallback) => updateAlertStatus(data, callback, errorCallback),
  acceptAlert: (data, callback, errorCallback) => acceptAlert(data, callback, errorCallback),
  declineAlert: (params, callback, errorCallback) => declineAlert(params, callback, errorCallback),

  recentSearch: (id, callback, errorCallback) => recentSearch(id, callback, errorCallback),
  searchAuto: (data, callback, errorCallback) => searchAuto(data, callback, errorCallback),
  searchResult: (data, callback, errorCallback) => searchResult(data, callback, errorCallback),
  saveSearch: (data, callback, errorCallback) => saveSearch(data, callback, errorCallback),

}

export default UserApi
