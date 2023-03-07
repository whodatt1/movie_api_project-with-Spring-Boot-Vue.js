<template>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
      <a class="navbar-brand" href="/">MPI FARM</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ms-auto py-4 py-lg-0">
          <li class="nav-item"><router-link to="/movie" class="nav-link px-lg-3 py-3 py-lg-4">영화</router-link></li>
          <li class="nav-item"><router-link to="/moviesearch" class="nav-link px-lg-3 py-3 py-lg-4">영화 검색</router-link></li>
          <li class="nav-item"><router-link :to="`/community?pageNo=1`" class="nav-link px-lg-3 py-3 py-lg-4">커뮤니티</router-link></li>
          <li class="nav-item"><router-link to="/movie" class="nav-link px-lg-3 py-3 py-lg-4" v-if="auth.status.loggedIn">북마크</router-link></li>
          <li class="nav-item">
            <router-link to="/login" v-if="!auth.status.loggedIn"><button class="btn btn-primary px-3 py-2 mt-2">로그인</button></router-link>
            <button class="btn btn-primary px-3 py-2 mt-2" v-else @click="logout">로그아웃</button>
          </li>
          <li class="nav-item"><router-link to="/"><button class="btn btn-success px-3 py-2 mt-2">내정보</button></router-link></li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Page Header-->
  <header class="masthead">
    <div class="container position-relative px-4 px-lg-5">
      <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
          <div class="site-heading">
            <h1>Welcome!</h1>
            <span class="subheading">영화 및 TV 프로그램 정보를 조회하세요.</span>
          </div>
        </div>
      </div>
    </div>
  </header>
  <router-view :key="$route.fullPath" />
  <Footer/>
</template>

<script>
import '../src/js/scripts.js'
import Footer from './components/Footer.vue'

export default {
  components: { Footer },
  data() {
    return {
      sampleData: ''
    }
  },
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  setup() { },
  methods: {
    logout() {
      this.$store.dispatch('auth/logout').then(() => {
        alert('로그아웃에 성공하였습니다.')
        this.$router.push('/')
      }).catch((err) => {
        console.log(err)
      })
    }
  }
}
</script>

<style>
@import '../src/css/styles.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>
