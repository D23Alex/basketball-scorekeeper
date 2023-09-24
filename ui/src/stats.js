// TODO: maybe move that to a different file
function lineupByTeam(team1, team2, lineupOccurrences) {
    return {
        team1: lineupOccurrences.filter((occurrence) => occurrence.team === team1),
        team2: lineupOccurrences.filter((occurrence) => occurrence.team === team2)
    };
}

import eq from "@/util";

const stats = {
    pointsInShots(fieldGoalAttempts, freeThrowAttempts) {
        const threes_made = fieldGoalAttempts.filter((fga) => fga.isSuccessful && fga.type === "THREE_POINTER").length;
        const twos_made = fieldGoalAttempts.filter((fga) => fga.isSuccessful && fga.type !== "THREE_POINTER").length;
        const free_throws_made = freeThrowAttempts.filter((fta) => fta.isSuccessful).length;
        return threes_made * 3 + twos_made * 2 + free_throws_made;
    },
    gameStats(player, gameEventLog) {
        return {
            player: player,
            points: this.pointsInShots(gameEventLog.fieldGoalAttempts.filter((fga) => eq.equalIds(fga.shooter, player)),
                gameEventLog.freeThrowAttempts.filter((fga) =>  eq.equalIds(fga.shooter, player))),
            assists: gameEventLog.fieldGoalAttempts.filter((fga) =>  eq.equalIds(fga.assistant, player)).length,
            rebounds: gameEventLog.fieldGoalAttempts.filter((fga) =>  eq.equalIds(fga.reboundedBy, player)).length,
        }
    }
}

export default stats;