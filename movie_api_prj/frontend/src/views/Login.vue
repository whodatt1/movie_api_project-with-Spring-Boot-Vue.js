<template>
  <div class="container px-4 px-lg-5">
    <div class="py-5 text-center">
      <h2>로그인</h2>
      <p class="lead">로그인 하여 서비스를 이용할 수 있습니다.</p>
    </div>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" v-model="user.userId">
      <label for="floatingInput">아이디</label>
      <div class="validation-div" v-if="errorMsgBag.userId">{{ errorMsgBag.userId }}</div>
    </div>
    <div class="form-floating mb-5">
      <input type="password" class="form-control" id="floatingPassword" v-model="user
      .userPw">
      <label for="floatingPassword">비밀번호</label>
      <div class="validation-div" v-if="errorMsgBag.userPw">{{ errorMsgBag.userPw }}</div>
    </div>

    <div class="py-5">
      <button class="w-100 btn btn-lg btn-primary mb-3" @click="login">로그인</button>
      <router-link to="/signup"><button class="w-100 btn btn-lg btn-success">회원가입</button></router-link>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      user: {
        userId: '',
        userPw: ''
      },
      errorMsgBag: {}
    }
  },
  setup() { },
  created() { },
  mounted() { },
  unmounted() { },
  methods: {
    login() {
      this.$store.dispatch('auth/login', this.user).then(() => {
        this.$router.push({ path: '/' })
        alert('로그인에 성공하였습니다.')
      },
      (error) => {
        if (error.response.status === 401) {
          alert('회원정보가 일치하지 않습니다 관리자에게 문의해주세요.')
        }
        this.errorMsgBag = error.response.data
      })
    }

  }
}
</script>
