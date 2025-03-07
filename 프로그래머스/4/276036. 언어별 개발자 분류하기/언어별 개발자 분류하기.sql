-- 코드를 작성해주세요
WITH TBL AS (
    SELECT SUM(CODE) AS FRONT
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
), TBL2 AS (
    SELECT CASE
        WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python') > 0
        AND SKILL_CODE & (SELECT FRONT FROM TBL) > 0 THEN 'A'
        WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#') > 0 THEN 'B'
        WHEN SKILL_CODE & (SELECT FRONT FROM TBL) > 0 THEN 'C'
        END AS GRADE, ID, EMAIL
    FROM DEVELOPERS
)
SELECT * FROM TBL2
WHERE GRADE IS NOT NULL
ORDER BY GRADE, ID