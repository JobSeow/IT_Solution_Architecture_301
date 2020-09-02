<template>
  <v-app>
    <v-navigation-drawer
      v-if="$store.state.isLoggedIn"
      v-model="drawerOpened"
      :clipped="false"
      fixed
      app
    >
      <v-list>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
          router
          exact
          @click.stop="drawerOpened = !drawerOpened"
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title v-text="item.title" />
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      v-if="$store.state.isLoggedIn"
      :clipped-left="false"
      dark
      fixed
      app
      :color="appBarColor"
    >
      <v-app-bar-nav-icon @click.stop="drawerOpened = !drawerOpened" />

      <v-toolbar-title></v-toolbar-title>

      <v-spacer />

      <v-btn icon @click.native="goToCheckOut()">
        <v-badge
          color="red"
          :content="cartCount"
          :value="cartCount"
          offset-x="12px"
          offset-y="8px"
        >
          <v-icon
            color="white"
          >
            shopping_cart
          </v-icon>
        </v-badge>
      </v-btn>
      <profile-menu v-if="$store.state.isLoggedIn" />
    </v-app-bar>
    <v-content>
      <nuxt />
    </v-content>
    <v-footer
      :fixed="fixed"
      app
    >
      <span>ITSA Gourds &copy; 2019</span>
    </v-footer>
  </v-app>
</template>

<script>
import ProfileMenu from '../components/ProfileMenu'

export default {
  components: { ProfileMenu },
  data () {
    return {
      drawerOpened: false,
      fixed: false,
      items: [
        {
          icon: 'home',
          title: 'All Masks',
          to: '/masks'
        },
        {
          icon: 'add',
          title: 'Add Mask',
          to: '/add'
        }
      ],
      miniVariant: false,
      title: "Gourds Mask Reseller",
      appBarColor: '#212121'
    }
  },
  computed: {
    cartCount () {
      return this.$store.state.currentUser.cart.length
    }
  },
  methods: {
    goToCheckOut () {
      this.$router.push({ name: 'checkout' })
    }
  }
}
</script>
