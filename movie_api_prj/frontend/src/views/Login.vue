<template>
  <div class="container px-4 px-lg-5">
    <div class="py-5 text-center">
      <h2>로그인</h2>
      <p class="lead">로그인 하여 서비스를 이용할 수 있습니다.</p>
    </div>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput">
      <label for="floatingInput">아이디</label>
    </div>
    <div class="form-floating mb-5">
      <input type="password" class="form-control" id="floatingPassword">
      <label for="floatingPassword">비밀번호</label>
    </div>

    <div class="py-5">
      <button class="w-100 btn btn-lg btn-primary mb-3" type="submit">로그인</button>
      <router-link to="/signup"><button class="w-100 btn btn-lg btn-success" type="submit">회원가입</button></router-link>
    </div>
  </div>
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
