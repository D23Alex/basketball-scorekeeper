<script setup>
defineProps({
  fieldGoalAttempt: {},
})
</script>

<script>
import PlayerPreview from "@/components/player/PlayerPreview.vue"
export default {
  components: {PlayerPreview},
}
</script>

<template>
  <div class="field-goal-attempt">
    <div class="players">
      <PlayerPreview :playerId="fieldGoalAttempt.shooter.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.shooter.firstName"
                     :lastName="fieldGoalAttempt.shooter.lastName"
                     :position="fieldGoalAttempt.shooter.position"
                     :teamId="1"
                     :label="'бросил'"/> <!-- //TODO: teamId and season are hardcoded atm -->
      <PlayerPreview v-if="fieldGoalAttempt.reboundedBy" :playerId="fieldGoalAttempt.reboundedBy.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.reboundedBy.firstName"
                     :lastName="fieldGoalAttempt.reboundedBy.lastName"
                     :position="fieldGoalAttempt.reboundedBy.position"
                     :teamId="1"
                     :label="'подобрал'"/>
      <PlayerPreview v-if="fieldGoalAttempt.assistant" :playerId="fieldGoalAttempt.assistant.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.assistant.firstName"
                     :lastName="fieldGoalAttempt.assistant.lastName"
                     :position="fieldGoalAttempt.assistant.position"
                     :teamId="1"
                     :label="'результативная передача'"/>
      <PlayerPreview v-if="fieldGoalAttempt.blockedBy" :playerId="fieldGoalAttempt.blockedBy.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.blockedBy.firstName"
                     :lastName="fieldGoalAttempt.blockedBy.lastName"
                     :position="fieldGoalAttempt.blockedBy.position"
                     :teamId="1"
                     :label="'блок'"/>
    </div>
    <div class="info">
      <div>{{ fieldGoalAttempt.type }}</div>
      <div>{{ fieldGoalAttempt.isSuccessful ? 'попадание' : 'промах' }}</div>
    </div>


  </div>
</template>

<style scoped>
.field-goal-attempt, .players {
  display: flex;
  flex-direction: row;
}

.info {
  display: flex;
  flex-direction: column;
}
</style>