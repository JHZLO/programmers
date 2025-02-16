/*
<문제>
- 자동차 종류가 '세단' 또는 'SUV' 인 자동차
- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능
- 30일간의 대여 금액이 50만원 이상 200만원 미만

<정렬>
- 대여 금액을 기준으로 내림차순 정렬
    - 대여 금액이 같은 경우 자동차 종류를 기준으로 오름차순 정렬
        - 자동차 종류까지 같은 경우 자동차 ID를 기준으로 내림차순 정렬
        
<출력>
자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 
*/
with CAR_DURATION AS (
    SELECT a.CAR_ID, a.CAR_TYPE, a.DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR a
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY b ON a.CAR_ID = b.CAR_ID
    WHERE a.CAR_TYPE IN ('세단', 'SUV')
    AND a.CAR_ID NOT IN (SELECT CAR_ID
          FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
          WHERE START_DATE <= '2022-11-30' AND
                END_DATE >='2022-11-01')
)

SELECT DISTINCT a.CAR_ID, a.CAR_TYPE, ROUND(a.DAILY_FEE * (1 - b.DISCOUNT_RATE / 100) * 30) AS FEE
FROM CAR_DURATION a
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN b on a.CAR_TYPE = b.CAR_TYPE
WHERE b.DURATION_TYPE = '30일 이상'
AND ROUND(a.DAILY_FEE * (1 - b.DISCOUNT_RATE / 100) * 30) BETWEEN 500000 AND 2000000 - 1
ORDER BY FEE DESC, a.CAR_TYPE ASC, a.CAR_ID DESC