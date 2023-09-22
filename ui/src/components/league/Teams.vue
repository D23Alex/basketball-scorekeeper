<script>
import axios from "axios";
import TeamShortInfo from "@/components/league/TeamShortInfo.vue";

export default {
  components: {TeamShortInfo},
  data() {
    return {
      teams: [],
    }
  },

  async mounted() {
    this.teams = (await axios.get("http://localhost:8080/api/teams")).data;
  }
}
</script>

<template>
<p>Команды лиги:</p>
  <li v-for="team in teams">
    <TeamShortInfo :team="team"/>
  </li>
</template>

<style scoped>

</style>