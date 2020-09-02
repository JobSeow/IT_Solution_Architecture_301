import Vue from 'vue'
import HelperOffset from '../components/helper/Offset'
import GourdsCard from '../components/gourds/Card'
import GourdsChartCard from '../components/gourds/ChartCard'
import GourdsNotification from '../components/gourds/Notification'
import GourdsStatsCard from '../components/gourds/StatsCard'
import ProfileMenu from '../components/ProfileMenu'
import GourdsMaskInfoCard from '../components/gourds/MaskInfoCard'
import GourdsParticipantsModal from '../components/gourds/ParticipantsModal'
import GourdsDatePicker from '../components/gourds/DatePicker'

const components = {
  HelperOffset,
  GourdsCard,
  GourdsChartCard,
  GourdsNotification,
  GourdsStatsCard,
  ProfileMenu,
  GourdsMaskInfoCard,
  GourdsParticipantsModal,
  GourdsDatePicker
}

Object.entries(components).forEach(([name, component]) => {
  Vue.component(name, component)
})
