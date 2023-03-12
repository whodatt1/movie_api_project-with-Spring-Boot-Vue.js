<template>
  <div class="movie-info mb-3">
    <div class="container">
        <div class="row">
          <div class="col-3">
            <img :src="'https://image.tmdb.org/t/p/original' + this.movie.posterPath" class="card-img-top">
          </div>
          <div class="col-9 description">
            <h3>{{ movie.title }}</h3>
            <div class="movie-realease mb-3">
              {{ movie.releaseDate }} (KR)
              <span v-for="(item, idx) in genreCd" :key="idx">
                {{ item.name }}<span v-if="idx != Object.keys(genreCd).length - 1">, </span>
              </span>
            </div>
            <h3>TMDB 회원 점수</h3>
            <div class="movie-realease mb-3">{{ movie.voteAverage }} / 10</div>
            <h3>MPI 회원 점수</h3>
            <div class="movie-realease mb-3">{{ average === '' ? 0 : average }} / 10</div>
            <h3>개요</h3>
            <div class="movie-realease mb-3">{{ movie.overview }}</div>
            <button class="bookmark" @click="createBookmark(movie.id)" v-if="!isBookmark"><i class="fa-regular fa-heart"></i></button>
            <button class="bookmark" @click="deleteBookmark(movie.id)" v-else><i class="fa-solid fa-heart"></i></button>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <h3>주요 출연진</h3>
      <div class="mb-3">
        <div class="scroll-row mb-5">
          <div class="scroll-card" v-for="(item, idx) in activeItemsCast" :key="idx">
            <img :src="'https://image.tmdb.org/t/p/original' + item.profile_path" class="card-img-top">
            <div class="card-body">
              <h5 class="card-title">{{ item.original_name }}</h5>
              <p class="card-text"><small class="text-muted">{{ item.character }}</small></p>
            </div>
          </div>
        </div>
      </div>
      <h3>리뷰</h3>
      <div class="text-center">별점을 선택해주세요.</div>
      <div>
        <div class="star-rating space-x-4 mx-auto text-center">
          <input type="radio" id="5-stars" name="rating" value="10" v-model="ratings"/>
          <label for="5-stars" class="star pr-4">★</label>
          <input type="radio" id="4-stars" name="rating" value="8" v-model="ratings"/>
          <label for="4-stars" class="star">★</label>
          <input type="radio" id="3-stars" name="rating" value="6" v-model="ratings"/>
          <label for="3-stars" class="star">★</label>
          <input type="radio" id="2-stars" name="rating" value="4" v-model="ratings"/>
          <label for="2-stars" class="star">★</label>
          <input type="radio" id="1-star" name="rating" value="2" v-model="ratings" />
          <label for="1-star" class="star">★</label>
        </div>
        <div class="comm-foot">
          <textarea class="form-control" rows="3" style="width:90%;" v-model="reviewArea"></textarea>
          <button style="width:10%;" class="btn btn-success btn-lg" @click="createReview">등록</button>
        </div>
        <div class="validation-div" v-if="errorMsgBag.content">{{ errorMsgBag.content }}</div>
        <div class="validation-div" v-if="errorMsgBag.ratings">{{ errorMsgBag.ratings }}</div>
      </div>
      <div>
          <ul style="list-style: none; margin: 0px; width: 100%">
            <li v-for="(item, idx) in reviewList" :key="idx" style="border-top: 1px solid gray" class="mt-3 mb-3">
              <div class="mb-3">
                <span style="-webkit-text-fill-color: gold;" v-for="(item, idx) in item.ratings / 2" :key="idx">
                  ★
                </span>
              </div>
              <strong>{{ item.writerId }} ( {{ item.regDt }} )</strong>
              <div style="float: right;" @click="reviewDel(item.id)" v-if="auth.status.loggedIn">
                <i class="fa-regular fa-circle-xmark" style="cursor: pointer;" v-if="item.writerId === auth.user.userId || auth.user.roles.includes('ROLE_ADMIN')"></i>
              </div>
              <div class="mt-3">{{ item.content }}</div>
            </li>
          </ul>
        </div>
    </div>
</template>
<script>
import MovieService from '@/services/movie.service'
import GenreService from '@/services/genre.service'
import BookmarkService from '@/services/bookmark.service'
import ReviewService from '@/services/review.service'

export default {
  components: {},
  data() {
    return {
      id: '',
      movie: {},
      genreCd: [],
      isBookmark: '',
      ratings: '',
      reviewArea: '',
      errorMsgBag: {},
      reviewList: [],
      average: '',
      cast: []
    }
  },
  computed: {
    activeItemsCast() {
      return this.cast.filter(item => item.profile_path)
    },
    auth() {
      return this.$store.state.auth
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.id = this.$route.params.id
    this.getMovieDetail(this.id)
    this.getIsBookmark(this.id)
    this.getReviewList(this.id)
    this.getRatingsAverage(this.id)
    this.getCredit(this.id)
  },
  unmounted() { },
  methods: {
    getCredit(movieId) {
      MovieService.getCreditForMovieId(movieId)
        .then((res) => {
          console.log(res.data)
          this.cast = res.data.cast
        }).catch((err) => {
          console.log(err)
        })
    },
    getRatingsAverage(movieId) {
      ReviewService.getRatingsAverage(movieId)
        .then((res) => {
          this.average = res.data
        }).catch((err) => {
          console.log(err)
        })
    },
    createReview() {
      const review = {
        content: this.reviewArea,
        movieId: this.id,
        ratings: this.ratings
      }

      if (this.auth.user) {
        review.writerId = this.auth.user.userId
      } else {
        alert('권한이 없습니다. 로그인 하여 주시기 바랍니다.')
        this.$router.push('/login')
      }

      ReviewService.createReivew(review)
        .then((res) => {
          alert('리뷰 작성이 완료되었습니다.')
          this.getReviewList(this.id)
          this.getRatingsAverage(this.id)
        }).catch((err) => {
          this.errorMsgBag = err.response.data
        })
    },
    getReviewList(movieId) {
      ReviewService.getReviewList(movieId)
        .then((res) => {
          if (res) {
            this.reviewList = res.data
          }
        }).catch((err) => {
          console.log(err)
        })
    },
    getIsBookmark(id) {
      const params = {
        movieId: id
      }

      if (this.auth.user) {
        params.userId = this.auth.user.userId
      } else {
        return
      }

      BookmarkService.getIsBookmark(params)
        .then((res) => {
          this.isBookmark = res.data
        }).catch((err) => {
          console.log(err)
        })
    },
    reviewDel(id) {
      if (confirm('리뷰를 삭제하시겠습니까?')) {
        const delParam = {
          id: id,
          movieId: this.id
        }

        if (this.auth.user) {
          delParam.userId = this.auth.user.userId
        }

        ReviewService.deleteReview(delParam)
          .then((res) => {
            alert('리뷰가 삭제되었습니다.')
            this.getReviewList(this.id)
          }).catch((err) => {
            console.log(err)
          })
      }
    },
    createBookmark(movieId) {
      if (!this.auth.user) {
        alert('권한이 없습니다. 로그인 하여 주시기 바랍니다.')
        return
      }

      if (confirm('북마크에 추가하시겠습니까?')) {
        const param = {
          movieId: movieId
        }

        if (this.auth.user) {
          param.userId = this.auth.user.userId
        }

        BookmarkService.createBookmark(param)
          .then((result) => {
            alert('추가가 완료되었습니다.')
            this.getIsBookmark(this.id)
          }).catch((err) => {
            console.log(err)
          })
      }
    },
    deleteBookmark(movieId) {
      if (!this.auth.user) {
        alert('권한이 없습니다. 로그인 하여 주시기 바랍니다.')
        return
      }

      if (confirm('북마크를 삭제하시겠습니까?')) {
        const param = {
          movieId: movieId
        }

        if (this.auth.user) {
          param.userId = this.auth.user.userId
        }

        BookmarkService.deleteBookmark(param)
          .then((result) => {
            alert('삭제가 완료되었습니다.')
            this.getIsBookmark(this.id)
          }).catch((err) => {
            console.log(err)
          })
      }
    },
    getMovieDetail(id) {
      MovieService.getMovieDetail(id)
        .then((result) => {
          this.movie = result.data
          this.getGenreCd(result.data.genreIds)
        }).catch((err) => {
          console.log(err)
        })
    },
    getGenreCd(genreIds) {
      GenreService.getGenreCd(genreIds)
        .then((result) => {
          this.genreCd = result.data
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
<style scoped>
.bookmark {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: none;
  background-color: bisque;
}
.movie-realease {
  font-size: 15px;
}
.description {
  padding: 3rem 0 3rem 0;
}
.movie-info{
  position: relative;
  padding: 3rem 0 3rem 0;
  background: no-repeat center center;
  background-color: #6c757d;
  background-size: cover;
  background-attachment: scroll;
  background-color: beige;
}
.comm-foot {
  display: flex;
  justify-content: space-between;
  margin: 0 0 20px 0;
  padding: 14px 10px 16px;
}
.star-rating {
  display: flex;
  flex-direction: row-reverse;
  font-size: 2.25rem;
  line-height: 2.5rem;
  justify-content: space-around;
  padding: 0 0.2em;
  text-align: center;
  width: 5em;
}
.scroll-row {
  align-items: stretch;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  overflow-x: auto;
  overflow-y: hidden;
}

.scroll-card {
  max-width: 22.222%;
  padding: .75rem;
  border: 0;
  flex-basis: 22.222%;
  flex-grow: 0;
  flex-shrink: 0;
}
.star-rating input {
  display: none;
}

.star-rating label {
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 2.3px;
  -webkit-text-stroke-color: #2b2a29;
  cursor: pointer;
}

.star-rating :checked ~ label {
  -webkit-text-fill-color: gold;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  -webkit-text-fill-color: #fff58c;
}
.scroll-row::-webkit-scrollbar {
  width: 6px;
}

.scroll-row::-webkit-scrollbar-thumb {
  height: 17%;
  background-color: black;
  border-radius: 10px;
}

.scroll-row::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: gray;
}
</style>
