<template>
  <div class="container" style="margin-top: 3rem;">
    <div class="input-group mb-5">
      <input type="text" class="form-control search-input" placeholder="영화를 검색하세요" v-model="title">
      <span class="input-group-btn">
        <button class="btn btn-default" @click="getResult">Search</button>
      </span>
    </div>
    <div class="row">
      <div class="col-12">
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
import MovieService from '@/services/movie.service'

export default {
  components: {},
  data() {
    return {
      pageNo: 1,
      title: '',
      movieList: [],
      moreVis: 'visible'
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getMovieListAll()
  },
  unmounted() {},
  methods: {
    getMovieListAll() {
      this.pageNo = 1

      MovieService.getMovieListAll(this.pageNo)
        .then((result) => {
          this.movieList = result.data.movieList
        }).catch((err) => {
          console.log(err)
        })
    },
    getResult() {
      this.pageNo = 1

      let title = this.title

      if (this.title === '') {
        title = 'none'
      }

      MovieService.getMovieListAllForResultTitle(this.pageNo, title)
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

      let title = this.title

      if (this.title === '') {
        title = 'none'
      }

      MovieService.getMovieListAllForResultTitle(this.pageNo, title)
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
