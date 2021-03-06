import  {ref}  from '../../composition/index.ts';
import firebase from 'firebase/app';
import 'firebase/messaging';
import Config from '../../../firebase';
import store from "../../store";
import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";

// import '@firebase/messaging';
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
// const Config = {
//   apiKey: "AIzaSyAlkz45CmAwsU4sJhmjJjAoqHUWPMka8oE",
//   authDomain: "donas-d47d8.firebaseapp.com",
//   databaseURL: "https://donas-d47d8-default-rtdb.asia-southeast1.firebasedatabase.app",
//   projectId: "donas-d47d8",
//   storageBucket: "donas-d47d8.appspot.com",
//   messagingSenderId: "635702943991",
//   appId: "1:635702943991:web:ea99975d129734b24ef3f2",
//   measurementId: "G-VJ7XLPCD90"
// };

firebase.initializeApp(Config);

const token = ref("AAAAlALZIPc:APA91bFPmuJhhTAeltNqFrm5OpS0Y7FVJHTQrr685xhCdqZmM5QUVU3u1NAXp6NtgeVg0M6w88tDeGvL30aHOUIdL8o7nDQlT_Nr90CgVPjSpj6lGORaXmpaVBx6k4VQla_rsuP5ZVxL");
const messaging = firebase.messaging();
messaging.usePublicVapidKey("BNtJjn9ch7RFp8N9V3C6SDBE02Zr-_W_OIoZSZcP6uT31Nlzsw22xpRbZ_EcX_04U0zjSePNFaF71aNG7tDZkMY");


messaging.getToken().then((currentToken) => {
    if (currentToken) {
        // console.log(currentToken);
        token.value = currentToken;
    } else {
        // Show permission request.
        console.log('No Instance ID token available. Request permission to generate one.');
    }
});
messaging.onMessage((payload) => {
    // console.log('foreground',payload);
    store.dispatch('setPayload', payload)
    // 울리면 바로 가지고 오게 하는 알람
    store.dispatch('fetchCommonAlarms')
    store.dispatch('updateQuestAlarms')
    store.dispatch('setMessageAll')
    // 메세지 불러오는 함수를 여기에 붙여넣으면 된다.
    // 채팅방을 보고 있을 때는 dispatch 보내서 -> 채팅방 불러오는 애
    // 아니면 목록 불러오는 애를 실행해야 함.
    //
    const title = '도나쓰';
    const options = {
        body: payload.data.message,
        icon: '/firebase-logo.png',
    };
    const notification = new Notification(title, options);
    return notification;
});

export {
    token,
    messaging,
};