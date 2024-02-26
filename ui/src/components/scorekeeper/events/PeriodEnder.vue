<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import {PERIOD_TRANSLATION} from "@/constants";
import SelectFromEnum from "@/components/scorekeeper/event-editing/SelectFromEnum.vue";

defineProps({
  periodEnder: {},
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
      this.periodEnder.player = player;
      this.$emit('periodenderchanged', this.periodEnder);
    },
    updatePeriod(period) {
      this.periodEnder.period = period;
      this.$emit('periodenderchanged', this.periodEnder);
    },
  },
}
</script>

<template>
  <div class="period-ender">
    <SelectPlayer @selectedplayerchanged="updatePlayer" :team-id-by-player-id="this.teamIdByPlayerId"
                  :label="'вышел с площадки'" :selected-player="periodEnder.player" :players="players"/>
    <SelectFromEnum @selectedoptionchanged="updatePeriod"
                    :label="'период'" :selected-option="[periodEnder.period, PERIOD_TRANSLATION[periodEnder.period]]"
                    :options="PERIOD_TRANSLATION"/>
  </div>
</template>

<style scoped>
.period-ender {
  display: flex;
  flex-direction: row;
}
</style>