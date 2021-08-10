import axios from "axios";
// import store from '@/store/modules/user'

// const URL = 'http://localhost:8081/api'
const URL = 'http://i5a603.p.ssafy.io:8081/api/'
const ROUTES = {
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

  alerts: '/alarm/',
  alertStatus:'/alarm',


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

// 로그인 사용자 정보 업데이트
const requestLoginUser = (id, callback, errorcallback) => {
  const requestLoginUserPath = URL + ROUTES.loginUser + id
  // console.log(requestLoginUserPath)
  axios.get(requestLoginUserPath)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorcallback(err)
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
const requestProfileInfo = (nickname, params, callback, errorCallback) => {
  const profileInfoPath = URL + ROUTES.profileInfo + nickname
  axios.get(profileInfoPath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}


// 프로필 정보 수정
const updateProfile = (id, data, callback, errorcallback) => {
  const updateProfilePath = URL + ROUTES.updateProfile + id
  axios.patch(updateProfilePath, data)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorcallback(err)
    })
}

// 비밀번호 변경
const updatePassword = (data, callback, errorcallback) => {
  const updatePasswordPath = URL + ROUTES.updatePassword
  axios.post(updatePasswordPath, data)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorcallback(err)
    })
}

// 팔로워 리스트
const requestFollowers = (params, callback, errorCallback) => {
  const followerListPath = URL + ROUTES.followers
  axios.get(followerListPath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 팔로잉 리스트
const requestFollowings = (params, callback, errorCallback) => {
  const followingListPath = URL + ROUTES.followings
  axios.get(followingListPath, {params: params})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 팔로우 요청
const requestFollow = (post, data, callback, errorCallback) => {
  const followPath = URL + ROUTES.follow
  if (post) {
    axios.post(followPath, data)
      .then(res => {
        callback(res)
      })
      .catch(err => {
        errorCallback(err)
      })
  } else {
    axios.delete(followPath, {params: data})
      .then(res => {
        callback(res)
      })
      .catch(err => {
        errorCallback(err)
      })
  }
}

//알림 리스트 불러오기
const requestAlertList = (id, callback, errorCallback) => {
  const AlertListPath = URL + ROUTES.alerts + id
  axios.get(AlertListPath)
    .then(res => {
      // console.log(res)
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })

}

const updateAlertStatus = (data, callback, errorcallback) => {
  const updateAlertPath = URL + ROUTES.alertStatus
  axios.patch(updateAlertPath, data)
    .then(res => {
      // console.log(res)
      callback(res)
    })
    .catch(err => {
      errorcallback(err)
    })
}




const UserApi = {
  URL,
  ROUTES,
  requestLogin:(data, callback, errorCallback)=>requestLogin(data, callback, errorCallback),
  checkNickname:(data, callback, errorCallback)=>checkNickname(data, callback, errorCallback),
  checkEmail:(data, callback, errorCallback)=>checkEmail(data, callback, errorCallback),
  requestSignup:(data, callback, errorCallback)=>requestSignup(data, callback, errorCallback),
  requestProfileInfo:(data, params, callback, errorCallback)=>requestProfileInfo(data, params, callback, errorCallback),
  requestFollowers:(data, callback, errorCallback)=>requestFollowers(data, callback, errorCallback),
  requestFollowings:(data, callback, errorCallback)=>requestFollowings(data, callback, errorCallback),
  requestFollow:(post, data, callback, errorCallback)=>requestFollow(post, data, callback, errorCallback),
  requestLoginUser:(id, callback, errorCallback)=>requestLoginUser(id, callback, errorCallback),
  updateProfile:(id, data, callback, errorCallback)=>updateProfile(id, data, callback, errorCallback),
  updatePassword:(data, callback, errorCallback)=>updatePassword(data, callback, errorCallback),

  requestAlertList:(data, callback, errorCallback) => requestAlertList(data,callback, errorCallback),
  updateAlertStatus:(data, callback, errorCallback) => updateAlertStatus(data,callback, errorCallback),


}



export default UserApi