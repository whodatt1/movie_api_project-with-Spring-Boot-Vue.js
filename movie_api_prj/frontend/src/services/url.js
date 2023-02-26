import axios from 'axios'
import { setupInterceptors } from '../services/setupInterceptors'

function createInstance() {
  return axios.create({})
}

function createInstanceWithAuth() {
  const instance = axios.create({})
  return setupInterceptors(instance)
}

const instance = createInstance()
const instanceAuth = createInstanceWithAuth()
export { instance, instanceAuth }
