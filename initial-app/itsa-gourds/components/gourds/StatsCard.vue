<template>
  <skh-card
    v-bind="$attrs"
    class="v-card--skh-stats"
    v-on="$listeners"
  >
    <v-card
      slot="offset"
      :class="`elevation-${elevation}`"
      :color="color"
      class="pa-4"
      dark
    >
      <v-icon size="40">
        {{ icon }}
      </v-icon>
    </v-card>

    <div class="text-right card-content">
      <p class="category grey--text font-weight-light" v-text="title" />
      <h3 class="title display-1 font-weight-light content-container">
        {{ value }} <small>{{ smallValue }}</small>
      </h3>
    </div>

    <template slot="actions">
      <v-icon class="mr-2" small>
        {{ subIcon }}
      </v-icon>
      <span class="caption grey--text font-weight-light">
        {{ subText }}
      </span>
    </template>
  </skh-card>
</template>

<script>
import Card from './Card'

export default {
  inheritAttrs: false,

  props: {
    ...Card.props,
    icon: {
      type: String,
      required: true
    },
    subIcon: {
      type: String,
      default: undefined
    },
    subIconColor: {
      type: String,
      default: undefined
    },
    subTextColor: {
      type: String,
      default: undefined
    },
    subText: {
      type: String,
      default: undefined
    },
    title: {
      type: String,
      default: undefined
    },
    value: {
      type: [Number, String],
      default: undefined
    },
    smallValue: {
      type: String,
      default: undefined
    }
  }
}
</script>

<style lang="scss">
.v-card--skh-stats {
  display: flex;
  flex-wrap: wrap;
  position: relative;
  margin-top: 0px !important;

  .v-offset {
    display: inline-block;
    flex: 0 1;
    margin-top: 0;
    margin-left: 0;
    margin-right: auto;
    margin-bottom: 0 !important;
    max-width: auto;
    padding: 0 16px 0;
  }

  .v-card {
    border-radius: 4px;
    flex: 0 1 auto;
  }

  .v-card__text {
    display: inline-block;
    position: absolute;
    top: 0;
    right: 0;
    width: 100%;
  }

  .v-card__actions {
    flex: 1 0 100%;
  }

  .card-content {
    margin-bottom: 24px !important;
  }

  .content-container {
    margin-top: -16px !important;
  }

}
</style>
