<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import {PERIOD_TRANSLATION} from "@/constants";
import SelectFromEnum from "@/components/scorekeeper/event-editing/SelectFromEnum.vue";

defineProps({
  periodStarter: {},
  players: Array,
  teamIdByPlayerId: {},
})
</script>

<script>
import Timer from "@/components/scorekeeper/Timer.vue";

export default {
  components: {Timer},
  methods: {
    updatePlayer(player) {
      this.periodStarter.player = player;
      this.$emit('periodstarterchanged', this.periodStarter);
    },
    updatePeriod(period) {
      this.periodStarter.period = period;
      this.$emit('periodstarterchanged', this.periodStarter);
    },
  },
}
</script>

<template>
<div class="period-starter">
  <SelectPlayer @selectedplayerchanged="updatePlayer" :team-id-by-player-id="this.teamIdByPlayerId"
                :label="'вышел на площадку'" :selected-player="periodStarter.player" :players="players"/>
  <SelectFromEnum @selectedoptionchanged="updatePeriod"
                  :label="'период'" :selected-option="[periodStarter.period, PERIOD_TRANSLATION[periodStarter.period]]"
                  :options="PERIOD_TRANSLATION"/>
</div>
</template>

<style scoped>
.period-starter {
  display: flex;
  flex-direction: row;
}
</style>