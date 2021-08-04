import axios from "axios";
// import store from '@/store/modules/user'

const URL = 'http://localhost:8081'
const ROUTES = {
  login: '/user/signin',
  checkNickname: '/user/nickname',
  checkEmail: '/user/email',
  signup: '/user/signup',
  profileInfo: '/profile/',
  follow: '/profile/follow',
  follower: '/profile/follower',
  following: '/profile/following',
  myPage: '/user/mypage/',
  updateProfile: '/user/profile/',
  updatePassword: '/user/password'
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

// 정보수정 페이지 초기 요청
const requestMyPage = (id, callback, errorcallback) => {
  const requestMyPagePath = URL + ROUTES.myPage + id
  axios.get(requestMyPagePath)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorcallback(err)
    })
}

// 프로필 정보 수정
const updateProfile = (id, data, callback, errorcallback) => {
  const updateProfilePath = URL + ROUTES.updateProfile + id
  axios.patch(updateProfilePath, data)
    .then(res => {
      console.log(res)
      callback(res)
    })
    .catch(err => {
      console.log('------')
      console.log(err)
      errorcallback(err)
    })
}

// 비밀번호 변경
const updatePassword = (data, callback, errorcallback) => {
  const updatePasswordPath = URL + ROUTES.updatePassword
  axios.patch(updatePasswordPath, data)
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorcallback(err)
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

// 팔로우 요청
const requestFollow = (post, data, callback, errorCallback) => {
  const followPath = URL + ROUTES.follow
  if (post) {
    console.log('팔로우')
    console.log(post, data)
    axios.post(followPath, data)
      .then(res => {
        callback(res)
      })
      .catch(err => {
        errorCallback(err)
      })
  } else {
    console.log('언팔')
    axios.delete(followPath, {params: data})
      .then(res => {
        callback(res)
      })
      .catch(err => {
        errorCallback(err)
      })
  }
}


const UserApi = {
  URL,
  ROUTES,
  requestLogin:(data, callback, errorCallback)=>requestLogin(data, callback, errorCallback),
  checkNickname:(data, callback, errorCallback)=>checkNickname(data, callback, errorCallback),
  checkEmail:(data, callback, errorCallback)=>checkEmail(data, callback, errorCallback),
  requestSignup:(data, callback, errorCallback)=>requestSignup(data, callback, errorCallback),
  requestProfileInfo:(data, params, callback, errorCallback)=>requestProfileInfo(data, params, callback, errorCallback),
  requestFollower:(data, callback, errorCallback)=>requestFollower(data, callback, errorCallback),
  requestFollowing:(data, callback, errorCallback)=>requestFollowing(data, callback, errorCallback),
  requestFollow:(post, data, callback, errorCallback)=>requestFollow(post, data, callback, errorCallback),
  requestMyPage:(id, callback, errorCallback)=>requestMyPage(id, callback, errorCallback),
  updateProfile:(id, data, callback, errorCallback)=>updateProfile(id, data, callback, errorCallback),
  updatePassword:(data, callback, errorCallback)=>updatePassword(data, callback, errorCallback),
}

export default UserApi