<template>
  <v-menu
    v-model="menu"
    :close-on-content-click="false"
    :nudge-right="40"
    transition="scale-transition"
    offset-y
    min-width="290px"
  >
    <template v-slot:activator="{ on }">
      <v-text-field
        v-model="formattedDate"
        :label="label"
        prepend-icon="mdi-calendar"
        readonly
        :value="formattedDate"
        :rules="[v => !!v || 'Item is required']"
        clearable
        v-on="on"
      />
    </template>

    <v-date-picker v-model="date" @input="selected" :max="today" />
  </v-menu>
</template>

<script>
export default {
  props: {
    label: {
      type: String,
      default: 'Date'
    }
  },
  data () {
    return {
      today: new Date().toISOString().substr(0, 10),
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      formattedDate: ''
    }
  },
  watch: {
    date (val, oldVal) {
      const year = val.slice(0, 4)
      const month = val.slice(5, 7)
      const day = val.slice(8, 10)
      this.formattedDate = `${day}-${month}-${year}`
    }
  },
  methods: {
    close () {
      this.menu = false
    },
    selected () {
      this.$emit('selected', this.formattedDate)
      this.menu = false
    }
  }
}
</script>

<style scoped>

</style>
