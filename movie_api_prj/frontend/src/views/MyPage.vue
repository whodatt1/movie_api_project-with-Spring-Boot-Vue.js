<template>
  <div class="mypage-info">
    <div class="container position-relative px-4 px-lg-5">
      <div class="row gx-4 gx-lg-5 justify-content-left">
        <div class="col-md-10 col-lg-8 col-xl-7">
          <div class="site-heading" style="color: white;">
            <h1>{{ auth.user.userId }} ( {{ auth.user.userNickNm }} )</h1>
            <span class="subheading">{{ auth.user.regDt }} 부터 회원</span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container mt-3">
    <h3>통계</h3>
    <div class="row">
      <div class="col-6">
        <div>총 평가 수</div>
        <h1 style="color: silver;">{{ reviewData.allCnt }}</h1>
      </div>
      <div class="col-6">
        <div>평점 분포</div>
        <canvas id="myChart"></canvas>
      </div>
    </div>
    <div class="row mt-3 mb-3">
      <h3>회원정보 관리</h3>
      <div class="col-6">
        <router-link to="/updateuser"><button class="btn btn-warning btn-lg">회원정보 수정</button></router-link>
      </div>
      <div class="col-6">
        <router-link to="/deleteuser"><button class="btn btn-danger btn-lg">회원 탈퇴</button></router-link>
      </div>
    </div>
  </div>
</template>
<script>
import ReviewService from '@/services/review.service'
import Chart from 'chart.js/auto'
export default {
  components: {},
  data() {
    return {
      reviewData: {}
    }
  },
  setup() {},
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  created() {},
  mounted() {
    this.getReviewData(this.auth.user.userId)
  },
  unmounted() {},
  methods: {
    getReviewData(userId) {
      ReviewService.getReviewDataForUserId(userId)
        .then((res) => {
          this.reviewData = res.data
          this.drawChart(res.data)
        }).catch((err) => {
          console.log(err)
        })
    },
    drawChart(reviewData) {
      const data = {
        labels: ['2', '4', '6', '8', '10'],
        datasets: [
          {
            label: 'Count',
            data: [reviewData.cnt2, reviewData.cnt4, reviewData.cnt6, reviewData.cnt8, reviewData.cnt10],
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
          }
        ]
      }

      const options = {
        responsive: false,
        maintainAspectRatio: false,
        width: 100,
        height: 100,
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true
              },
              gridLines: {
                display: true
              },
              scaleLabel: {
                display: true,
                labelString: 'Count'
              }
            }
          ]
        }
      }

      // eslint-disable-next-line no-new
      new Chart(document.getElementById('myChart'), {
        type: 'bar',
        data: data,
        options: options
      })
    }
  }
}
</script>
<style scoped>
.mypage-info {
  position: relative;
  padding-top: 3rem;
  padding-bottom: 3rem;
  background: no-repeat center center;
  background-color: #6c757d;
  background-size: cover;
  background-attachment: scroll;
  background-image: url('../assets/mypage.png');
}
</style>
