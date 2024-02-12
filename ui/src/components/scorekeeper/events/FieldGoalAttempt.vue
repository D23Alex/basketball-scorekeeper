<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import SelectBoolean from "@/components/scorekeeper/event-editing/SelectBoolean.vue";

defineProps({
  fieldGoalAttempt: {},
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
      this.fieldGoalAttempt.shooter = player;
      this.$emit('fieldgoalattemptchanged', this.fieldGoalAttempt);
    },
    updateAssistant(player) {
      this.fieldGoalAttempt.assistant = player;
      this.$emit('fieldgoalattemptchanged', this.fieldGoalAttempt);
    },
    updateBlockedBy(player) {
      this.fieldGoalAttempt.blockedBy = player;
      this.$emit('fieldgoalattemptchanged', this.fieldGoalAttempt);
    },
    updateReboundedBy(player) {
      this.fieldGoalAttempt.reboundedBy = player;
      this.$emit('fieldgoalattemptchanged', this.fieldGoalAttempt);
    },
    updateIsSuccessful(isSuccessful) {
      this.fieldGoalAttempt.isSuccessful = isSuccessful;
      this.$emit('fieldgoalattemptchanged', this.fieldGoalAttempt);
    },
    updateTime(timeInSeconds) {
      this.fieldGoalAttempt.millisecondsSinceStart = timeInSeconds * 1000;
      this.$emit('fieldgoalattemptchanged', this.fieldGoalAttempt);
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updateShooter" :team-id-by-player-id="this.teamIdByPlayerId"
                :label="'бросил'" :selected-player="fieldGoalAttempt.shooter" :players="players"/>
  <SelectPlayer @selectedplayerchanged="updateAssistant" :team-id-by-player-id="this.teamIdByPlayerId"
                :label="'результативная передача'" :selected-player="fieldGoalAttempt.assistant" :players="players"/>
  <SelectPlayer @selectedplayerchanged="updateReboundedBy" :team-id-by-player-id="this.teamIdByPlayerId"
                :label="'подобрал'" :selected-player="fieldGoalAttempt.reboundedBy" :players="players"/>
  <SelectPlayer @selectedplayerchanged="updateBlockedBy" :team-id-by-player-id="this.teamIdByPlayerId"
                :label="'блок'" :selected-player="fieldGoalAttempt.blockedBy" :players="players"/>
  <SelectBoolean @selectedbooleanchanged="updateIsSuccessful"
                 :label="'попадание'" :selected-value="fieldGoalAttempt.isSuccessful"/>
  <Timer @selectedtimechanged="updateTime"
         :initial-time="Math.floor(fieldGoalAttempt.millisecondsSinceStart / 1000)"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>