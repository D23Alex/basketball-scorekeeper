INSERT INTO arenas (name, address) VALUES ('СШОР Василеостровского р-на', 'пр-кт Малый, 66, лит. А, Санкт-Петербург');
INSERT INTO arenas (name, address) VALUES ('С/З "ГМУ им. Павлова"', 'ул. Льва Толстого, д. 6—8');
INSERT INTO arenas (name, address) VALUES ('КСК АРЕНА', 'Санк-Петербург, Футбольная аллея 6');
INSERT INTO arenas (name, address) VALUES ('Какая-то арена в Москве', 'Где-то в Москве');
INSERT INTO arenas (name, address) VALUES ('Какая-то арена в Нижнем Новгороде', 'Нижний Новгород');

INSERT INTO coaches (first_name, last_name, date_of_birth, description) VALUES ('Вася', 'Пупкин', '1981-12-23', 'Дамы и господа, это тренер!');
INSERT INTO coaches (first_name, last_name, date_of_birth, description) VALUES ('Петя', 'Покупкин', '1995-10-12', 'Это еще один тренер!');
INSERT INTO coaches (first_name, last_name, date_of_birth, description) VALUES ('Саша', 'Печкин', '1971-03-05', 'Почтальон?!');

INSERT INTO teams (name, city, home_arena_id) VALUES ('Зенит', 'Санкт-Петербург', 3);
INSERT INTO teams (name, city, home_arena_id) VALUES ('ЦСКА', 'Москва', 4);
INSERT INTO teams (name, city, home_arena_id) VALUES ('Пари НН', 'Пари НН', 5);

INSERT INTO players (first_name, last_name, date_of_birth, description, position) VALUES ('Сергей', 'Карасев', '1971-03-05', 'Описание игрока Сергей Карасев', 1);
INSERT INTO players (first_name, last_name, date_of_birth, description, position) VALUES ('Денис', 'Захаров', '1971-03-05', 'Описание игрока Денис Захаров', 1);
INSERT INTO players (first_name, last_name, date_of_birth, description, position) VALUES ('Игорь', 'Вольхин', '1971-03-05', 'Описание игрока Игорь Вольхин', 1);
-- этот игрок первого декабря перейдёт из цска в нижний новгород
INSERT INTO players (first_name, last_name, date_of_birth, description, position) VALUES ('Иван', 'Петренко', '1971-03-05', 'Описание игрока Иван Петренко', 1);
INSERT INTO players (first_name, last_name, date_of_birth, description, position) VALUES ('Михаил', 'Зубенко', '1971-03-05', 'Мафиозник (Шумиловский городок)', 1);


INSERT INTO player_contracts (player_id, team_id, valid_from, valid_to, jersey_number) VALUES (1, 1, '2023-09-01', '2025-09-01', 7);
INSERT INTO player_contracts (player_id, team_id, valid_from, valid_to, jersey_number) VALUES (2, 1, '2023-09-01', '2024-01-01', 3);
INSERT INTO player_contracts (player_id, team_id, valid_from, valid_to, jersey_number) VALUES (3, 1, '2023-09-01', '2026-09-01', 8);
-- игрок который перешёл из цска в нижний новгород 1 декабря
INSERT INTO player_contracts (player_id, team_id, valid_from, valid_to, jersey_number) VALUES (4, 2, '2022-09-01', '2023-11-30', 99);
INSERT INTO player_contracts (player_id, team_id, valid_from, valid_to, jersey_number) VALUES (4, 3, '2023-12-01', '2024-12-01', 99);


-- игра 25 ноября зенит против цска в спб
INSERT INTO games (arena_id, home_team_id, scheduled_start_time, team1_id, team2_id) VALUES (3, 1, '2023-11-25 19:00:00', 1, 2);

INSERT INTO period_starts (game_id, timestamp) VALUES (1, '2023-11-25 19:00:00');
INSERT INTO period_endings (game_id, timestamp) VALUES (1, '2023-11-25 19:20:00');
INSERT INTO period_starts (game_id, timestamp) VALUES (1, '2023-11-25 19:22:00');
INSERT INTO period_endings (game_id, timestamp) VALUES (1, '2023-11-25 19:45:00');
INSERT INTO period_starts (game_id, timestamp) VALUES (1, '2023-11-25 20:00:00');
INSERT INTO period_endings (game_id, timestamp) VALUES (1, '2023-11-25 20:20:00');
INSERT INTO period_starts (game_id, timestamp) VALUES (1, '2023-11-25 20:22:00');
INSERT INTO period_endings (game_id, timestamp) VALUES (1, '2023-11-25 20:40:00');

INSERT INTO lineup_occurrences (game_id, jersey_number, player_id, team_id) VALUES (1, 999, 1, 1);
INSERT INTO lineup_occurrences (game_id, jersey_number, player_id, team_id) VALUES (1, 999, 2, 1);
INSERT INTO lineup_occurrences (game_id, jersey_number, player_id, team_id) VALUES (1, 999, 3, 1);
INSERT INTO lineup_occurrences (game_id, jersey_number, player_id, team_id) VALUES (1, 999, 4, 2);

INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 1, 1, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 1, 2, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 1, 4, 2);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 2, 1, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 2, 2, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 2, 4, 2);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 3, 1, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 3, 2, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 3, 4, 2);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 4, 1, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 4, 2, 1);
INSERT INTO period_starters (game_id, period, player_id, team_id) VALUES (1, 4, 4, 2);

INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 1, 1, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 1, 2, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 1, 4, 2);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 2, 1, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 2, 2, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 2, 4, 2);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 3, 1, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 3, 2, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 3, 4, 2);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 4, 1, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 4, 2, 1);
INSERT INTO period_enders (game_id, period, player_id, team_id) VALUES (1, 4, 4, 2);


-- события игры 1
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 1, 1, 1000, 1);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 1, 1, 2000, 2);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 1, 1, 4000, 4);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 0, 1, 5000, 1);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 2, 1, 6000, 1);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 3, 1, 7000, 1);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, rebounded_by_id) VALUES (false, 1, 1, 8000, 1, 2);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, rebounded_by_id) VALUES (false, 1, 1, 8000, 1, 4);

-- фол на неуспешном броске
INSERT INTO personal_fouls (is_offensive, is_unsportsmanlike, fouled_player_id, fouling_player_id, game_id, milliseconds_since_start) VALUES (false, false, 1, 4, 1, 9000);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, foul_on_shooter_id) VALUES (false, 1, 1, 9000, 1, 1);
INSERT INTO free_throw_attempts (is_successful, game_id, milliseconds_since_start, shooter_id) VALUES (true, 1, 9000, 1);
INSERT INTO free_throw_attempts (is_successful, game_id, milliseconds_since_start, shooter_id) VALUES (false, 1, 9000, 1);
-- фол на успешном броске (and one)
INSERT INTO personal_fouls (is_offensive, is_unsportsmanlike, fouled_player_id, fouling_player_id, game_id, milliseconds_since_start) VALUES (false, false, 1, 4, 1, 10000);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, foul_on_shooter_id) VALUES (true, 1, 1, 10000, 1, 2);
INSERT INTO free_throw_attempts (is_successful, game_id, milliseconds_since_start, shooter_id) VALUES (true, 1, 10000, 1);
-- фол в поле в нападении
INSERT INTO personal_fouls (is_offensive, is_unsportsmanlike, fouled_player_id, fouling_player_id, game_id, milliseconds_since_start) VALUES (true, false, 1, 4, 1, 11000);

INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, blocked_by_id) VALUES (false, 1, 1, 12000, 1, 4);

-- Команда 1 берёт таймаут, игрок 3 выходит на поле, 2 садится
INSERT INTO substitution_calls (calling_team_id, game_id, milliseconds_since_start) VALUES (1, 1, 13000);
INSERT INTO substitution_outs (game_id, player_id, substitution_call_id) VALUES (1, 2, 1);
INSERT INTO substitution_ins (game_id, player_id, substitution_call_id) VALUES (1, 3, 1);

INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, rebounded_by_id) VALUES (false, 1, 1, 13000, 3, 1);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id) VALUES (true, 1, 1, 14000, 3);

-- командой 1 взят тайм-аут, игрок 3 сел, игрок 2 вышел
INSERT INTO timeouts (calling_team_id, game_id, milliseconds_since_start) VALUES (1, 1, 15000);
INSERT INTO after_timeout_court_exits (game_id, player_id, team_id, timeout_id) VALUES (1, 3, 1, 1);
INSERT INTO after_timeout_court_appearances (game_id, player_id, team_id, timeout_id) VALUES (1, 2, 1, 1);

INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, rebounded_by_id) VALUES (false, 1, 1, 16000, 2, 1);
INSERT INTO field_goal_attempts (is_successful, type, game_id, milliseconds_since_start, shooter_id, assistant_id) VALUES (true, 1, 1, 17000, 2, 1);
