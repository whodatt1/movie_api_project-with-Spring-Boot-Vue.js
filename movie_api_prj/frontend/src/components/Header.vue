<template>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
      <a class="navbar-brand" href="index.html">MPI FARM</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ms-auto py-4 py-lg-0">
          <li class="nav-item"><router-link to="/" class="nav-link px-lg-3 py-3 py-lg-4">영화</router-link></li>
          <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="about.html">TV 프로그램</a></li>
          <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="post.html">북마크</a></li>
          <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="contact.html">커뮤니티</a></li>
          <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="contact.html">FAQ</a></li>
          <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="contact.html">아이콘샵</a></li>
          <li class="nav-item"><router-link to="/login"><button class="btn btn-primary px-3 py-2 mt-2">로그인</button></router-link></li>
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
</template>
<script>
export default {
  components: {},
  data() {
    return {
      sampleData: ''
    }
  },
  setup() { },
  created() { },
  mounted() { },
  unmounted() { },
  methods: {}
}
</script>
