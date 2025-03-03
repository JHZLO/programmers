WITH RECURSIVE TBL AS (
    SELECT
        0 AS HOUR    

    UNION ALL    

    SELECT HOUR + 1
    FROM TBL
    WHERE HOUR < 23
    )

SELECT 
    t.HOUR,
    COUNT(a.ANIMAL_ID) AS COUNT
FROM
    TBL t
    LEFT JOIN (SELECT
                ANIMAL_ID,
                HOUR(DATETIME) AS HOUR
               FROM
                ANIMAL_OUTS) a
    ON t.HOUR = a.HOUR
GROUP BY
    t.HOUR
ORDER BY
    t.HOUR ASC;