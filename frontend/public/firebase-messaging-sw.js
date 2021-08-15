importScripts('https://www.gstatic.com/firebasejs/8.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.8.1/firebase-messaging.js');

const config = {
    apiKey: "AIzaSyAlkz45CmAwsU4sJhmjJjAoqHUWPMka8oE",
    authDomain: "donas-d47d8.firebaseapp.com",
    databaseURL: "https://donas-d47d8-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "donas-d47d8",
    storageBucket: "donas-d47d8.appspot.com",
    messagingSenderId: "635702943991",
    appId: "1:635702943991:web:ea99975d129734b24ef3f2",
    measurementId: "G-VJ7XLPCD90"
};

firebase.initializeApp(config);
const messaging = firebase.messaging();

//token값 알아내기
// messaging.requestPermission()
//   .then(function () {
//     console.log("Have permission");
//     return messaging.getToken();
//   })
//   .then(function (token) {
//     console.log(token);
//   })
//   .catch(function (arr) {
//     console.log("Error Occured");
//   });

// console.log("포그라운드에서 받은 알림");

// messaging.onMessage(function (payload) {
//   console.log('onMessage: ', payload);
//   var title = "고라니 서비스";
//   var options = {
//     body: payload.notification.body
//   };
//
//   var notification = new Notification(title, options);
// });

// 백그라운드 상태에서 받은 알림 처리
messaging.setBackgroundMessageHandler((payload) => {
  console.log('background', payload);
  // Customize notification here
  const title = 'Background Message Title'
  const options = {
    body: payload.data.message,
    icon: '/firebase-logo.png'
  }

  return self.registration.showNotification(title, options);
});