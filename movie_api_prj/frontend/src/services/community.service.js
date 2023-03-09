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

  getCommunityDetailForUpdate(id) {
    return instance.get(`/community/pub/update/detail/${id}`)
  }

  getCommunityDetailImg(commId) {
    return instance.get(`/community/pub/detail/img/${commId}`)
  }

  deleteCommunity(delParam) {
    return instanceAuth.post('/community/del', delParam)
  }

  deleteImgForUpdate(uuid) {
    return instanceAuth.post(`/community/deletefile/${uuid}`)
  }

  updateCommunity(formData) {
    return instanceAuth.post('/community/update', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  }

  voteCommunity(id) {
    return instanceAuth.post(`/community/vote/${id}`)
  }
}

export default new CommunityService()
