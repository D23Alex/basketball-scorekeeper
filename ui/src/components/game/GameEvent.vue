<script setup>

import FieldGoalAttempt from "@/components/game/events/FieldGoalAttempt.vue";
import {prettyGameTimestampBySecondsSinceStart} from "@/util";

defineProps({
  type: String,
  ev: Object,
})
</script>

<template>
<div class="game-event">
  <div>Ивент</div>
  <div>{{ this.type }}</div>
  <div v-if="ev.millisecondsSinceStart">
    {{ prettyGameTimestampBySecondsSinceStart(ev.millisecondsSinceStart / 1000) }}
  </div>
  <template v-if="type === 'field-goal-attempt'">
    <FieldGoalAttempt :field-goal-attempt="ev"/>
  </template>
</div>
</template>

<style scoped>

</style>