<template>
  <v-dialog v-model="dialog" max-width="540px" height="540px" persistent>
    <template v-slot:activator="{ on }">
      <v-btn slot="activator" color="primary" v-on="on">
        <v-icon small>mdi-account-multiple</v-icon>
        <span>Compare Participants</span>
      </v-btn>
    </template>

      <v-card>
      <v-card-title class="primary elevation-1">
        <span class="headline card-title-text">Compare Participants</span>
      </v-card-title>

      <v-card-content>
        <v-container>
          <v-form ref="form">
            <skh-participant-multi-select
              label="Group A"
              :participants="participants"
              @selected="updateGroupA"
            />

            <skh-participant-multi-select
              label="Group B"
              :participants="participants"
              @selected="updateGroupB"
            />

            <skh-date-picker
              label="Date From"
              @selected="updateDateFrom"
            />

            <skh-date-picker
              label="Date To"
              @selected="updateDateTo"
            />
          </v-form>
        </v-container>
      </v-card-content>

      <v-card-actions>
        <v-spacer />
        <v-btn text @click.native="close()">
          Close
        </v-btn>
        <v-btn color="primary" @click.native="compare()" >
          Compare
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: {
    participants: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  data () {
    return {
      dialog: false,
      groupA: [],
      groupB: [],
      dateFrom: undefined,
      dateTo: undefined
    }
  },
  methods: {
    updateGroupA (selected) {
      this.groupA = selected
    },
    updateGroupB (selected) {
      this.groupB = selected
    },
    updateDateFrom (date) {
      this.dateFrom = date
    },
    updateDateTo (date) {
      this.dateTo = date
    },
    close () {
      this.dialog = false
    },
    compare () {
      if (this.$refs.form.validate()) {
        this.dialog = false
      }
    }
  }
}
</script>

<style lang="scss">
  .card-title-text { color: white }
</style>
