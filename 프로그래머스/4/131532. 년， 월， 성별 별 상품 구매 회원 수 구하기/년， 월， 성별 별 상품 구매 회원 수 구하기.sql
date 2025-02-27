/*
년, 월, 성별 별로 상품을 구매한 회원수를 집계하는 SQL문을 작성

<정렬>
결과는 년, 월, 성별을 기준으로 오름차순 정렬 
이때, 성별 정보가 없는 경우 결과에서 제외
*/

WITH BUYER AS (
    SELECT a.USER_ID, a.GENDER, b.SALES_DATE
    FROM USER_INFO a
    JOIN ONLINE_SALE b ON a.USER_ID = b.USER_ID and a.GENDER IS NOT NULL
)
SELECT YEAR(SALES_DATE) AS YEAR, MONTH(SALES_DATE) AS MONTH, GENDER, COUNT(DISTINCT USER_ID) AS USERS
FROM BUYER
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR ASC, MONTH ASC, GENDER ASC