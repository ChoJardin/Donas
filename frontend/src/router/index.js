import Vue from 'vue'
import VueRouter from 'vue-router'

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


// 피드
import Feed from "@/views/articles/Feed";
import VerticalFeed from "@/views/articles/VerticalFeed";
// 게시글
import ArticleDetail from "@/components/articles/ArticleDetail";
import UpsertArticle from "@/views/articles/UpsertArticle";

// 마일리지
import Mileage from "@/views/mileages/Mileage";
import Donation from "@/components/mileages/Donation";
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


Vue.use(VueRouter)

const routes = [
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
  {path: '/quests/:questId', name: 'QuestDetail', component: QuestDetail},

  // 피드
  {path: '/feed', name: 'Feed', component: Feed},
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
  {path: '/article/create', component: UpsertArticle},

  // 마일리지
  {
    path: '/user/mileage', component: Mileage,
    children: [
      {path: '', name: 'Donation', component: Donation},
      {path: 'cashout', name: 'CashOut', component: CashOut},
      {path: 'success', name: 'CashOutResult',component: CashOutResult, meta: { transitionName: 'slide' }}
    ]
  },

  // 프로필
  // {path: '/user/profile/', redirect: '/login'},
  {path: '/user/profile/undefined', redirect: '/login'},
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

  // 로그인
  {path: '/login', name: 'Login', component: Login},
  // 회원가입
  {path: '/signup', name: 'Signup', component: Signup},
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
  // 404
  // {path: '/*', redirect: '/404'},
  // {path: '/404', name: 'PageNotFound', component: PageNotFound}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
