<script setup>
defineProps({
  label: String,
  options: {},
  selectedOption: {}
})
</script>

<script>
import PlayerPreview from "@/components/scorekeeper/PlayerPreview.vue"
export default {
  components: {PlayerPreview},
  data() {
    return {
      selected: this.selectedOption,
    }
  },

  watch: {
    selected: function(val) {
      this.$emit('selectedoptionchanged', this.selected[0]);
    }
  }
}
</script>

<template>
  <div class="select-from-enum">
    <div>{{ label }}</div>
    <select v-model="selected" style="height: 50px;">
      <option v-for="option in Object.entries(options)" v-bind:value="option">
        {{ option[1] }}
      </option>
    </select>
  </div>
</template>

<style scoped>

</style>