<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import {TURNOVER_CAUSE_TRANSLATION} from "@/constants";
import SelectFromEnum from "@/components/scorekeeper/event-editing/SelectFromEnum.vue";

defineProps({
  turnover: {},
  players: Array,
  initialGameTimeInSeconds: Number,
})
</script>

<script>
import Timer from "@/components/scorekeeper/Timer.vue";

export default {
  components: {Timer},
  methods: {
    updatePlayer(player) {
      this.turnover.player = player;
      this.$emit('turnoverchanged', this.turnover);
    },
    updateStealer(player) {
      this.turnover.stealer = player;
      this.$emit('turnoverchanged', this.turnover);
    },
    updateCause(cause) {
      this.turnover.cause = cause;
      this.$emit('turnoverchanged', this.turnover);
    },

    updateTime(timeInSeconds) {
      this.turnover.millisecondsSinceStart = timeInSeconds * 1000;
      this.$emit('turnoverchanged', this.turnover);
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updatePlayer"
                :label="'потеря'" :selected-player="turnover.player" :players="players"/>
  <SelectPlayer @selectedplayerchanged="updateStealer"
                :label="'перехват'" :selected-player="turnover.stealer" :players="players"/>
  <SelectFromEnum @selectedoptionchanged="updateCause"
                  :label="'причина'" :selected-option="['OTHER', 'другое']" :options="TURNOVER_CAUSE_TRANSLATION"/>
  <Timer @selectedtimechanged="updateTime"
         :initial-time="Math.floor(turnover.millisecondsSinceStart / 1000)"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>