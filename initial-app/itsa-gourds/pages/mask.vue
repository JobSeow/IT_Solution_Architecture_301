<template>
  <v-container grid-list-md text-xs-center>
    <v-card
      class="mx-auto mask-info-card"
      outlined
    >
      <v-layout row>
        <v-spacer />

        <img
          :src="maskInfo.image"
          :alt="maskInfo.name"
          class="mask-image"
        >

        <v-spacer />
        <v-divider vertical inset/>
        <v-spacer />

        <v-layout column>
          <p
            class="title font-weight-light mb-2"
            v-text="maskInfo.name"
          />

          <p
            v-if="maskInfo.price"
            class="title"
          >
            ${{maskInfo.price}}
          </p>

          <p
            v-if="maskInfo.description"
            class="category font-weight-thin"
            v-text="maskInfo.description"
          />

          <v-select
            v-model="quantity"
            :items="items"
            label="Quantity"
            outlined
          />

          <v-btn color="primary" @click.native="addToCart()" >
            <v-icon left>mdi-cart</v-icon> Add to cart
          </v-btn>

        </v-layout>
      </v-layout>
    </v-card>
  </v-container>
</template>

<script>
import api from "../assets/api";

export default {
  data() {
    return {
      maskInfo: null,
      quantity: 0,
      items: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
    }
  },
  methods: {
    addToCart () {
      console.log("Add to cart")
      console.log(this.$store.state.currentUser.cart)

      api.post(`api/users/${this.$store.state.currentUser.id}/carts`, { bodyContent: this.$store.state.currentUser.cart } )
        .then((response) => {
          console.log(response)
        })

      this.$store.commit(
        'addToCart',
        {
          maskInfo: this.maskInfo,
          quantity: this.quantity
        }
      )
    }
  },
  formatRequestBody() {

  },
  mounted() {
    console.log(this.$store.state)
    if (!this.$store.state.isLoggedIn) {
      this.$router.push({name: 'index'})
    }
  },
  created() {
    this.maskInfo = this.$route.query
  }
}
</script>
<style>
  .mask-info-card {
    padding: 16px;
  }
</style>
