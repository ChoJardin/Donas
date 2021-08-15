import Vue from 'vue'
import VueRouter from 'vue-router'
import UserApi from "@/api/UserApi";
import store from '@/store'
import cookies from "vue-cookies";

import Main from "@/views/Main";

// 퀘스트
// 퀘스트 리스트
import Quest from "@/views/quests/Quest";
import SoloQuest from "@/components/quests/SoloQuest"
import RelayQuest from "@/components/quests/RelayQuest"
import GroupQuest from "@/components/quests/GroupQuest"
import AllQuest from "@/components/quests/AllQuest";
import QuestDetail from "@/views/quests/QuestDetail";
// 퀘스트 생성
import CreateQuest from "@/views/quests/CreateQuest";
import CreateSolo from "@/components/quests/CreateSolo";
import CreateGroup from "@/components/quests/CreateGroup";
import CreateRelay from "@/components/quests/CreateRelay";
// 퀘스트 상세 참여자
import ParticipantsList from "../components/quests/ParticipantsList";


// 피드
import Feed from "@/views/articles/Feed";
import VerticalFeed from "@/views/articles/VerticalFeed";
// 게시글
import ArticleDetail from "@/components/articles/ArticleDetail";
import UpsertArticle from "@/views/articles/UpsertArticle";

// 마일리지
import Mileage from "@/views/mileages/Mileage";
import Donation from "@/components/mileages/Donation";
import CharityDetail from "../components/mileages/CharityDetail";
import CashOut from "@/components/mileages/CashOut";
import CashOutResult from "@/components/mileages/CashOutResult"

// 프로필
import Profile from "@/views/user/Profile";
import ProfileEdit from "@/views/user/ProfileEdit";
import Login from "@/views/user/Login";
import Signup from "@/views/user/Signup";

//팔로우
import Follow from "@/views/user/Follow";
import Follower from "@/views/user/Follower";
import Following from "@/views/user/Following";

// 알람
import Alert from "@/views/user/Alert";
import CommonAlert from "@/components/user/CommonAlert";
import QuestAlert from "@/components/user/QuestAlert";
import MessageAll from "@/components/user/MessageAll";

// 검색
import Search from "@/views/common/Search";

// 오류
import Error from "@/views/common/Error";
import PageNotFound from "@/views/common/PageNotFound";

// 테스트용
import ImageInput from "@/components/common/ImageInput";
import Test from "@/views/Test";
import SignInUp from "@/views/user/SignInUp";


Vue.use(VueRouter)

const routes = [
  {path: '/index.html', component: Main, alias: '/'},
  // 메인
  {path: '', redirect: '/main'},
  {path: '/main', name: 'Main', component: Main},

  // 전체 퀘스트
  {path: '/quests', component: Quest,
    children: [
      {path: '', name: 'AllQuest', component: AllQuest},
      {path: 'solo', name: 'SoloQuest', component: SoloQuest},
      {path: 'group', name: 'GroupQuest', component: GroupQuest},
      {path: 'relay', name: 'RelayQuest', component: RelayQuest},
    ]
  },
  //퀘스트 생성
  {path: '/quests/create', name:'CreateQuest', component: CreateQuest,
      children: [
      {path: 'solo', name: 'CreateSolo', component: CreateSolo},
      {path: 'group', name: 'CreateGroup', component: CreateGroup},
      {path: 'relay', name: 'CreateRelay', component: CreateRelay},
      ]
  },
  //퀘스트 상세
  {path: '/quests/:questId', name: 'QuestDetail', component: QuestDetail,},
  {path: '/participants', name:'ParticipantsList', component: ParticipantsList},


  // 피드
  {path: '/feed', name: 'Feed', component: Feed},
  {path: '/article/create', name: 'UpsertArticle', component: UpsertArticle, alias: '/article/edit'},
  {
    path: '/article', component: VerticalFeed,
    children: [
      {
        path: ':id',
        name: 'ArticleDetail',
        component: ArticleDetail,
        props: true,
        meta: { transitionName: 'slide' }
      },
    ]
  },
  {path: '/article/:id/edit', component: UpsertArticle},

  // 마일리지
  {
    path: '/user/mileage', component: Mileage,
    children: [
      {path: '', name: 'Donation', component: Donation},
      {path: 'cashout', name: 'CashOut', component: CashOut},
      {path: 'success', name: 'CashOutResult',component: CashOutResult, meta: { transitionName: 'slide' }}
    ]
  },
  {path: '/charity/:id', name: 'CharityDetail', component: CharityDetail},

  // 프로필
  // {path: '/user/profile/', redirect: '/login'},
  // {path: '/user/profile/undefined', redirect: `/user/profile/`},
  {path: '/user/profile/edit', name: 'ProfileEdit', component: ProfileEdit},
  {path: '/user/profile/:nickname', name: 'Profile', component: Profile, meta: {transitionName: 'slide'}},

  // 팔로우
  {
    path: '/follow/:nickname', component: Follow,
    children: [
      {path: 'follower', name:'Follower', component: Follower},
      {path: 'following', name: 'Following', component: Following}
    ]
  },

  // 로그인/ 회원가입
  {
    path: '/login', component: SignInUp,
    children: [
        {path: '', name: 'Login', component: Login},
        // 회원가입
        {path: '/signup', name: 'Signup', component: Signup},
      ]
  },
  // 알림
  {
    path: '/notification/:nickname', component: Alert,
    children: [
      {path: '/', name: 'QuestAlert', component: QuestAlert},
      {path: 'common', name: 'CommonAlert', component: CommonAlert},
    ]
  },
  // 메세지
  {path: '/messages/:nickname', name: 'Messages', component: MessageAll},

  //검색
  {path: '/search', name: 'Search', component: Search},

  // 에러
  {path: '/error', name: 'Error', component: Error},

  // 개발시 테스트용.. 여기에 컴포넌트 연결해서 필요한 거 보시져..
  {path: '/dev/test', name: 'Test', component: Test},

  // 404
  // {path: '/*', redirect: '/404'},
  {path: '/404', name: 'PageNotFound', component: PageNotFound, alias: '/*'},
]

// navigation duplicated
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior (to, from, savedPosition) {
  return { x: 0, y: 0 }
}
})

router.beforeEach((to, from, next) => {
  // 로그인 되어 있는 경우, 정보를 다시 새로 받아오겠습니다.
  const user = cookies.get('auto-login')
  // const user = cookies.get('login-user')
  if (user) {
    store.dispatch('updateUserInfo', user);
    store.dispatch('updateQuestAlarms', user);
    store.dispatch('updateCommonAlarms', user);
    //     // store.dispatch('setCommonAlarms', user)
  }

  // if (store.getters.isLoggedIn) {
  //   store.dispatch('updateUserInfo')
  // }
  next()
})




export default router
