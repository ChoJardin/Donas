import { register } from 'register-service-worker'

if (process.env.NODE_ENV === 'production') {
  register(`${process.env.BASE_URL}service-worker.js`, {
    ready () {
      console.log(
        'App is being served from cache by a service worker.\n' +
        'For more details, visit https://goo.gl/AFskqB'
      )
    },

    registered () {
      console.log('Service worker has been registered.')
    },
    cached () {
      console.log('Content has been cached for offline use.')
    },
    updatefound () {
      console.log('New content is downloading.')
    },
    updated () {
      console.log('New content is available; please refresh.')
    },
    offline () {
      console.log('No internet connection found. App is running in offline mode.')
    },
    error (error) {
      console.error('Error during service worker registration:', error)
    }
  })
}
// if ('serviceWorker' in navigator) {
//   navigator.serviceWorker.register('firebase-messaging-sw.js')
//     .then(reg => {
//       console.log(`Service Worker Registration (Scope: ${reg.scope})`);
//     })
//     .catch(error => {
//       const msg = `Service Worker Error (${error})`;
//       console.error(msg);
//     });
// } else {
//   // happens when the app isn't served over HTTPS or if the browser doesn't support service workers
//   console.warn('Service Worker not available');
// }