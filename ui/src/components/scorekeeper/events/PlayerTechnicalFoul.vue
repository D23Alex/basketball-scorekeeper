<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";

defineProps({
  playerTechnicalFoul: {},
  players: Array,
  initialGameTimeInSeconds: Number,
})
</script>

<script>
import Timer from "@/components/scorekeeper/Timer.vue";

export default {
  components: {Timer},
  methods: {
    updateFoulingPlayer(player) {
      this.playerTechnicalFoul.foulingPlayer = player;
      this.$emit('playertechnicalfoulchanged', this.playerTechnicalFoul);
    },
    updateTime(timeInSeconds) {
      this.playerTechnicalFoul.millisecondsSinceStart = timeInSeconds * 1000;
      this.$emit('playertechnicalfoulchanged', this.playerTechnicalFoul);
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updateFoulingPlayer"
                :label="'сфолил'" :selected-player="playerTechnicalFoul.foulingPlayer" :players="players"/>
  <Timer @selectedtimechanged="updateTime"
         :initial-time="Math.floor(playerTechnicalFoul.millisecondsSinceStart / 1000)"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>