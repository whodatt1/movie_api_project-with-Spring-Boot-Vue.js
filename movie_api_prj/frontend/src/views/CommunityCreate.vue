<template>
  <div class="container" style="margin-top: 3rem;">
    <h3 class="mb-3">글 작성</h3>
    <div class="form-group mb-3">
      <select class="form-select-custom">
        <option value="">공지</option>
        <option value="">일반</option>
      </select>
      <input type="email" class="form-control-custom" placeholder="제목을 입력하세요">
    </div>
    <div style="position: relative; display: block;" class="mb-3">
      <input type="file" class="my-form" name="files" id="files" style="display: none;" multiple required>
      <button type="button" id="trigger" class="my-btn d-block mb-3">파일 첨부</button>
    </div>
    <div class="mb-3">
      <textarea class="form-control" id="exampleFormControlTextarea1" rows="20"></textarea>
    </div>
    <div class="text-end mb-3">
      <button class="btn btn-primary btn-custom">작성완료</button>
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
  width: 80%;
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
