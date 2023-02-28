<template>
  <div class="container">
    <div class="input-group mb-5">
        <input type="text" class="form-control search-input" placeholder="영화를 검색하세요">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">Search</button>
        </span>
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
.search-input {
  height: 50px;
  font-size: 15px;
  border: 0;
  border-radius: 30px;
  outline: none;
  background-color: rgb(233, 233, 233);
}
</style>
