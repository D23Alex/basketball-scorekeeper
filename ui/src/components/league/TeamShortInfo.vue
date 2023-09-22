<script setup>
defineProps({
  team: Object
})
</script>

<template>
  <p>{{ team.name }}</p>
  <p>{{ team.city }}</p>
  <p>{{ team.homeArena.name }}</p>
</template>

<style scoped>

</style>