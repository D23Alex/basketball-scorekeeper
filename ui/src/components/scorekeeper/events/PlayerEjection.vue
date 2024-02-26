<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import {EJECTION_CAUSE_TRANSLATION} from "@/constants";
import SelectFromEnum from "@/components/scorekeeper/event-editing/SelectFromEnum.vue";

defineProps({
  playerEjection: {},
  players: Array,
  initialGameTimeInSeconds: Number,
  teamIdByPlayerId: {},
})
</script>

<script>
import Timer from "@/components/scorekeeper/Timer.vue";

export default {
  components: {Timer},
  methods: {
    updateEjectedPlayer(player) {
      this.playerEjection.ejectedPlayer = player;
      this.$emit('playerejectionchanged', this.playerEjection);
    },
    updateEjectionCause(cause) {
      this.playerEjection.ejectionCause = cause;
      this.$emit('playerejectionchanged', this.playerEjection);
    },
    updateTime(timeInSeconds) {
      this.playerEjection.millisecondsSinceStart = timeInSeconds * 1000;
      this.$emit('playerejectionchanged', this.playerEjection);
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updateEjectedPlayer" :team-id-by-player-id="teamIdByPlayerId"
                :label="'удалён'" :selected-player="playerEjection.ejectedPlayer" :players="players"/>
  <SelectFromEnum @selectedoptionchanged="updateEjectionCause"
                  :label="'причина'" :selected-option="playerEjection.ejectionCause === null? ['OTHER', 'другое']
                    : [playerEjection.ejectionCause, EJECTION_CAUSE_TRANSLATION[playerEjection.ejectionCause]]"
                  :options="EJECTION_CAUSE_TRANSLATION"/>
  <Timer @selectedtimechanged="updateTime"
         :initial-time="Math.floor(playerEjection.millisecondsSinceStart / 1000)"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>