<script setup>
defineProps({
  label: String,
  players: Array,
  selectedPlayer: {}
})
</script>

<script>
import PlayerPreview from "@/components/scorekeeper/PlayerPreview.vue"
export default {
  components: {PlayerPreview},
  data() {
    return {
      selected: this.selectedPlayer,
    }
  },

  watch: {
    selected: function(val) {
      this.$emit('selectedplayerchanged', this.selected);
    }
  }
}
</script>

<template>
  <div class="select-player">
    <div>{{ label }}</div>
    <PlayerPreview v-if="selected !== null" :player-id="selected.id"
                   :firstName="selected.firstName"
                   :lastName="selected.lastName"
                   :teamId="1"
                   :jerseyNumber="1"/>
    <select v-model="selected" style="height: 50px;">
      <option v-for="player in players.concat(null)" v-bind:value="player">
        {{ player !== null ? player.firstName + " " + player.lastName : "никто" }}
      </option>
    </select>
  </div>
</template>

<style scoped>

</style>