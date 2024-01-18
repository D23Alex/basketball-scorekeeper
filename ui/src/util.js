import {RULES} from "@/constants";

export function secondsInPeriods(periods) {
    if (periods <= RULES.mainPeriods)
        return RULES.mainPeriodInSeconds * periods;
    return RULES.mainTimeInSeconds + RULES.overtimePeriodInSeconds * (periods - RULES.mainPeriods);
}

export function periodAsText(period) {
    if (period <= RULES.mainPeriods)
        return period + ' четверть';
    return period - RULES.mainPeriods + 'ОТ';
}

export function periodsInSeconds(seconds) {
    let beforeOverTime = seconds < RULES.mainTimeInSeconds;
    if (beforeOverTime)
        return 1 + Math.floor(seconds / RULES.mainPeriodInSeconds);
    return RULES.mainPeriods + 1
        + Math.floor((seconds - RULES.mainTimeInSeconds) / RULES.overtimePeriodInSeconds);
}

export function prettyGameTimestampBySecondsSinceStart(timeInSeconds) {
    let m = Math.floor((timeInSeconds - secondsInPeriods(periodsInSeconds(timeInSeconds) - 1)) / 60);
    let s = timeInSeconds % 60;
    return periodAsText(periodsInSeconds(timeInSeconds)) + ' 0' + m + ':' + (s >= 10 ? '' : '0') + s;
}

export function timePlayedInMinutesAndSeconds(secondsPlayed) {
    let m = Math.floor(secondsPlayed / 60);
    let s = secondsPlayed % 60;
    return (m >= 10 ? "" : "0") + m + ":" + (s >= 10 ? "" : "0") + s;
}