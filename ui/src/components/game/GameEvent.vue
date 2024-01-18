<script setup>
import FieldGoalAttempt from "@/components/game/events/FieldGoalAttempt.vue";
import { prettyGameTimestampBySecondsSinceStart } from "@/util";

defineProps({
  type: String,
  ev: Object,
});
</script>

<template>
  <div class="game-event">
    <div class="event-type">{{ this.type }}</div>
    <div v-if="ev.millisecondsSinceStart" class="event-timestamp">
      {{ prettyGameTimestampBySecondsSinceStart(ev.millisecondsSinceStart / 1000) }}
    </div>
    <template v-if="type === 'field-goal-attempt'">
      <div class="field-goal-attempt-wrapper">
        <FieldGoalAttempt :field-goal-attempt="ev" />
      </div>
    </template>
  </div>
</template>

<style scoped>
.game-event {
  text-align: center;
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: center; /* Центрирование по горизонтали */
}

.event-type {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.event-timestamp {
  font-size: 14px;
  color: #555;
}

.field-goal-attempt-wrapper {
  display: flex;
  justify-content: center; /* Центрирование по горизонтали */
  align-items: center; /* Центрирование по вертикали */
  width: 100%; /* Ширина 100% родительского элемента */
}
</style>