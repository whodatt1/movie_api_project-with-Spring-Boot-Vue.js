<template>
  <div class="container mt-3" style="margin-top: 3rem;">
    <h4>커뮤니티</h4>
    <table class="table table-hover mb-3" style="table-layout: fixed">
      <thead>
        <tr>
          <th scope="col" style="width: 8%; text-align: center;">번호</th>
          <th scope="col" style="width: 5%; text-align: center;">종류</th>
          <th scope="col" style="width: 55%; text-align: center;">제목</th>
          <th scope="col" style="width: 8%; text-align: center;">글쓴이</th>
          <th scope="col" style="width: 8%; text-align: center;">등록일</th>
          <th scope="col" style="width: 8%; text-align: center;">조회</th>
          <th scope="col" style="width: 8%; text-align: center;">추천</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, idx) in communityNoticeList" :key="idx">
          <th scope="row" style="width: 8%; text-align: center;">{{ item.id }}</th>
          <th scope="row" style="width: 5%; text-align: center;">{{ item.category === 'normal' ? '일반' : '공지' }}</th>
          <th scope="row" style="width: 55%;">
            <div class="div-wrap">
              <div class="title-div">
                <router-link :to="`/communitydetail?id=${item.id}&pageNo=${params.pageNo}&type=${params.type}&keyWord=${params.keyWord}`" class="comu-title" style="color: blue; font-weight: 900;">{{ item.title }}</router-link>
              </div>
              <span v-if="item.replyCount !== 0" class="reply">[{{ item.replyCount }}]</span>
              <span v-if="item.file"><i class="fa-sharp fa-regular fa-image"></i></span>
            </div>
          </th>
          <th scope="row" style="width: 8%; text-align: center; font-weight: 900;">{{ item.writerId }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ app.getRegDtForCommunity(item.regDt) }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ item.views }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ item.vote }}</th>
        </tr>
        <tr v-for="(item, idx) in communityList" :key="idx">
          <th scope="row" style="width: 8%; text-align: center;">{{ item.id }}</th>
          <th scope="row" style="width: 5%; text-align: center;">{{ item.category === 'normal' ? '일반' : '공지' }}</th>
          <th scope="row" style="width: 55%;">
            <div class="div-wrap">
              <div class="title-div">
                <router-link :to="`/communitydetail?id=${item.id}&pageNo=${params.pageNo}&type=${params.type}&keyWord=${params.keyWord}`" class="comu-title">{{ item.title }}</router-link>
              </div>
              <span v-if="item.replyCount !== 0" class="reply">[{{ item.replyCount }}]</span>
              <span v-if="item.file"><i class="fa-sharp fa-regular fa-image"></i></span>
            </div>
          </th>
          <th scope="row" style="width: 8%; text-align: center;">{{ item.writerId }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ app.getRegDtForCommunity(item.regDt) }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ item.views }}</th>
          <th scope="row" style="width: 8%; text-align: center;">{{ item.vote }}</th>
        </tr>
      </tbody>
    </table>
    <div class="text-end mb-3">
      <router-link to="/communitycreate"><button class="btn btn-primary btn-custom">글쓰기</button></router-link>
    </div>
    <ul class="pagination justify-content-center py-5">
      <li class="page-item" v-if="prev">
        <router-link :to="`/community?pageNo=${startPage - 1}&type=${params.type}&keyWord=${params.keyWord}`" class="page-link">Prev</router-link>
      </li>
      <li :class="`page-item ${ index == params.pageNo ? 'active' : '' }`" aria-current="page" v-for="index in range(startPage, endPage)" :key="index">
        <router-link :to="`/community?pageNo=${index}&type=${params.type}&keyWord=${params.keyWord}`" class="page-link" style="cursor: pointer;">{{ index }}</router-link>
      </li>
      <li class="page-item" v-if="next">
        <router-link :to="`/community?pageNo=${endPage + 1}&type=${params.type}&keyWord=${params.keyWord}`" class="page-link">Next</router-link>
      </li>
</ul>
    <div class="text-center mb-3">
      <select class="mb-3 form-select-custom" v-model="params.type">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="writerId">글쓴이</option>
      </select>
      <input class="form-control-custom" type="text" v-model="params.keyWord">
      <button class="btn btn-success btn-custom" @click="getCommunityAll">검색</button>
    </div>
  </div>
</template>
<script>
import CommunityService from '@/services/community.service'
import app from '@/js/app'
export default {
  components: {},
  data() {
    return {
      params: {
        pageNo: '',
        type: 'title',
        keyWord: ''
      },
      communityList: [],
      communityNoticeList: [],
      startPage: '',
      endPage: '',
      next: '',
      prev: ''
    }
  },
  setup() {
    return { app }
  },
  created() {},
  mounted() {
    if (this.$route.query.type) {
      this.params.type = this.$route.query.type
    }
    if (this.$route.query.keyWord) {
      this.params.keyWord = this.$route.query.keyWord
    }
    if (this.$route.query.pageNo) {
      this.params.pageNo = this.$route.query.pageNo
    }
    this.getCommunityAll(this.params)
    this.getCommunityAllNotice()
  },
  unmounted() {},
  methods: {
    range(start, end) {
      return Array(end - start + 1).fill().map((val, i) => start + i)
    },
    getCommunityAll(params) {
      CommunityService.getCommunityAll(params)
        .then((result) => {
          this.startPage = result.data.startPage
          this.endPage = result.data.endPage
          this.next = result.data.next
          this.prev = result.data.prev
          this.communityList = result.data.communityList
        }).catch((err) => {
          console.log(err)
        })
    },
    getCommunityAllNotice() {
      CommunityService.getCommunityAllNotice()
        .then((result) => {
          this.communityNoticeList = result.data
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
<style scoped>
.reply {
  color: coral;
}
.div-wrap {
  display: flex;
  overflow: hidden;
  width: 95%;
}
.title-div {
  max-width: 100%;
  text-overflow: ellipsis;
  white-space: nowrap;
  word-wrap: normal;
  width: auto;
  overflow: hidden;
}
.btn-custom {
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
}
.form-control-custom {
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  border-radius: 0;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.form-select-custom {
  margin-right: 1.0rem;
  padding: 0.375rem 2.25rem 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  background-color: #fff;
  background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='%23343a40' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M2 5l6 6 6-6'/%3e%3c/svg%3e");
  background-repeat: no-repeat;
  background-position: right 0.75rem center;
  background-size: 16px 12px;
  border: 1px solid #ced4da;
  border-radius: 0;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
}
.comu-title {
  vertical-align: baseline;
}
</style>
