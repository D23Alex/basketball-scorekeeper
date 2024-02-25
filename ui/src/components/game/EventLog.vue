<script setup>
import GameEvent from "@/components/game/GameEvent.vue";

defineProps({
  gameEventLog: {
    periodStarts: [],
    periodEndings: [],
    lineupOccurrences: [],
    periodStarters: [],
    periodEnders: [],
    afterTimeoutCourtAppearances: [],
    afterTimeoutCourtExits: [],
    coachEjections: [],
    coachTechnicalFouls: [],
    fieldGoalAttempts: [],
    freeThrowAttempts: [],
    personalFouls: [],
    playerEjections: [],
    playerTechnicalFouls: [],
    substitutionCalls: [],
    substitutionIns: [],
    substitutionOuts: [],
    timeouts: [],
    turnovers: [],
  },
});
</script>

<script>
export default {
  data() {
    return {
      eventsWithTypesOrderedByMillisSinceStart: [],
    };
  },

  mounted() {
    this.updateEventsWithTypesOrderedByMillisSinceStart();
  },

  methods: {
    updateEventsWithTypesOrderedByMillisSinceStart() {
      this.eventsWithTypesOrderedByMillisSinceStart =
        this.gameEventLog.fieldGoalAttempts
          .map((ev) => ({ type: "field-goal-attempt", ev: ev }))
          .concat(
            this.gameEventLog.freeThrowAttempts.map((ev) => ({
              type: "free-throw-attempt",
              ev: ev,
            }))
          )
          .sort(
            (a, b) => a.ev.millisecondsSinceStart - b.ev.millisecondsSinceStart
          );
    },
  },
};
</script>

<template>
  <div v-for="eventAndType in eventsWithTypesOrderedByMillisSinceStart">
    <GameEvent :type="eventAndType.type" :ev="eventAndType.ev" />
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px;
}

.event-list {
  display: grid;
  gap: 20px;
}

/* GameEvent Component Styling */
.game-event {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.game-event img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 10px;
}

.game-event-info {
  display: flex;
  flex-direction: column;
}

.game-event-info div {
  margin-bottom: 8px;
}

body {
  font-family: "Arial", sans-serif;
  background-color: #f0f0f0;
}
</style>
