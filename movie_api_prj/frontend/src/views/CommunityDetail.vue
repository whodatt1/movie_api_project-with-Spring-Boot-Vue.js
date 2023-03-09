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
            <button class="btn btn-primary btn-lg" @click="voteComm(detail.id)"><i class="fa-regular fa-thumbs-up"></i> {{ detail.vote }}</button>
          </div>
        </div>
        <div style="text-align: right;" class="mb-5">
          <span v-if="auth.status.loggedIn">
            <button class="btn btn-danger btn-lg" v-if="detail.writerId === auth.user.userId || auth.user.roles.includes('ROLE_ADMIN')" @click="communityDel(detail.id)">글 삭제</button>
            <router-link :to="`/communityupdate?id=${detail.id}&pageNo=${params.pageNo}&type=${params.type}&keyWord=${params.keyWord}`" v-if="detail.writerId === auth.user.userId"><button class="btn btn-warning btn-lg">글 수정</button></router-link>
          </span>
          <router-link :to="`/community?id=${detail.id}&pageNo=${params.pageNo}&type=${params.type}&keyWord=${params.keyWord}`"><button class="btn btn-success btn-lg">글 목록</button></router-link>
        </div>
        <div>
          <h4><i class="fa-regular fa-message"></i>댓글 ( {{ detail.replyCount }} )</h4>
          <strong v-if="auth.status.loggedIn" class="comm-userId">{{ auth.user.userId }}</strong>
          <div class="comm-foot">
            <textarea class="form-control" rows="3" style="width:90%;" v-model="commentArea"></textarea>
            <button style="width:10%;" class="btn btn-success btn-lg" @click="createComment">등록</button>
          </div>
          <div class="validation-div" v-if="errorMsgBag.content">{{ errorMsgBag.content }}</div>
        </div>
        <div>
          <ul style="list-style: none; margin: 0px; width: 100%">
            <li v-for="(item, idx) in commentList" :key="idx" style="border-top: 1px solid gray" class="mt-3 mb-3">
              <strong>{{ item.writerId }} ( {{ item.regDt }} )</strong>
              <div style="float: right;" @click="reviewDel(item.id)" v-if="auth.status.loggedIn">
                <i class="fa-regular fa-circle-xmark" style="cursor: pointer;" v-if="item.writerId === auth.user.userId || auth.user.roles.includes('ROLE_ADMIN')"></i>
              </div>
              <div class="mt-3">{{ item.content }}</div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CommunityService from '@/services/community.service'
import CommentService from '@/services/comment.service'

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
      fileList: [],
      commentArea: '',
      commentList: [],
      errorMsgBag: {}
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
    this.getCommentList(this.id)
  },
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  unmounted() {},
  methods: {
    voteComm(id) {
      if (!this.auth.user) {
        alert('권한이 없습니다. 로그인 하여 주시기 바랍니다.')
        return
      }

      if (confirm('추천하시겠습니까?')) {
        CommunityService.voteCommunity(id)
          .then((result) => {
            alert('추천이 완료되었습니다.')
            this.getCommunityDetail(this.id)
          }).catch((err) => {
            console.log(err)
          })
      }
    },
    getCommunityDetail(id) {
      CommunityService.getCommunityDetail(id)
        .then((result) => {
          this.detail = result.data
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
    getCommentList(commId) {
      CommentService.getCommentList(commId)
        .then((res) => {
          if (res) {
            this.commentList = res.data
          }
        }).catch((err) => {
          console.log(err)
        })
    },
    createComment() {
      const comment = {
        content: this.commentArea,
        commId: this.id
      }

      if (this.auth.user) {
        comment.writerId = this.auth.user.userId
      } else {
        alert('권한이 없습니다. 로그인 하여 주시기 바랍니다.')
        this.$router.push('/login')
      }

      CommentService.createComment(comment)
        .then((res) => {
          if (res) {
            alert('댓글 등록이 완료되었습니다.')
            this.getCommunityDetail(this.id)
            this.getCommentList(this.id)
          }
        }).catch((err) => {
          this.errorMsgBag = err.response.data
          console.log(this.errorMsgBag.content)
        })
    },
    commentDel(id) {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        const delParam = {
          id: id,
          commId: this.id
        }

        if (this.auth.user) {
          delParam.userId = this.auth.user.userId
        }

        CommentService.deleteComment(delParam)
          .then((res) => {
            alert('댓글이 삭제되었습니다.')
            this.getCommunityDetail(this.id)
            this.getCommentList(this.id)
          }).catch((err) => {
            console.log(err)
          })
      }
    },
    communityDel(id) {
      if (confirm('글을 삭제하시겠습니까?')) {
        const delParam = {
          id: id
        }

        if (this.auth.user) {
          delParam.userId = this.auth.user.userId
        }

        CommunityService.deleteCommunity(delParam)
          .then((res) => {
            alert('글이 삭제되었습니다.')
            this.$router.push({ name: 'community', query: { pageNo: 1 } })
          })
      }
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
.comm-foot {
  display: flex;
  justify-content: space-between;
  margin: 0 0 20px 0;
  padding: 14px 10px 16px;
}
.comm-userId {
  padding: 0 10px;
}
</style>
