import { createRouter, createWebHistory } from 'vue-router'
import CommunityVue from '../views/Community.vue'
import LoginVue from '../views/Login.vue'
import Main from '../views/Main.vue'
import MovieVue from '../views/Movie.vue'
import SignUpVue from '../views/SignUp.vue'
import MovieDetailVue from '../views/MovieDetail.vue'
import IconShopVue from '../views/IconShop.vue'
import MovieSearchVue from '../views/MovieSearch.vue'
import CommunityCreateVue from '../views/CommunityCreate.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: Main,
    meta: { requiredAuth: false }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignUpVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/movie',
    name: 'movie',
    component: MovieVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/moviesearch',
    name: 'moviesearch',
    component: MovieSearchVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/moviedetail/:id',
    name: 'moviedetail',
    component: MovieDetailVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/iconshop',
    name: 'iconshop',
    component: IconShopVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/communitycreate',
    name: 'communitycreate',
    component: CommunityCreateVue,
    meta: { requiredAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem('user')

  if (to.meta.requiredAuth) {
    if (!loggedIn) {
      alert('권한이 필요합니다. 로그인 해주세요')
      return next({ path: '/login' })
    } else {
      next()
    }
  }

  return next()
})

export default router
