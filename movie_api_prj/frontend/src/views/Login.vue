<template>
  <div class="container px-4 px-lg-5">
    <div class="py-5 text-center">
      <h2>로그인</h2>
      <p class="lead">로그인 하여 서비스를 이용할 수 있습니다.</p>
    </div>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" v-model="state.user.userId">
      <label for="floatingInput">아이디</label>
      <div class="validation-div" v-if="state.errorMsgBag.userId">{{ state.errorMsgBag.userId }}</div>
    </div>
    <div class="form-floating mb-5">
      <input type="password" class="form-control" id="floatingPassword" v-model="state.user
      .userPw">
      <label for="floatingPassword">비밀번호</label>
      <div class="validation-div" v-if="state.errorMsgBag.userPw">{{ state.errorMsgBag.userPw }}</div>
    </div>

    <div class="py-5">
      <button class="w-100 btn btn-lg btn-primary mb-3" @click="submit">로그인</button>
      <router-link to="/signup"><button class="w-100 btn btn-lg btn-success">회원가입</button></router-link>
    </div>
  </div>
</template>
<script>
import { reactive } from '@vue/reactivity'
import { store } from 'vuex'
import router from '@/router'

export default {
  components: {},
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {
    const state = reactive({
      user: {
        userId: '',
        userPw: ''
      },
      errorMsgBag: {}
    })

    const submit = () => {
      store.dispatch('auth/login', state.user).then(() => {
        router.push({ path: '/' })
        alert('로그인에 성공하였습니다.')
      },
      (error) => {
        state.errorMsgBag = error.response.data
      })
    }

    return { state, submit }
  },
  created() { },
  mounted() { },
  unmounted() { },
  methods: {}
}
</script>
