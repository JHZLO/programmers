/*
7월 아이스크림 총 주문량과 
상반기의 아이스크림 총 주문량을 더한 값이 
큰 순서대로 상위 3개의 맛을 조회하는 SQL 문

같은 아이스크림에 대하여 서로 다른 두 공장에서 아이스크림 가게로 출하를 진행하는 경우가 있습니다. 
이 경우 같은 맛의 아이스크림이라도 다른 출하 번호를 갖게 됩니다.
*/

with TOP_FLAVOR AS (
    SELECT a.SHIPMENT_ID, a.FLAVOR, sum(a.TOTAL_ORDER) + b.TOTAL_ORDER AS TOTAL_ORDER
    FROM JULY a
    LEFT OUTER JOIN FIRST_HALF b ON a.SHIPMENT_ID = b.SHIPMENT_ID
    GROUP BY a.FLAVOR
    ORDER BY TOTAL_ORDER DESC
    LIMIT 3
)

SELECT FLAVOR FROM TOP_FLAVOR
