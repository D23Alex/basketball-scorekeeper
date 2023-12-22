<script>
import axios from "axios";
import TeamPreview from "@/components/league/TeamPreview.vue";

export default {
  components: {TeamPreview},
  data() {
    return {
      teams: [],
      season: 2023 // hardcoded for now
    }
  },

  async mounted() {
    this.teams = (await axios.get("http://localhost:8080/api/teams/get_all")).data;
  }
}
</script>

<template>
<p>Команды лиги:</p>
  <li v-for="team in teams">
    <TeamPreview :season="this.season" :team-id="team.id" :team-city="team.city" :team-name="team.name"/>
  </li>
</template>

<style scoped>

</style>