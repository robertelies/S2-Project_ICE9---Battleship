SELECT * from Player;

SELECT * FROM Player JOIN Game ON Game.Player = Player.Username ORDER BY Duration DESC;

SELECT Attacks_Made, COUNT(Game_ID) FROM Game WHERE Player = 'adrilumedina' GROUP BY Attacks_Made ORDER BY Attacks_Made; # gets the attacks made per game statistic
#SELECT Games_Won,Games_Played FROM Player WHERE  Username = 'adrilumedina'; # gets the games won / games played statistic

SELECT username, SUM(case when winner = 1 then 1 else 0 end),COUNT(Game_ID) FROM Game JOIN Player on Player.Username = Game.Player GROUP BY Player.Username;
#SELECT username, winner FROM Game JOIN Player on Player.Username = Game.Player ;