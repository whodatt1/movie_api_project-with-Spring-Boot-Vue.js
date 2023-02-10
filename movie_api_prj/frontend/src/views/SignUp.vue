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
            <label for="id" class="form-label">아이디</label>
            <input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요." v-model="state.signUp.id">
            <div v-if="state.errorMsgBag.id">{{ state.errorMsgBag.id }}</div>
          </div>
          <div class="col-12">
            <label for="nickName" class="form-label">닉네임</label>
            <input type="text" class="form-control" id="nickName" placeholder="비밀번호를 입력하세요."
              v-model="state.signUp.nickName">
            <div v-if="state.errorMsgBag.nickName">{{ state.errorMsgBag.nickName }}</div>
          </div>
          <div class="col-12">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="password" placeholder="비밀번호를 한번 더 입력하세요."
              v-model="state.signUp.password">
            <div v-if="state.errorMsgBag.password">{{ state.errorMsgBag.password }}</div>
          </div>
          <div class="col-12">
            <label for="passwordChk" class="form-label">비밀번호 확인</label>
            <input type="password" class="form-control" id="passwordChk" placeholder="비밀번호를 입력하세요."
              v-moel="state.signUp.passwordChk">
            <div v-if="state.errorMsgBag.passwordChk">{{ state.errorMsgBag.passwordChk }}</div>
          </div>

          <div class="col-12">
            <label for="email" class="form-label">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요." required=""
              v-model="state.signUp.email">
            <div v-if="state.errorMsgBag.passwordChk">{{ state.errorMsgBag.passwordChk }}</div>
          </div>
          <div class="col-12">
            <input type="checkbox" class="form-check-input" id="isAdult" v-model="state.signUp.isAdult">
            <label class="form-check-label" for="isAdult">성인 여부를 체크해주세요.</label>
          </div>
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-primary btn-lg mb-5" @click="submit">회원가입</button>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from '@vue/reactivity'
import app from '../js/app'
// import axios from 'axios'
// import router from '@/router'

export default {
  components: {},
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {
    const state = reactive({
      signUp: {
        id: '',
        nickName: '',
        password: '',
        passwordChk: '',
        email: '',
        isAdult: ''
      },
      errorMsgBag: {}
    })

    const submit = () => {
      validationChk()
    }

    const validationChk = () => {
      if (!state.signUp.id) {
        state.errorMsgBag.id = '아이디는 필수 입력 사항입니다.'
      } else {
        state.errorMsgBag.id = ''
      }

      if (!state.signUp.nickName) {
        state.errorMsgBag.nickName = '닉네임은 필수 입력 사항입니다.'
      }

      if (!state.signUp.password) {
        state.errorMsgBag.password = '비밀번호는 필수 입력 사항입니다.'
      }

      if (!state.signUp.passwordChk) {
        state.errorMsgBag.passwordChk = '비밀번호 확인은 필수 입력 사항입니다.'
      }

      if (!state.signUp.email) {
        state.errorMsgBag.email = '이메일은 필수 입력 사항입니다.'
      } else if (!app.validEmail(state.signUp.email)) {
        state.errorMsgBag.emailVali = '이메일 형식을 확인해주세요.'
      }

      if (!app.isEmptyObj(state.errorMsgBag)) {
        // realRegistAccount()
      }
    }

    // const realRegistAccount = () => {
    //   axios.post('/auth/signup', state.signUp).then((res) => {
    //     router.push({ path: '/' })
    //     alert('회원가입이 완료 되었습니다. 가입한 계정으로 로그인 하세요.')
    //   }).catch(() => {
    //     alert('회원가입에 실패했습니다. 관리자에게 문의해주시기 바랍니다.')
    //   })
    // }

    return { state, submit }
  },
  created() { },
  mounted() { },
  unmounted() { },
  methods: {}
}
</script>
