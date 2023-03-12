<template>
  <div class="container px-4 px-lg-5">
    <div class="py-5 text-center">
      <h2>회원 탈퇴</h2>
      <p class="lead">비밀번호가 일치할 시 회원 탈퇴가 완료됩니다.</p>
    </div>

    <div class="row g-5">
      <div class="col-12">
        <div class="row g-3">
          <div class="col-12">
            <label for="userPw" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="userPw" placeholder="비밀번호를 입력하세요."
              v-model="user.userPw">
            <div class="validation-div" v-if="errorMsgBag.userPw">{{ errorMsgBag.userPw }}</div>
          </div>
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-danger btn-lg mb-5" @click="deleteUser">회원 탈퇴</button>
      </div>
    </div>
  </div>
</template>
<script>
import AuthService from '@/services/auth.service'
import store from '@/store'
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
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.user.userId = this.auth.user.userId
  },
  unmounted() {},
  methods: {
    deleteUser() {
      AuthService.delete(this.user)
        .then((res) => {
          store.dispatch('auth/logout').then(() => {
            alert('회원정보가 삭제되었습니다. 감사합니다.')
            this.$router.push({ path: '/' })
          },
          (error) => {
            console.log(error)
          })
        }).catch((err) => {
          if (err.response.status === 401) {
            alert('비밀번호가 일치하지 않습니다.')
          }
          this.errorMsgBag = err.response.data
        })
    }
  }
}
</script>
