<template>
  <div class="container px-4 px-lg-5">
    <div class="py-5 text-center">
      <h2>회원가입</h2>
      <p class="lead">회원가입에 필요한 정보를 입력하세요.</p>
    </div>

    <div class="row g-5">
      <div class="col-12">
        <div class="row g-3">
          <div class="col-12">
            <label for="userId" class="form-label">아이디</label>
            <input type="text" class="form-control" id="userId" placeholder="아이디를 입력하세요." v-model="user.userId">
            <div class="validation-div" v-if="errorMsgBag.userId">{{ errorMsgBag.userId }}</div>
            <div class="validation-div" v-if="errorMsgBag.userIdDuple">{{ errorMsgBag.userIdDuple }}</div>
          </div>
          <div class="col-12">
            <label for="userNickNm" class="form-label">닉네임</label>
            <input type="text" class="form-control" id="userNickNm" placeholder="닉네임을 입력하세요."
              v-model="user.userNickNm">
            <div class="validation-div" v-if="errorMsgBag.userNickNm">{{ errorMsgBag.userNickNm }}</div>
          </div>
          <div class="col-12">
            <label for="userPw" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="userPw" placeholder="비밀번호를 입력하세요."
              v-model="user.userPw">
            <div class="validation-div" v-if="errorMsgBag.userPw">{{ errorMsgBag.userPw }}</div>
          </div>
          <div class="col-12">
            <label for="email" class="form-label">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요." required=""
              v-model="user.userEmail">
            <div class="validation-div" v-if="errorMsgBag.userEmail">{{ errorMsgBag.userEmail }}</div>
          </div>
          <div class="col-12">
            <input type="checkbox" class="form-check-input" id="isAdult" v-model="user.isAdult">
            <label class="form-check-label" for="isAdult">성인 여부를 체크해주세요.</label>
          </div>
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-primary btn-lg mb-5" @click="signUp">회원가입</button>
      </div>
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
        userNickNm: '',
        userPw: '',
        userEmail: '',
        adult: ''
      },
      errorMsgBag: {}
    }
  },
  setup() { },
  created() { },
  mounted() { },
  unmounted() { },
  methods: {
    signUp() {
      this.$store.dispatch('auth/signup', this.user).then(() => {
        this.$router.push({ path: '/' })
        alert('가입이 완료되었습니다. 회원전용 서비스를 이용하고 싶다면 로그인 하세요.')
      },
      (error) => {
        this.errorMsgBag = error.response.data
      })
    }
  }
}
</script>
