<template>
  <div class="container px-4 px-lg-5">
    <div class="py-5 text-center">
      <h2>회원정보 수정</h2>
      <p class="lead">수정할 정보를 입력하세요.</p>
    </div>

    <div class="row g-5">
      <div class="col-12">
        <div class="row g-3">
          <div class="col-12">
            <label for="userNickNm" class="form-label">닉네임</label>
            <input type="text" class="form-control" id="userNickNm" placeholder="닉네임을 입력하세요."
              v-model="user.userNickNm">
            <div class="validation-div" v-if="errorMsgBag.userNickNm">{{ errorMsgBag.userNickNm }}</div>
          </div>
          <div class="col-12">
            <label for="userPw" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="userPw" placeholder="새로운 비밀번호를 입력하세요."
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
            <input type="checkbox" class="form-check-input" id="isAdult" v-model="user.adult">
            <label class="form-check-label" for="isAdult">성인 여부를 체크해주세요.</label>
          </div>
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-primary btn-lg mb-5" @click="update">회원정보 수정</button>
      </div>
    </div>
  </div>
</template>
<script>
import AuthService from '@/services/auth.service'
import store from '@/store'
import router from '@/router'

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
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  created() { },
  mounted() {
    this.user.userId = this.auth.user.userId
    this.user.userNickNm = this.auth.user.userNickNm
    this.user.userEmail = this.auth.user.userEmail
    this.user.adult = this.auth.user.adult
  },
  unmounted() { },
  methods: {
    update() {
      AuthService.update(this.user)
        .then((res) => {
          store.dispatch('auth/logout').then(() => {
            alert('수정이 완료되었습니다. 다시 로그인 해주세요.')
            router.push({ path: '/login' })
          },
          (error) => {
            console.log(error)
          })
        }).catch((err) => {
          this.errorMsgBag = err.response.data
        })
    }
  }
}
</script>
