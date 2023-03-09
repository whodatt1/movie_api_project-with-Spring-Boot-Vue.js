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
    <div class="cole-12 mb-3">
      <ul class="list-group list-group-flush">
        <li class="list-group-item d-flex justify-content-between align-items-start mb-3" style="border-radius: 4px;" v-for="(item, idx) in fileList" :key="idx">
          <div v-if="item.fileType > 0">
            <img :src="require(`../assets/uploadFile/${item.saveDir.replaceAll('\\', '/')}/${item.uuid}_th_${item.fileName}`)" style="width:100px; height:80px;">
          </div>
          <div v-else>
            <i class="fas fa-file fa-3x" style="color: white;"></i>
          </div>
          <div class="ms-2 me-auto">
              <div class="fw-bold">{{ item.fileName }}</div>
              {{ item.regAt }}
              <div>{{ item.fileSize }} KB</div>
          </div>
          <div style="float:left;" @click="delImgForUpdate(item.uuid)">
            <i class="fa-regular fa-circle-xmark" style="cursor: pointer;"></i>
          </div>
        </li>
      </ul>
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
      <button class="btn btn-primary btn-custom" :disabled="submitDis" @click="submit">수정</button>
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
      params: {
        pageNo: '',
        type: '',
        keyWord: ''
      },
      formData: {
        category: 'normal',
        title: '',
        writerId: '',
        content: '',
        file: ''
      },
      id: '',
      fileList: [],
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
  mounted() {
    if (this.$route.query.id) {
      this.id = this.$route.query.id
    }
    if (this.$route.query.type) {
      this.params.type = this.$route.query.type
    }
    if (this.$route.query.keyWord) {
      this.params.keyWord = this.$route.query.keyWord
    }
    if (this.$route.query.pageNo) {
      this.params.pageNo = this.$route.query.pageNo
    }
    this.getCommunityDetail(this.id)
    this.getCommunityDetailImg(this.id)
  },
  unmounted() {},
  methods: {
    delImgForUpdate(uuid) {
      CommunityService.deleteImgForUpdate(uuid)
        .then((res) => {
          this.getCommunityDetailImg(this.id)
        }).catch((err) => {
          console.log(err)
        })
    },
    getCommunityDetailImg(commId) {
      CommunityService.getCommunityDetailImg(commId)
        .then((result) => {
          this.fileList = result.data
        }).catch((err) => {
          console.log(err)
        })
    },
    getCommunityDetail(id) {
      CommunityService.getCommunityDetailForUpdate(id)
        .then((result) => {
          this.formData.category = result.data.category
          this.formData.title = result.data.title
          this.formData.writerId = result.data.writerId
          this.formData.content = result.data.content
        }).catch((err) => {
          console.log(err)
        })
    },
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
      if (this.id) {
        this.formData.id = this.id
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

      CommunityService.updateCommunity(formData)
        .then((res) => {
          alert('수정이 완료되었습니다.')
          this.$router.push({ name: 'communitydetail', query: { id: this.id, pageNo: this.params.pageNo, type: this.params.type, keyWord: this.params.keyWord } })
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
