<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import SelectBoolean from "@/components/scorekeeper/event-editing/SelectBoolean.vue";

defineProps({
  freeThrowAttempt: {},
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
    updateShooter(player) {
      this.freeThrowAttempt.shooter = player;
      this.$emit('freethrowattemptchanged', this.freeThrowAttempt);
    },
    updateIsSuccessful(isSuccessful) {
      this.freeThrowAttempt.isSuccessful = isSuccessful;
      this.$emit('freethrowattemptchanged', this.freeThrowAttempt);
    },
    updateTime(timeInSeconds) {
      this.freeThrowAttempt.millisecondsSinceStart = timeInSeconds * 1000;
      this.$emit('freethrowattemptchanged', this.freeThrowAttempt);
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updateShooter" :team-id-by-player-id="teamIdByPlayerId"
                :label="'бросил'" :selected-player="freeThrowAttempt.shooter" :players="players"/>
  <SelectBoolean @selectedbooleanchanged="updateIsSuccessful"
                 :label="'попадание'" :selected-value="freeThrowAttempt.isSuccessful"/>
  <Timer @selectedtimechanged="updateTime"
         :initial-time="Math.floor(freeThrowAttempt.millisecondsSinceStart / 1000)"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>