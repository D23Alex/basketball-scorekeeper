<script>
import axios from "axios";
import Header from "./Header.vue";
import TeamShortInfo from "@/components/TeamShortInfo.vue";

export default {
  components: {TeamShortInfo, Header},
  data() {
    return {
      teams: [],
    }
  },

  methods: {
    async loadAllTeams() {
      const result = await axios.get("http://localhost:8080/api/teams");
      if (200 <= result.status && result.status < 300)
        this.teams = result.data;
    }
  },

  mounted() {
    this.loadAllTeams();
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