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
    turnovers: []
  }
})
</script>

<script>
  export default {
    data() {
      return {
        eventsWithTypesOrderedByMillisSinceStart: [],
      }
    },

    mounted() {
      this.updateEventsWithTypesOrderedByMillisSinceStart();
    },

    methods: {
      updateEventsWithTypesOrderedByMillisSinceStart() {
        console.log(this.eventsWithTypesOrderedByMillisSinceStart);
        let eventLog = this.gameEventLog;
        this.eventsWithTypesOrderedByMillisSinceStart = this.gameEventLog.fieldGoalAttempts.map(ev => ({type: "field-goal-attempt", ev: ev}))
            .concat(eventLog.freeThrowAttempts.map(ev => ({type: "free-throw-attempt", ev: ev})))
            .sort((a, b) => a.ev.millisecondsSinceStart - b.ev.millisecondsSinceStart);
        console.log(this.eventsWithTypesOrderedByMillisSinceStart);
      },
    }
  }
</script>

<template>
  <div>Ивент лог</div>
  <div v-for="eventAndType in this.eventsWithTypesOrderedByMillisSinceStart">
    {{ this.eventsWithTypesOrderedByMillisSinceStart }}
  </div>
  <div v-for="eventAndType in this.eventsWithTypesOrderedByMillisSinceStart">
    <GameEvent :type="eventAndType.type" :ev="eventAndType.ev"/>
  </div>
</template>

<style scoped>

</style>
