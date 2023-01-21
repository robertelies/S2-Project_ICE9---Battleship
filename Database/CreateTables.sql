DROP TABLE IF EXISTS  Game;
DROP TABLE IF EXISTS Player;
CREATE TABLE Player (
    Username varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    email varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
    Password varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (Username),
    UNIQUE KEY `Name_UNIQUE` (Username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Game (
    Game_ID INTEGER AUTO_INCREMENT,
    Player varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL REFERENCES Player(Username),
    No_Opponents integer NOT NULL,
    Attacks_Made integer NOT NULL,
    Duration TIME NOT NULL,
    Winner BOOLEAN DEFAULT FALSE,
    Number_of_ships_remaining INTEGER NOT NULL,
    Game_Over BOOLEAN NOT NULL,
    Game_Path varchar(255) COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (Game_ID)
);

