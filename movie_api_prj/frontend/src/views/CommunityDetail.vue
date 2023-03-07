<template>
  <div class="container" style="margin-top: 3rem;">
    <div>
      <div>
        <div class="comm-header">
          <div style="width: 30%;">
            {{ detail.writerId }}
          </div>
          <div style="width: 40%; text-align: center;">
            {{ detail.regDt }}
          </div>
          <div style="width: 30%; text-align: right;">
            <span>
              조회수 :
            </span>
            {{ detail.views }}
            <span>
              추천 :
            </span>
            {{ detail.vote }}
          </div>
        </div>
        <div>
          <h5 v-if="detail.category === 'normal'">[ 일반 ]</h5>
          <h5 v-else>[ 공지 ]</h5>
          <h2 class="mb-5">{{ detail.title }}</h2>
          <div class="mb-5">
            <div>
              <img :src="require(`../assets/uploadFile/${item.saveDir.replaceAll('\\', '/')}/${item.uuid}_${item.fileName}`)" v-for="(item, idx) in fileList" :key="idx">
            </div>
            {{ detail.content }}
          </div>
          <div style="text-align: center;" class="comm-bottom">
            <button class="btn btn-primary btn-lg"><i class="fa-regular fa-thumbs-up"></i> {{ detail.vote }}</button>
          </div>
        </div>
        <div style="text-align: right;" class="mb-5">
          <span v-if="auth.status.loggedIn">
            <button class="btn btn-danger btn-lg" v-if="detail.writerId === auth.user.userId">글 삭제</button>
            <button class="btn btn-warning btn-lg" v-if="detail.writerId === auth.user.userId" @click="deleteComm(detail.id)">글 수정</button>
          </span>
          <router-link :to="`/community?pageNo=${params.pageNo}&type=${params.type}&keyWord=${params.keyWord}`"><button class="btn btn-success btn-lg">글 목록</button></router-link>
        </div>
        <div>
          <h4><i class="fa-regular fa-message"></i>댓글</h4>
          <div v-if="auth.status.loggedIn" class="comm-userId">{{ auth.user.userId }}</div>
          <div class="comm-header">
            <textarea class="form-control" rows="3" style="width:90%;"></textarea>
            <button style="width:10%;" class="btn btn-success btn-lg">등록</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
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
      id: '',
      detail: {},
      fileList: {}
    }
  },
  setup() {},
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
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  unmounted() {},
  methods: {
    getCommunityDetail(id) {
      CommunityService.getCommunityDetail(id)
        .then((result) => {
          console.log(result)
          this.detail = result.data
        }).catch((err) => {
          console.log(err)
        })
    },
    getCommunityDetailImg(commId) {
      CommunityService.getCommunityDetailImg(commId)
        .then((result) => {
          console.log(result)
          this.fileList = result.data
        }).catch((err) => {
          console.log(err)
        })
    },
    deleteComm() {

    }
  }
}
</script>
<style scoped>
.comm-header {
  display: flex;
  justify-content: space-between;
  margin: 0 0 20px 0;
  padding: 14px 10px 16px;
  border-bottom: 1px solid #ccc;
}
.comm-bottom {
  margin: 0 0 20px 0;
  padding: 14px 10px 16px;
  border-bottom: 1px solid #ccc;
}
.comm-userId {
  padding: 0 10px;
}
</style>
