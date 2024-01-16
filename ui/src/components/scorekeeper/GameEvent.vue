<script setup>
import FieldGoalAttempt from "@/components/scorekeeper/events/FieldGoalAttempt.vue";

defineProps({
  unsavedByDefault: Boolean,
  type: String,
  ev: Object,
  players: Array,
  initialGameTimeInSeconds: Number,
})
</script>

<script>
import axios from "axios";
import {API} from "@/constants";

export default {
  data() {
    return {
      neverChanged: true,
      currentEvent: this.ev,
      color: 'green',
      hasUnsavedChanges: this.unsavedByDefault !== false && this.unsavedByDefault !== null
          && this.unsavedByDefault !== undefined,
      deleted: false
    }
  },
  methods: {
    setColor() {
      // есть несохранённые изменения - жёлтый, есть сохранённые изменения - синий,
      // не было изменений после загрузки из базы - зелёный. //TODO: цвета
      this.color = this.hasUnsavedChanges ? "yellow" : (this.neverChanged ? "green" : "blue");
    },

    async deleteEvent() {
      if (!this.hasUnsavedChanges)
        await axios.delete(API + "/events/" + this.type + "/" + this.ev.id);
      this.deleted = true;
    },

    async saveEvent() {
      this.ev.id = (await axios.post(API + "/events/" + this.type, this.ev)).data.id;
      this.hasUnsavedChanges = false;
      this.setColor();
    },

    updateEventLocally(newEv) {
      this.neverChanged = false;
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
    <div class="clickable" @click="this.deleteEvent()">удалить</div>
    <div class="clickable" v-if="hasUnsavedChanges" @click="this.saveEvent()">сохранить</div>
    <template v-if="type === 'field-goal-attempt'">
      <FieldGoalAttempt @fieldgoalattemptchanged="updateEventLocally"
                        :field-goal-attempt="ev"
                        :players="players" :initial-game-time-in-seconds="this.initialGameTimeInSeconds"/>
    </template>
  </div>
</template>

<style scoped>
.game-event {
  border-style: solid;
  border-width: 5px;
  border-color: v-bind(color)
}

.game-event:hover {
  background-color:rgba(0, 0, 0, 0.2);
}

.clickable {
  cursor: pointer;
}
</style>