// TODO: maybe move that to a different file
import {all} from "axios";

function lineupByTeam(team1, team2, lineupOccurrences) {
    return {
        team1: lineupOccurrences.filter((occurrence) => occurrence.team === team1),
        team2: lineupOccurrences.filter((occurrence) => occurrence.team === team2)
    };
}

import eq from "@/util";

const stats = {


    gameStats(player, gameEventLog) {
        return {
            player: player,

            points: pointsInShots(player, gameEventLog),

            assists: assists(player, gameEventLog),

            rebounds: rebounds(player, gameEventLog),

            timePlayed: timePlayedInMillis(player, gameEventLog),

            twoPointersAttempted: twoPointAttempts(player, gameEventLog).length,
            threePointersAttempted: threePointAttempts(player, gameEventLog).length,
            freeThrowsAttempted: freeThrowAttempts(player, gameEventLog).length,

            twoPointersMade: twoPointersMade(player, gameEventLog),
            threePointersMade: threePointersMade(player, gameEventLog),
            freeThrowsMade: freeThrowsMade(player, gameEventLog)

        }
    },
}

function lastEventTimestamp(gameEventLog) {

    console.log( [{millisecondsSinceStart: 0}].concat(
        gameEventLog.coachEjections.slice(-1),
        gameEventLog.coachTechnicalFouls.slice(-1),
        gameEventLog.fieldGoalAttempts.slice(-1),
        gameEventLog.freeThrowAttempts.slice(-1),
        gameEventLog.personalFouls.slice(-1),
        gameEventLog.playerEjections.slice(-1),
        gameEventLog.substitutionCalls.slice(-1),
        gameEventLog.timeouts.slice(-1),
        gameEventLog.turnovers.slice(-1)).map(event => event.millisecondsSinceStart));

    return Math.max(...[{millisecondsSinceStart: 0}].concat(
        gameEventLog.coachEjections.slice(-1),
        gameEventLog.coachTechnicalFouls.slice(-1),
        gameEventLog.fieldGoalAttempts.slice(-1),
        gameEventLog.freeThrowAttempts.slice(-1),
        gameEventLog.personalFouls.slice(-1),
        gameEventLog.playerEjections.slice(-1),
        gameEventLog.substitutionCalls.slice(-1),
        gameEventLog.timeouts.slice(-1),
        gameEventLog.turnovers.slice(-1)).map(event => event.millisecondsSinceStart));
}

function timePlayedInMillis(player, gameEventLog) {
    let courtEnterTimestamps = gameEventLog.substitutionIns.filter(sIn => eq.equalIds(sIn.player, player))
        .map(sIn => sIn.substitutionCall.millisecondsSinceStart);
    let courtExitTimestamps = gameEventLog.substitutionOuts.filter(sOut => eq.equalIds(sOut.player, player))
        .map(sOut => sOut.substitutionCall.millisecondsSinceStart);

    let isStarter = gameEventLog.startingLineupOccurrences.filter(occurrence => eq.equalIds(occurrence.player, player)).length > 0;
    if (isStarter)
        courtEnterTimestamps = [0].concat(courtEnterTimestamps);

    let stillOnCourt = courtEnterTimestamps.length > courtExitTimestamps.length;
    if (stillOnCourt)
        courtExitTimestamps.push(gameEventLog.gameEnding !== null ? 40 * 60 * 1000 : lastEventTimestamp(gameEventLog));
    // TODO: учитывать овертаймы

    return courtExitTimestamps.reduce((a, b) => a + b, 0) - courtEnterTimestamps.reduce((a, b) => a + b, 0);
}

function fieldGoalAttempts(player, gameEventLog) {
    return gameEventLog.fieldGoalAttempts.filter(sga => eq.equalIds(sga.shooter, player));
}

function twoPointAttempts(player, gameEventLog) {
    return fieldGoalAttempts(player, gameEventLog).filter(fga => fga.type !== "THREE_POINTER");
}

function threePointAttempts(player, gameEventLog) {
    return fieldGoalAttempts(player, gameEventLog).filter(fga => fga.type === "THREE_POINTER");
}

function freeThrowAttempts(player, gameEventLog) {
    return gameEventLog.freeThrowAttempts.filter(fta => eq.equalIds(fta.shooter, player));
}

function twoPointersMade(player, gameEventLog) {
    return twoPointAttempts(player, gameEventLog).filter(attempt => attempt.isSuccessful).length;
}

function threePointersMade(player, gameEventLog) {
    return threePointAttempts(player, gameEventLog).filter(attempt => attempt.isSuccessful).length;
}

function freeThrowsMade(player, gameEventLog) {
    return freeThrowAttempts(player, gameEventLog).filter(attempt => attempt.isSuccessful).length;
}

function pointsInShots(player, gameEventLog) {
    return twoPointersMade(player, gameEventLog) * 2
        + threePointersMade(player, gameEventLog) * 3
        + freeThrowAttempts(player, gameEventLog).length;
}

function assists(player, gameEventLog) {
    return gameEventLog.fieldGoalAttempts.filter((fga) =>  eq.equalIds(fga.assistant, player)).length;
}

function rebounds(player, gameEventLog) {
    return gameEventLog.fieldGoalAttempts.filter((fga) =>  eq.equalIds(fga.reboundedBy, player)).length;
}



export default stats;