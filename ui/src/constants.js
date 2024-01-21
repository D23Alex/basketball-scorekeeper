export const TEAM_MAIN_COLOR_BY_ID = {
    1: "blue",
    2: "red",
    3: "orange"
};

export const IMAGES_PATH = 'images'

// export const API_HOST = "158.160.137.233";
export const API_HOST = "localhost";
export const API_PORT = "8080";
export const API = "https://" + API_HOST + ":" + API_PORT + "/api";

export const RULES = {
    mainPeriodInSeconds: 600,
    overtimePeriodInSeconds: 300,
    mainTimeInSeconds: 2400,
    mainPeriods: 4
}

export const TURNOVER_CAUSE_TRANSLATION = {
    "OTHER": "другое",
    "STOLEN_BY_OPPONENT": "перехвачено",
    "FIVE_SECOND_VIOLATION": "правило 5 секунд",
    "EIGHT_SECOND_VIOLATION": "правило 8 секунд",
    "TWENTY_FOUR_SECOND_VIOLATION": "правило 24 секунд",
    "ZONE_VIOLATION": "нарушение правила зоны(!)",
    "OUT_OF_BOUNDS": "мяч вне площадки",
    "TRAVEL" : "пробежка",
    "LEG_PLAY": "игра ногой",
}

export const EJECTION_CAUSE_TRANSLATION = {
    "OTHER": "другое",
    "FOULS": "перебор замечаний",
    "UNSPORTSMANLIKE_BEHAVIOR": "неспортивное поведение"
}