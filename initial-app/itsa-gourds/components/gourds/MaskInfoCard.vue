<template>
  <v-card
    v-bind="$attrs"
    :style="styles"
    v-on="$listeners"
    class="card"
  >
    <helper-offset
      v-if="hasOffset"
      :inline="inline"
      :full-width="fullWidth"
      :offset="offset"
    >
      <v-layout justify-center align-center wrap>
        <img
          :src="maskInfo.image"
          :alt="maskInfo.name"
          height="96"
          width="96"
          class="mask-image"
        >
      </v-layout>
    </helper-offset>

    <v-card-text class="card-content">
      <v-layout>
        <p
          class="title font-weight-light mb-2"
          v-text="maskInfo.name"
        />
        <v-spacer />
        <p
          v-if="maskInfo.description"
          class="title"
          align="end"
        >
          ${{maskInfo.price}}
        </p>
      </v-layout>

      <p
        v-if="maskInfo.description"
        class="category font-weight-thin"
        v-text="maskInfo.description"
      />
    </v-card-text>

    <v-card-actions>
      <v-spacer />
      <v-btn color="primary" text @click="viewMask()">View Mask</v-btn>
      <v-spacer />
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  inheritAttrs: false,

  props: {
    maskInfo: {
      type: Object,
      default () {
        return ({})
      }
    },
    elevation: {
      type: [Number, String],
      default: 10
    },
    inline: {
      type: Boolean,
      default: false
    },
    fullWidth: {
      type: Boolean,
      default: false
    },
    offset: {
      type: [Number, String],
      default: 48
    }
  },

  methods: {
    viewMask () {
      console.log("ViewMask()")
      this.$router.push(
        {
          name: 'mask',
          query: this.maskInfo
        }
      )
    }
  },

  computed: {
    hasOffset () {
      return this.$slots.header ||
        this.$slots.offset ||
        this.maskInfo.name ||
        this.maskInfo.description
    },
    styles () {
      if (!this.hasOffset) { return null }

      return {
        marginBottom: `${this.offset}px`,
        marginTop: `${this.offset * 2}px`
      }
    }
  }
}
</script>

<style lang="scss">
  .card {
    margin-top: 0px !important;
  }

  .v-card--skh {
    &__header {
      &.v-card {
        border-radius: 4px;
      }
    }
  }

  .card-content {
    height: 96px;
  }

  .mask-image {
    margin-top: 24px;
  }
</style>
