<template>
  <v-container grid-list-md text-xs-center>
    <v-card
      class="mx-auto mask-info-card"
      outlined
    >
      <v-layout v-for="(cart, i) in $store.state.currentUser.cart" :key="i" align-center="true" class="item-row">

        <v-img :src="cart.maskInfo.image" height="56" max-width="56" />

        <v-divider inset vertical class="inset-divider"/>

        <p
          class="body-1 font-weight-light cart-text"
          v-text="cart.maskInfo.name"
        />

        <v-spacer />

        <p class="font-weight-thin cart-text quantity-text">Quantity: </p>
        <p
          class="title font-weight-light cart-text"
          v-text="cart.quantity"
        />

        <v-divider inset vertical class="inset-divider" />

        <p class="font-weight-thin cart-text quantity-text">Price: </p>
        <p
          class="title font-weight-light cart-text"
        >
          ${{cart.maskInfo.price}}
        </p>
      </v-layout>

      <v-row class="total-text">
        <v-spacer />
        <p class="title font-weight-thin total-text-label">Total: </p>
        <p class="title">${{formattedTotalPrice}}</p>
      </v-row>

      <v-card-actions>
        <v-btn text color="primary" @click.native="clearCart()">
          <v-icon left>close</v-icon> Clear Cart
        </v-btn>
        <v-spacer />
        <v-btn color="primary" @click.native="checkOut()">
          <v-icon left>shopping_cart</v-icon> Checkout
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import helper from '../assets/helper'

export default {
  beforeCreate() {
    if (!this.$store.state.isLoggedIn) {
      this.$router.push({name: 'index'})
    }
  },

  computed: {
    totalPrice () {
      let total = 0
      this.$store.state.currentUser.cart.forEach(item => {
          // To take care of rounding errors
          total += item.maskInfo.price * 10 * item.quantity * 10 / 100
        }
      )
      return total
    },

    formattedTotalPrice () {
      return helper.addZeroes(this.totalPrice.toString())
    }
  },

  methods: {
    clearCart () {
      this.$store.commit(
        'clearCart'
      )
    },
    checkOut () {
      console.log("checkout")
    }
  }
}
</script>
<style>
  .item-row {
    height: 80px;
    padding: 8px;
    background-color: #F5F5F5;
    margin-bottom: 8px !important;
    border-radius: 8px;
  }

  .total-text {
    padding: 8px;
  }

  .total-text-label {
    margin-right: 8px;
  }

  .inset-divider {
    margin-left: 8px;
    margin-right: 8px;
  }

  .cart-text {
    margin-top: 12px;
  }

  .quantity-text {
    margin-right: 8px;
  }

  .mask-info-card {
    padding: 16px
  }
</style>
