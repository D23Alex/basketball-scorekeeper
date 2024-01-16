<script setup>
import GameEvent from "@/components/game/GameEvent.vue";

defineProps({
  gameEventLog: {}
})
</script>

<script>
  export default {
    data() {
      return {
        eventsWithTypesOrderedByMillisSinceStart: [],
        allLoaded: false,
      }
    },

    mounted() {
      this.updateEventsWithTypesOrderedByMillisSinceStart();
      this.allLoaded = true;
    },

    methods: {
      updateEventsWithTypesOrderedByMillisSinceStart() {
        console.log(this.eventsWithTypesOrderedByMillisSinceStart);
        let eventLog = this.gameEventLog;
        //this.eventsWithTypesOrderedByMillisSinceStart = this.gameEventLog.fieldGoalAttempts.map(ev => ({type: "field-goal-attempt", ev: ev}))
            //.concat(eventLog.freeThrowAttempts.map(ev => ({type: "free-throw-attempt", ev: ev})))
            //.sort((a, b) => a.ev.millisecondsSinceStart - b.ev.millisecondsSinceStart);
        console.log(this.eventsWithTypesOrderedByMillisSinceStart);
      },
    }
  }
</script>

<template>
  <div>Ивент лог</div>
  <div v-for="eventAndType in eventsWithTypesOrderedByMillisSinceStart">
    1
  </div>
</template>

<style scoped>

</style>
