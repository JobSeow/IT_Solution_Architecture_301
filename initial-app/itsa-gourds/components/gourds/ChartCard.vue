<template>
  <skh-card
    v-bind="$attrs"
    class="v-card--skh-chart"
    v-on="$listeners"
  >
    <div slot="header" class="card-header">
      <h3 class="title font-weight-light">
        {{ title }}
      </h3>
    </div>

    <bar-chart
      v-if="type === 'Bar'"
      :chart-data="chartData"
      :chart-options="chartOptions"
      :height="256"
    />
    <pie-chart
      v-if="type === 'Pie'"
      :chart-data="chartData"
      :chart-options="chartOptions"
      :height="256"
    />
    <line-chart
      v-if="type === 'Line'"
      :chart-data="chartData"
      :chart-options="chartOptions"
      :height="256"
    />
    <stack-bar-chart
      v-if="type === 'StackedBar'"
      :chart-data="chartData"
      :height="256"
    />
    <horizontal-stack-bar-chart
      v-if="type === 'HorizontalStackedBar'"
      :chart-data="chartData"
      :height="256"
    />

    <v-icon class="mr-2" small>
      mdi-clock-outline
    </v-icon>
    <span class="caption grey--text font-weight-light">
      {{ lastUpdated }}
    </span>
  </skh-card>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    title: {
      type: String,
      default: undefined
    },
    chartData: {
      type: Object,
      default: () => ({})
    },
    chartOptions: {
      type: Object,
      default () {
        return {
          responsive: true,
          maintainAspectRatio: false,
          legend: {
            display: true
          }
        }
      }
    },
    lastUpdated: {
      type: String,
      default: undefined
    },
    type: {
      type: String,
      required: true,
      validator: v => ['Bar', 'Line', 'Pie'].includes(v)
    }
  }
}
</script>

<style lang="scss">
  .v-card--skh-chart {
    margin-top: 0px !important;

    .v-card--skh__header {
      .ct-label {
        color: inherit;
        opacity: .7;
        font-size: 0.975rem;
        font-weight: 100;
      }

      .ct-grid{
        stroke: rgba(255, 255, 255, 0.2);
      }
      .ct-series-a .ct-point,
      .ct-series-a .ct-line,
      .ct-series-a .ct-bar,
      .ct-series-a .ct-slice-donut {
          stroke: rgba(255,255,255,.8);
      }
      .ct-series-a .ct-slice-pie,
      .ct-series-a .ct-area {
          fill: rgba(255,255,255,.4);
      }
    }
  }

  .card-header {
    padding: 8px;
  }
</style>
