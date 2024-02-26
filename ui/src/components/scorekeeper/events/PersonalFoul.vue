<script setup>
import SelectPlayer from "@/components/scorekeeper/event-editing/SelectPlayer.vue";
import SelectBoolean from "@/components/scorekeeper/event-editing/SelectBoolean.vue";
defineProps({
  personalFoul: {},
  players: Array,
  initialGameTimeInSeconds: Number,
  teamIdByPlayerId: {},
})
</script>

<script>
import Timer from "@/components/scorekeeper/Timer.vue";

export default {
  components: {Timer},
  methods: {
    updateFoulingPlayer(player) {
      this.personalFoul.foulingPlayer = player;
      this.$emit('personalfoulchanged', this.personalFoul);
    },
    updateFouledPlayer(player) {
      this.personalFoul.fouledPlayer = player;
      this.$emit('personalfoulchanged', this.personalFoul);
    },
    updateIsUnsportsmanlike(isUnsportsmanlike) {
      this.personalFoul.isUnsportsmanlike = isUnsportsmanlike;
      this.$emit('personalfoulchanged', this.personalFoul);
    },
    updateIsOffensive(isOffensive) {
      this.personalFoul.isOffensive = isOffensive;
      this.$emit('personalfoulchanged', this.personalFoul);
    },
    updateTime(timeInSeconds) {
      this.personalFoul.millisecondsSinceStart = timeInSeconds * 1000;
      this.$emit('personalfoulchanged', this.personalFoul);
    }
  },
}
</script>

<template>
<div class="field-goal-attempt">
  <SelectPlayer @selectedplayerchanged="updateFoulingPlayer" :team-id-by-player-id="teamIdByPlayerId"
                :label="'сфолил'" :selected-player="personalFoul.foulingPlayer" :players="players"/>
  <SelectPlayer @selectedplayerchanged="updateFouledPlayer" :team-id-by-player-id="teamIdByPlayerId"
                :label="'на ком сфолили'" :selected-player="personalFoul.fouledPlayer" :players="players"/>
  <SelectBoolean @selectedbooleanchanged="updateIsUnsportsmanlike"
                 :label="'неспортивный'" :selected-value="personalFoul.isUnsportsmanlike"/>
  <SelectBoolean @selectedbooleanchanged="updateIsOffensive"
                 :label="'в нападении'" :selected-value="personalFoul.isOffensive"/>
  <Timer @selectedtimechanged="updateTime"
         :initial-time="Math.floor(personalFoul.millisecondsSinceStart / 1000)"/>
</div>
</template>

<style scoped>
.field-goal-attempt {
  display: flex;
  flex-direction: row;
}
</style>