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
    <PlayerPreview :player-id="this.selected.id"
                   :firstName="this.selected.firstName"
                   :lastName="this.selected.lastName"
                   :teamId="1"
                   :jerseyNumber="1"/>
    <select v-model="selected" style="height: 50px;">
      <option v-for="player in players" v-bind:value="player">
        {{ player.firstName + " " + player.lastName }}
      </option>
    </select>
  </div>
</template>

<style scoped>

</style>