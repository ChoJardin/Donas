import  {ref}  from '../../composition/index.ts';
import firebase from 'firebase/app';
import 'firebase/messaging';
import Config from '../../../firebase';

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

// firebase.initializeApp(Config);

const token = ref("");
const messaging = Config;
messaging.usePublicVapidKey("BNtJjn9ch7RFp8N9V3C6SDBE02Zr-_W_OIoZSZcP6uT31Nlzsw22xpRbZ_EcX_04U0zjSePNFaF71aNG7tDZkMY");


messaging.getToken().then((currentToken) => {
    if (currentToken) {
        console.log(currentToken);
        token.value = currentToken;
    } else {
        // Show permission request.
        console.log('No Instance ID token available. Request permission to generate one.');
    }
});
messaging.onMessage((payload) => {
    console.log('foreground',payload);
    const title = 'Title';
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