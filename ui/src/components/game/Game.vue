<script>
import axios from "axios";
import BoxScore from "@/components/game/BoxScore.vue";


export default {
  components: {BoxScore},
  data() {
    return {
      gameEvents: null
    }
  },

  async mounted() {
    this.gameEvents = (await axios.get("http://localhost:8080/api/events/game/" + this.$route.params.gameId)).data
  }
}
</script>

<template>
  <p>Вся информация о конкретной игре. Тут будут располагаться стартовые составы, box score, графики и т.п.</p>
  <p>{{ gameEvents }}</p>
  <BoxScore/>
</template>

<style scoped>

</style>