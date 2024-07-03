-- Create Team table
CREATE TABLE Team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create Player table
CREATE TABLE Player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES Team(id)
);

-- Create Match table
CREATE TABLE Match (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team1_id BIGINT,
    team2_id BIGINT,
    overs INT NOT NULL,
    team1Score INT DEFAULT 0,
    team1Wickets INT DEFAULT 0,
    team2Score INT DEFAULT 0,
    team2Wickets INT DEFAULT 0,
    FOREIGN KEY (team1_id) REFERENCES Team(id),
    FOREIGN KEY (team2_id) REFERENCES Team(id)
);

-- Create PlayerPerformance table
CREATE TABLE PlayerPerformance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_id BIGINT,
    match_id BIGINT,
    runsScored INT DEFAULT 0,
    ballsFaced INT DEFAULT 0,
    runsConceded INT DEFAULT 0,
    wicketsTaken INT DEFAULT 0,
    FOREIGN KEY (player_id) REFERENCES Player(id),
    FOREIGN KEY (match_id) REFERENCES Match(id)
);


    Team                          Player                    PlayerPerformance
    +-----------+                 +------------+            +-------------------+
    | id (PK)   |1------------N   | id (PK)    |1---------N | id (PK)           |
    | name      |                 | name       |            | player_id (FK)    |
    +-----------+                 | team_id (FK)|            | match_id (FK)     |
          1                        +------------+            | runsScored        |
          |                                                  | ballsFaced        |
          |                                                  | runsConceded      |
          |                                                  | wicketsTaken      |
          |                                                 +-------------------+
           |                                                       N
            |                                                      |
             |                                                     |
              |                                                   1
    Match      N                 
    +-------------+                                                
    | id (PK)     |                                             
    | team1_id (FK)|                                           
    | team2_id (FK)|                                             
    | overs       |                                             
    | team1Score  |                                             
    | team1Wickets|                                             
    | team2Score  |                                             
    | team2Wickets|                                             
    +-------------+                                              


