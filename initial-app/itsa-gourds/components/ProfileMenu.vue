<template>
  <v-menu
    :close-on-content-click="false"
    :nudge-width="200"
    offset-y
  >
    <template v-slot:activator="{ on }">
      <v-btn
        icon
        v-on="on"
      >
        <v-icon>account_circle</v-icon>
      </v-btn>
    </template>

    <v-card>
      <v-list>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>{{$store.state.currentUser.username}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-divider />

      <v-card-actions>
        <v-btn text>Settings</v-btn>
        <v-spacer />
        <v-btn color="red" text @click="logout()">Logout</v-btn>
      </v-card-actions>
    </v-card>
  </v-menu>
</template>

<script>
import api from "../assets/api";

export default {
  name: 'ProfileMenu',
  methods: {
    logout () {
      console.log("logout")
      api.setToken(undefined);
      this.$store.commit(
        'clearCurrentUser'
      )
      this.$router.push({ name: 'index' })
    }
  }
}
</script>

<style scoped>

</style>
