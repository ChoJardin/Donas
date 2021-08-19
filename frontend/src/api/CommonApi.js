import axios from "axios";

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  main: '/main',
  // 메세지
  // 보내기
  sendMessage: '/message',
  // 목록
  messageList: '/message/list',
  // 내용
  message: '/message/content',


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

// 메세지 보내기
const sendMessage = (data, callback, errorCallback) => {
  const sendMessagePath = URL + ROUTES.sendMessage
  axios.post(sendMessagePath, data)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 메세지 목록 요청
const requestMessageList = (data, callback, errorCallback) => {
  const messageListPath = URL + ROUTES.messageList
  axios.get(messageListPath, {params: data})
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

// 단일 채팅방 불러오기
const requestEachRoom = (data, callback, errorCallback) => {
  const requestEachRoomPath = URL + ROUTES.message
  axios.get(requestEachRoomPath, {params: data})
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const CommonApi = {
  URL,
  ROUTES,
  requestMainInfo:(data, callback, errorCallback)=>requestMainInfo(data, callback, errorCallback),
  sendMessage:(data, callback, errorCallback)=>sendMessage(data, callback, errorCallback),
  requestMessageList:(data, callback, errorCallback)=>requestMessageList(data, callback, errorCallback),
  requestEachRoom:(data, callback, errorCallback)=>requestEachRoom(data, callback, errorCallback),
}

export default CommonApi