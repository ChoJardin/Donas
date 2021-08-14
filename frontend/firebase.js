import firebase from 'firebase/app'
import 'firebase/firebase-messaging'

var firebaseConfig = {
    apiKey: "AIzaSyAlkz45CmAwsU4sJhmjJjAoqHUWPMka8oE",
    authDomain: "donas-d47d8.firebaseapp.com",
    databaseURL: "https://donas-d47d8-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "donas-d47d8",
    storageBucket: "donas-d47d8.appspot.com",
    messagingSenderId: "635702943991",
    appId: "1:635702943991:web:ea99975d129734b24ef3f2",
    measurementId: "G-VJ7XLPCD90"
}

firebase.initializeApp(firebaseConfig)

export default firebase.messaging()