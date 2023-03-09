<template>
  <div class="container" style="margin-top: 3rem;">
    <h3 class="mb-3">글 작성</h3>
    <div class="form-group mb-3">
      <select class="form-select-custom" v-model="formData.category">
        <option value="normal">일반</option>
        <option value="notice" v-if="auth.user.roles.includes('ROLE_ADMIN')">공지</option>
      </select>
      <input type="text" class="form-control-custom" placeholder="제목을 입력하세요" v-model="formData.title">
      <div class="validation-div" v-if="errorMsgBag.category">{{ errorMsgBag.category }}</div>
      <div class="validation-div" v-if="errorMsgBag.title">{{ errorMsgBag.title }}</div>
    </div>
    <div style="position: relative; display: block;" class="mb-3">
      <input type="file" ref="fileObjects" name="files" style="display: none;" multiple required @change="fileChange">
      <button class="btn btn-success btn-custom" @click="fileUpload">파일 첨부</button>
    </div>
    <div>
      <ul class="list-group list-group-flush">
        <li v-for="(item, idx) in fileObjects" :key="idx" class="list-group-item d-flex justify-content-between align-items-start mb-3" style="border-radius: 4px;">
          <div class="ms-2 me-auto">
            <div class="fw-bold text-success" v-if="item.validResult === 1">업로드 가능</div>
            <div class="fw-bold text-danger" v-else>업로드 불가</div>
            {{ item.name }}
            <span class="" style="border-radius: 4px;"> | {{ item.size }} Bytes</span>
          </div>
        </li>
      </ul>
    </div>
    <div class="mb-3">
      <textarea class="form-control" id="exampleFormControlTextarea1" rows="20" v-model="formData.content"></textarea>
      <div class="validation-div" v-if="errorMsgBag.content">{{ errorMsgBag.content }}</div>
    </div>
    <div class="text-end mb-3">
      <button class="btn btn-primary btn-custom" :disabled="submitDis" @click="submit">작성완료</button>
    </div>
  </div>
</template>
<script>
import app from '@/js/app'
import CommunityService from '@/services/community.service'

export default {
  components: {},
  data() {
    return {
      formData: {
        category: 'normal',
        title: '',
        writerId: '',
        content: '',
        file: ''
      },
      fileObjects: [],
      submitDis: false,
      errorMsgBag: {}
    }
  },
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    fileUpload() {
      this.$refs.fileObjects.click()
    },
    fileChange() {
      this.fileObjects = []

      let isOk = 1
      const files = this.$refs.fileObjects.files

      for (const file of files) {
        const validResult = app.fileSizeValidation(file.name, file.size)
        isOk *= validResult
        file.validResult = validResult
        this.fileObjects.push(file)
      }

      if (isOk === 0) {
        this.submitDis = true
      } else {
        this.submitDis = false
      }
    },
    submit() {
      const formData = new FormData()

      if (this.auth.user) {
        this.formData.writerId = this.auth.user.userId
      }
      if (this.fileObjects.length === 0) {
        this.formData.file = false
      } else {
        this.formData.file = true
      }

      formData.append('commRequest', new Blob([JSON.stringify(this.formData)], { type: 'application/json' }))

      for (let i = 0; i < this.fileObjects.length; i++) {
        const file = this.fileObjects[i]
        formData.append('files', file)
      }

      CommunityService.createCommunity(formData)
        .then((res) => {
          console.log(res)
          alert('글 작성이 완료되었습니다.')
          this.$router.push({ name: 'community', query: { pageNo: 1 } })
        }).catch((err) => {
          this.errorMsgBag = err.response.data
        })
    }
  }
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
