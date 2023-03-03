<template>
  <div class="container mt-3" style="margin-top: 3rem;">
    <h4>커뮤니티</h4>
    <table class="table table-hover mb-3" style="table-layout: fixed">
      <thead>
        <tr>
          <th scope="col" style="width: 5%; text-align: center;">번호</th>
          <th scope="col" style="width: 5%; text-align: center;">종류</th>
          <th scope="col" style="width: 55%; text-align: center;">제목</th>
          <th scope="col" style="width: 8%; text-align: center;">글쓴이</th>
          <th scope="col" style="width: 8%; text-align: center;">등록일</th>
          <th scope="col" style="width: 8%; text-align: center;">조회</th>
          <th scope="col" style="width: 8%; text-align: center;">추천</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row" style="width: 5%; text-align: center;">1</th>
          <th scope="row" style="width: 5%; text-align: center;">공지</th>
          <th scope="row" style="width: 60%;">
            <span class="comu-title">지건 오지게마마마마마마마마마마마마마마마마마마마aasssssssssssssssssssssssssssssssssssssss</span>
            <span>[1]</span>
          </th>
          <th scope="row" style="width: 8%; text-align: center;">1</th>
          <th scope="row" style="width: 8%; text-align: center;">1</th>
          <th scope="row" style="width: 8%; text-align: center;">1</th>
          <th scope="row" style="width: 8%; text-align: center;">1</th>
        </tr>
      </tbody>
    </table>
    <div class="text-end mb-3">
      <router-link to="/communitycreate"><button class="btn btn-primary btn-custom">글쓰기</button></router-link>
    </div>
    <div class="text-center mb-3">
      <select class="mb-3 form-select-custom">
        <option value="dd">제목</option>
        <option value="dd">내용</option>
        <option value="dd">글쓴이</option>
        <option value="dd">종류</option>
      </select>
      <input class="form-control-custom" type="text">
      <button class="btn btn-success btn-custom">검색</button>
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
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>
<style scoped>
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
span {
  display: inline-block;
}
.comu-title {
  width: 580px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
