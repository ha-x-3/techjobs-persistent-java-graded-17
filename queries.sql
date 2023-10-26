--Part 1 -> List Job Table Columns & Data Types:
--id - INTEGER PRIMARY KEY
--employer - VARCHAR(255)
--name - VARCHAR(255)
--skills - VARCHAR(255)

--Part 2 -> Write query to list names of employers in St. Louis
SELECT name FROM employer
WHERE location = "St. Louis City";

--Part 3 -> Write query to drop job table
DROP TABLE job;

--Part 4 -> Write query to return names of all skills in alphabetical order
SELECT * FROM skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;
