import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from "@/views/Main";

// 퀘스트
import Quest from "@/views/quests/Quest";
import CreateQuest from "@/views/quests/CreateQuest";
import SoloQuest from "@/components/quests/SoloQuest"
import RelayQuest from "@/components/quests/RelayQuest"
import GroupQuest from "@/components/quests/GroupQuest"
import AllQuest from "@/components/quests/AllQuest";
import QuestDetail from "@/views/quests/QuestDetail";

// 피드
import Feed from "@/views/articles/Feed";
import VerticalFeed from "@/views/articles/VerticalFeed";

// 마일리지
import Mileage from "@/views/mileages/Mileage";
import Donation from "@/components/mileages/Donation";
import CashOut from "@/components/mileages/CashOut";

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
import AlertAll from "@/components/user/AlertAll";
import MessageAll from "@/components/user/MessageAll";

// 검색
import Search from "@/views/common/Search";

// 오류
import Error from "@/views/common/Error";
import PageNotFound from "@/views/common/PageNotFound";


Vue.use(VueRouter)

const routes = [
  // 메인
  {path: '/', name: 'Main', component: Main},

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
  {path: '/quests/create', name: 'CreateQuest', component: CreateQuest},
  //퀘스트 상세
  {path: '/quests/:questId', name: 'QuestDetail', component: QuestDetail},

  // 피드
  {path: '/feed', name: 'Feed', component: Feed},
  {path: '/feed/v', name: 'VerticalFeed', component: VerticalFeed},

  // 마일리지
  {
    path: '/user/mileage', component: Mileage,
    children: [
      {path: '', name: 'Donation', component: Donation},
      {path: 'cashout', name: 'CashOut', component: CashOut}
    ]
  },

  // 프로필
  {path: '/user/profile/undefined', redirect: '/login'},
  {path: '/user/profile/:nickname', name: 'Profile', component: Profile},
  {path: '/user/profile/:nickname/edit', name: 'ProfileEdit', component: ProfileEdit},

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
      {path: '', name: 'Alert', component: AlertAll},
      {path: 'messages', name: 'Messages', component: MessageAll}
    ]
  },

  //검색
  {path: '/search', name: 'Search', component: Search},

  // 에러
  {path: '/error', name: 'Error', component: Error},
  // 404
  {path: '*', redirect: '/404'},
  {path: '/404', name: 'PageNotFound', component: PageNotFound}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  // 새 페이지 로딩할 때 항상 스크롤 맨 위로
  // scrollBehavior(to, from) {
  //   return {x: 0, y:0}
  // }
})

export default router
