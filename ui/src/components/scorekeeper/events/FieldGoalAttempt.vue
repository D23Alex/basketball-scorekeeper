<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import SelectBoolean from "@/components/scorekeeper/event-editing/SelectBoolean.vue";
defineProps({
  fieldGoalAttempt: {},
  players: []
})
</script>

<script>
export default {
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
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updateShooter"
                :label="'бросил'" :selected-player="fieldGoalAttempt.shooter" :players="players"/>
  <SelectPlayer v-if="fieldGoalAttempt.assistant" @selectedplayerchanged="updateAssistant"
                :label="'результативная передача'" :selected-player="fieldGoalAttempt.assistant" :players="players"/>
  <SelectPlayer v-if="fieldGoalAttempt.reboundedBy" @selectedplayerchanged="updateReboundedBy"
                :label="'подобрал'" :selected-player="fieldGoalAttempt.reboundedBy" :players="players"/>
  <SelectPlayer v-if="fieldGoalAttempt.blockedBy" @selectedplayerchanged="updateBlockedBy"
                :label="'блок'" :selected-player="fieldGoalAttempt.blockedBy" :players="players"/>
  <SelectBoolean @selectedplayerchanged="updateIsSuccessful"
                 :label="'попадание'" :selected-value="fieldGoalAttempt.isSuccessful"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>