/* eslint-disable no-useless-escape */
export default {
  /* 객체가 비어있는지 체크 */
  isEmptyObj(obj) {
    if (obj.constructor === Object && Object.keys(obj).length === 0) {
      return true
    }

    return false
  }
}
