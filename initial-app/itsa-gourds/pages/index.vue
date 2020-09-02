<template>
  <v-container id="bg" fill-height fluid>
    <v-layout justify-center align-center wrap>
      <v-flex xs12 md3 class="login-container">
        <v-layout>
          <v-flex xs12>
            <v-img :src="logo" class="logo-container" />
            <h4 class="text-center font-weight-medium text-wrap title-container">
              ITSA Gourds Mask Reseller 2
            </h4>
          </v-flex>
        </v-layout>
        <v-card
          class="mx-auto fields-container"
          outlined
        >
          <v-form>
            <v-container py-0>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field
                    v-model="username"
                    prepend-icon="face"
                    label="Username" />
                </v-flex>
                <v-flex xs12>
                  <v-text-field
                    v-model="password"
                    label="Password"
                    type="password"
                    prepend-icon="lock"
                    class="purple-input"
                  />
                </v-flex>
                <v-flex xs12>
                  <v-btn block color="primary" :loading="isSubmitting" @click="checkLogin()">
                    <span class="font-weight-bold">Login</span>
                  </v-btn>
                </v-flex>
              </v-layout>
            </v-container>
          </v-form>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import logo from '../assets/images/logo.png'
import api from '../assets/api'

export default {
  data: () => ({
    logo,
    isSubmitting: false,
    username: undefined,
    password: undefined
  }),
  mounted() {
    console.log(this.$store.state)
  },
  methods: {
    checkLogin () {
      this.isSubmitting = true;

      api.post('api/users/authenticate', { username: this.username, password: this.password })
        .then((response) => {
          this.isSubmitting = false;

          if (response.success) {
            this.$store.commit(
              'setCurrentUser',
              {
                id: response.data.id,
                username: response.data.username,
                token: response.data.jwttoken,
                cart: response.data.cartContent
              }
            );
            this.$router.push('masks')
          }
        })
    }
  }
}
</script>
<style>
  .logo-container {
    height: 128px;
    width: 128px;
    margin: auto auto 12px;
  }

  .title-container {
    width: 256px;
    margin: auto auto 12px;
  }

  .fields-container {
    padding: 16px;
  }
</style>
