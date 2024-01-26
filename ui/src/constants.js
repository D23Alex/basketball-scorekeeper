export const TEAM_MAIN_COLOR_BY_ID = {
    1: "#add8e6",
    2: "red",
    3: "#487b71",
    4: "grey",
    5: "#d67315",
    6: "#4338ac",
};

export const IMAGES_PATH = 'images'

export const API_HOST = "158.160.137.233";
//export const API_HOST = "localhost";
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

export const PLAYER_POSITION_TRANSLATION = {
    "SHOOTING_GUARD": "атакующий защитник",
    "POINT_GUARD": "разыгрывающий защитник",
    "CENTER": "центровой",
    "SMALL_FORWARD": "легкий форвард",
    "POWER_FORWARD": "тяжелый форвард",
    "UNKNOWN": "неизвестно",
}

export const SHOT_TYPE_TRANSLATION = {
    "TWO_POINTER_JUMP_SHOT": "2 очка в прыжке",
    "TWO_POINTER_DRIVE": "2 очка в проходе",
    "TWO_POINTER_DUNK": "2 очка в сверху",
    "THREE_POINTER": "3 очка",
}

export const EVENT_NAME_BY_SLUG = {
    "field-goal-attempt": "бросок",
    "free-throw-attempt": "штрафной бросок",
    "player-technical-foul": "технический фол",
    "personal-foul": "нарушение",
    "player-ejection": "удаление игрока",
    "turnover": "потеря",
}

export const GAME_STATUS_TRANSLATION = {
    "UNKNOWN": "незвестно",
    "NOT_STARTED": "не начата",
    "FINISHED": "завершена",
    "IN_PROGRESS": "live",
}
