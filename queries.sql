--Part 1 -> List Job Table Columns & Data Types:
--id - INTEGER PRIMARY KEY
--employer - VARCHAR(255)
--name - VARCHAR(255)
--skills - VARCHAR(255)

--Part 2 -> Write query to list names of employers in St. Louis
SELECT name FROM employer
WHERE location = "St. Louis City";

--Part 3
DROP TABLE job;

--Part 4
