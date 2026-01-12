
CREATE TABLE players (id INT PRIMARY KEY, name VARCHAR(100), role VARCHAR(20));
CREATE TABLE batting_stats (id INT PRIMARY KEY, player_id INT, match_id INT, runs INT, balls INT);
CREATE TABLE bowling_stats (id INT PRIMARY KEY, player_id INT, match_id INT, overs DOUBLE, runs_conceded INT, wickets INT);
