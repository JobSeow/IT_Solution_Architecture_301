<template>
  <v-container grid-list-md text-xs-center>
    <div class="spacer" />
    <v-layout row>
      <v-flex v-for="maskInfo in masksList" :key="maskInfo.id" sm4>
        <gourds-mask-info-card
          :maskInfo="maskInfo"
        >
          <div slot="header" class="card-header" />
        </gourds-mask-info-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../assets/api";

export default {
  data () {
    return {
      masksList: []
    }
  },
  mounted () {
    console.log(this.$store.state);
    if (!this.$store.state.isLoggedIn) {
      this.$router.push({ name: 'index' })
    }

    api.setToken(this.$store.state.token)

    api.get('api/masks')
      .then((response) => {
        if (response.success) {
          this.masksList = response.data
        }
      })
  }
}
</script>
<style>
  .spacer {
    height: 48px;
  }
</style>
