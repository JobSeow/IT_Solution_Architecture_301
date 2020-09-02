import axios from 'axios'
import helper from "~/assets/helper";

const BASE_URL = 'https://itsamasks-backend.tk'
// const BASE_URL = process.env.BASE_URL
let headers = {
  'Content-Type': 'application/json'
}
let token = undefined;

const api = {
  apiAction (method, url, config) {
    this.checkHeader()
    return new Promise((resolve, reject) => {
      const actionObj = Object.assign({
        method,
        url: `${BASE_URL}/${url}`,
        headers
      }, config)
      axios(actionObj)
        .then((response) => {
          resolve(Object.assign({ success: true }, response))
        })
        .catch((err) => {
          console.log(err)
          resolve(Object.assign({ success: false }, err.response))
        })
    })
  },

  setToken (t) {
    token = t;
  },

  checkHeader() {
    if (helper.nullUndefinedOrBlank(token)) {
      headers = {
        'Content-Type': 'application/json'
      }
    } else {
      headers = {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Allow-Control-Allow-Methods': '*'
      }
    }
  },

  get (url, params = {}) {
    return this.apiAction('get', url, { params })
  },

  post (url, data) {
    return this.apiAction('post', url, { data })
  },

  put (url, data) {
    return this.apiAction('put', url, { data })
  },

  delete (url, data) {
    return this.apiAction('delete', url, { data })
  }
}

export default api
