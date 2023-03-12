<template>
  <div class="container mt-3" style="margin-top: 3rem;">
    <h4>북마크</h4>
    <div class="row mt-3">
    <div class="col-10">
      <div>
      <div class="mb-5 row">
        <div v-if="!bookmarkList">현재 북마크에 추가하신 영화가 없습니다.</div>
        <div class="scroll-card" v-for="(item, idx) in bookmarkList" :key="idx" v-else>
          <router-link v-bind:to="`/moviedetail/${item.id}`"><img :src="'https://image.tmdb.org/t/p/original' + item.posterPath" class="card-img-top"></router-link>
          <div class="card-body">
            <h5 class="card-title">{{ item.title }}</h5>
            <p class="card-text"><small class="text-muted">{{ item.releaseDate }}</small></p>
          </div>
        </div>
      </div>
      </div>
      <button class="w-100 btn btn-lg btn-primary mb-3" @click="getMore" v-bind:style="{ visibility: moreVis }">MORE +</button>
    </div>
  </div>
  </div>
</template>
<script>
import BookmarkService from '@/services/bookmark.service'
export default {
  components: {},
  data() {
    return {
      pageNo: '',
      bookmarkList: [],
      moreVis: 'hidden',
      userId: ''
    }
  },
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  setup() {},
  created() {},
  mounted() {
    if (this.$route.query.pageNo) {
      this.pageNo = parseInt(this.$route.query.pageNo)
    }
    if (this.auth.user) {
      this.userId = this.auth.user.userId
    }
    this.getBookmarkByUserId(this.userId)
  },
  unmounted() {},
  methods: {
    getBookmarkByUserId(userId) {
      BookmarkService.getBookmarkListByUserId(userId, this.pageNo)
        .then((res) => {
          console.log(res)
          this.bookmarkList = res.data.movieList
          if (res.data.criteria.pageNo < res.data.endPage) {
            this.moreVis = 'visible'
          } else {
            this.moreVis = 'hidden'
          }
        }).catch((err) => {
          console.log(err)
        })
    },
    getMore() {
      this.pageNo += 1
      this.getBookmarkByUserId(this.userId)
    }
  }
}
</script>
<style scoped>
.scroll-card {
  max-width: 22.222%;
  padding: .75rem;
  border: 0;
  flex-basis: 22.222%;
  flex-grow: 0;
  flex-shrink: 0;
}
</style>
