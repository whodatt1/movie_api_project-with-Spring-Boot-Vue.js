import { instanceAuth, instance } from './url'

class CommunityService {
  createCommunity(formData) {
    return instanceAuth.post('/community/create', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  }

  getCommunityAll(params) {
    return instance.get('/community/pub/list', {
      params: {
        pageNo: params.pageNo,
        type: params.type,
        keyWord: params.keyWord
      }
    })
  }

  getCommunityDetail(id) {
    return instance.get(`/community/pub/detail/${id}`)
  }

  getCommunityDetailImg(commId) {
    return instance.get(`/community/pub/detail/img/${commId}`)
  }
}

export default new CommunityService()
