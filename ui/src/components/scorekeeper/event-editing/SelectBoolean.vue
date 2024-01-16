<script setup>
defineProps({
  label: String,
  selectedValue: Boolean,
})
</script>

<script>
export default {
  data() {
    return {
      selected: this.selectedValue
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
<div class="select-boolean">
  <div>{{ label }}</div>
  <div>
    <input type="checkbox" id="checkbox" v-model="selected">
    <label for="checkbox">{{ selected }}</label>
  </div>
</div>
</template>

<style scoped>
.select-boolean {
  display: flex;
  flex-direction: column;
}
</style>