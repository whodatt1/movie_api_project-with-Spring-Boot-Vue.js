const app = {
  fileSizeValidation(fileName, fileSize) {
    const regExpImg = /.(jpg|jpeg|png|gif|PNG)$/
    const maxSize = 1024 * 1024 * 10

    if (!regExpImg.test(fileName)) {
      return 0
    } else if (maxSize < fileSize) {
      return 0
    } else {
      return 1
    }
  },

  getRegDtForCommunity(regDt) {
    const regDate = new Date(regDt.substring(0, 10))
    const today = new Date()

    const isSameDate = this.isSameDate(regDate, today)

    if (isSameDate) {
      return regDt.substring(11, 16)
    } else {
      return regDt.substring(5, 10)
    }
  },

  isSameDate(date1, date2) {
    return date1.getFullYear() === date2.getFullYear() &&
    date1.getMonth() === date2.getMonth() &&
    date1.getDate() === date2.getDate()
  }
}

export default app
