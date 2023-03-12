<template>
    <div class="container" style="margin-top: 3rem;">
      <div class="row">
        <div class="col-2">
          <select class="form-select sort-by mb-3" v-model="sortBy">
            <option value="" selected>정렬 기준 선택</option>
            <option value="popDesc" selected>인기도 내림차순</option>
            <option value="popAsc">인기도 오름차순</option>
            <option value="voteDesc">평점 내림차순</option>
            <option value="voteAsc">평점 오름차순</option>
            <option value="relDesc">상영일 내림차순</option>
            <option value="relAsc">상영일 오름차순</option>
          </select>
          <select class="form-select sort-by mb-3" v-model="genre">
            <option value="" selected>장르 선택</option>
            <option v-for="(item, idx) in genreCdAll" :key="idx" :value="item.id">
              {{ item.name }}
            </option>
          </select>
          <button class="w-100 btn btn-lg btn-primary sort-btn" @click="getResult">적용</button>
        </div>
        <div class="col-10">
          <div>
          <div class="mb-5 row">
            <div class="scroll-card" v-for="(item, idx) in movieList" :key="idx">
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
import GenreService from '@/services/genre.service'
import MovieService from '@/services/movie.service'

export default {
  components: {},
  data() {
    return {
      pageNo: 1,
      sortBy: '',
      genre: '',
      genreCdAll: [],
      movieList: [],
      moreVis: 'visible',
      adult: false
    }
  },
  setup() {},
  created() {},
  mounted() {
    if (this.$route.query.pageNo) {
      this.pageNo = parseInt(this.$route.query.pageNo)
    }
    if (this.$route.query.adult) {
      this.adult = this.$route.query.adult
    }
    this.getGenreCdAll()
    this.getMovieListAll(this.pageNo, this.adult)
  },
  unmounted() {},
  methods: {
    getGenreCdAll() {
      GenreService.getGenreCdAll()
        .then((result) => {
          this.genreCdAll = result.data
        }).catch((err) => {
          console.log(err)
        })
    },
    getMovieListAll(pageNo, adult) {
      MovieService.getMovieListAll(pageNo, adult)
        .then((result) => {
          console.log(result)
          this.movieList = result.data.movieList
        }).catch((err) => {
          console.log(err)
        })
    },
    getResult() {
      this.pageNo = 1

      let genre = this.genre
      let sortBy = this.sortBy
      const adult = this.adult

      if (this.genre === '') {
        genre = 'none'
      }

      if (this.sortBy === '') {
        sortBy = 'none'
      }

      MovieService.getMovieListAllForResult(this.pageNo, genre, sortBy, adult)
        .then((result) => {
          this.movieList = result.data.movieList
          if (result.data.criteria.pageNo < result.data.endPage) {
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
      let genre = this.genre
      let sortBy = this.sortBy
      const adult = this.adult

      if (this.genre === '') {
        genre = 'none'
      }

      if (this.sortBy === '') {
        sortBy = 'none'
      }

      MovieService.getMovieListAllForResult(this.pageNo, genre, sortBy, adult)
        .then((result) => {
          this.movieList = result.data.movieList
          if (result.data.criteria.pageNo < result.data.endPage) {
            this.moreVis = 'visible'
          } else {
            this.moreVis = 'hidden'
          }
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
<style scoped>
.sort-by {
  border-radius: 10px;
}
.sort-btn {
  border-radius: 30px;
}
.search-input {
  height: 50px;
  font-size: 15px;
  border: 0;
  border-radius: 30px;
  outline: none;
  background-color: rgb(233, 233, 233);
}
.scroll-card {
  max-width: 22.222%;
  padding: .75rem;
  border: 0;
  flex-basis: 22.222%;
  flex-grow: 0;
  flex-shrink: 0;
}
</style>
