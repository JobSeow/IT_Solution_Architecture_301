import helper from '../assets/helper'

export const state = () => ({
  currentUser: {
    id: undefined,
    username: undefined,
    cart: []
  },
  isLoggedIn: false,
  token: null
})

export const mutations = {
  setCurrentUser (state, payload) {
    state.currentUser.id = payload.id;
    state.currentUser.username = payload.username;
    state.token = payload.token;

    if(payload.cart !== [] && payload.cart !== null) {
      state.currentUser.cart = payload.cart
    }

    state.isLoggedIn = helper.notEmpty(state.currentUser.username);
  },

  addToCart(state, item) {
    state.currentUser.cart.push(item)
  },

  clearCart(state, item) {
    state.currentUser.cart = []
  },

  clearCurrentUser(state) {
    state.currentUser.id = undefined;
    state.currentUser.username = undefined;
    state.currentUser.cart = [];
    state.isLoggedIn = false;
    state.token = undefined;
  }
}
