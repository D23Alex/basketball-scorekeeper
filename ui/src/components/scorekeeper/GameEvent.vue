<script setup>
import FieldGoalAttempt from "@/components/scorekeeper/events/FieldGoalAttempt.vue";

defineProps({
  unsavedByDefault: Boolean,
  type: String,
  ev: Object,
  players: [],
})
</script>

<script>
import axios from "axios";
import {API} from "@/constants";

export default {
  data() {
    return {
      currentEvent: this.ev,
      color: 'green',
      hasUnsavedChanges: this.unsavedByDefault !== false && this.unsavedByDefault !== null
          && this.unsavedByDefault !== undefined,
      deleted: false
    }
  },
  methods: {
    setColor() {
      this.color = this.hasUnsavedChanges ? "yellow" : "green";
    },

    async deleteEvent() {
      if (!this.hasUnsavedChanges)
        await axios.delete(API + "/events/" + this.type + "/" + this.ev.id);
      this.deleted = true;
    },

    async saveEvent() {
      await axios.post(API + "/events/" + this.type, this.ev);
      this.hasUnsavedChanges = false;
      this.setColor();
    },

    updateEventLocally(newEv) {
      this.currentEvent = newEv;
      this.hasUnsavedChanges = true;
      this.setColor();
    },
  },

  mounted() {
    this.setColor();
  }
}
</script>

<template>
  <div v-if="!this.deleted" class="game-event">
    <div>Ивент</div>
    <div>{{ this.type }}</div>
    <div @click="this.deleteEvent()">удалить</div>
    <div v-if="hasUnsavedChanges" @click="this.saveEvent()">сохранить</div>
    <template v-if="type === 'field-goal-attempt'">
      <FieldGoalAttempt @fieldgoalattemptchanged="updateEventLocally" :field-goal-attempt="ev" :players="players"/>
    </template>
  </div>
</template>

<style scoped>
.game-event {
  border-style: solid;
  border-width: 5px;
  border-color: v-bind(color)
}
</style>