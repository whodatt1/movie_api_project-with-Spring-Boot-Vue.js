<template>
  <div class="container mt-3" style="margin-top: 3rem;">
    <h4>관리자 페이지</h4>
    <table class="table table-hover mb-3" style="table-layout: fixed">
      <thead>
        <tr>
          <th scope="col" style="width: 10%; text-align: center;">아이디</th>
          <th scope="col" style="width: 10%; text-align: center;">닉네임</th>
          <th scope="col" style="width: 8%; text-align: center;">성인 여부</th>
          <th scope="col" style="width: 18%; text-align: center;">생성 일자</th>
          <th scope="col" style="width: 18%; text-align: center;">수정 일자</th>
          <th scope="col" style="width: 18%; text-align: center;">최종 접속 시간</th>
          <th scope="col" style="width: 8%; text-align: center;">탈퇴 처리</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, idx) in userList" :key="idx">
          <th scope="row" style="width: 10%; text-align: center;">{{ item.userId }}</th>
          <th scope="row" style="width: 10%; text-align: center;">{{ item.userNickNm }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ item.adult ? 'O' : 'X' }}</th>
          <th scope="row" style="width: 18%; text-align: center;">{{ item.regDt }}</th>
          <th scope="row" style="width: 18%; text-align: center;">{{ item.modDt }}</th>
          <th scope="row" style="width: 18%; text-align: center;">{{ item.lastLogin }}</th>
          <th scope="row" style="width: 8%; text-align: center;"><button class="btn btn-danger btn-sm">회원 탈퇴</button></th>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import AuthService from '@/services/auth.service'
export default {
  components: {},
  data() {
    return {
      userList: []
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getUserListForAdmin()
  },
  unmounted() {},
  methods: {
    getUserListForAdmin() {
      AuthService.getUserList()
        .then((res) => {
          this.userList = res.data
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
