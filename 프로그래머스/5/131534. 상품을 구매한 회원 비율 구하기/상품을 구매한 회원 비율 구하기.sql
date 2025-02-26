/*
USER_INFO 테이블과 ONLINE_SALE 테이블에서 
2021년에 가입한 전체 회원들 중 
상품을 구매한 회원 수와 
상품을 구매한 회원의 비율(=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)을 
년, 월 별로 출력하는 SQL문을 작성

상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림

<정렬>
전체 결과는 년을 기준으로 오름차순 정렬
년이 같다면 월을 기준으로 오름차순 정렬해주세요.
*/
SELECT YEAR(SALES_DATE) AS YEAR, 
        MONTH(SALES_DATE) AS MONTH,
        COUNT(DISTINCT U.USER_ID) AS PURCHASED_USERS,
        ROUND(COUNT(DISTINCT U.USER_ID)/(SELECT COUNT(DISTINCT USER_ID) FROM USER_INFO WHERE YEAR(JOINED) = 2021), 1) AS PUCHASED_RATIO
    FROM USER_INFO U JOIN ONLINE_SALE S 
      ON U.USER_ID = S.USER_ID
    WHERE YEAR(JOINED) = 2021
    GROUP BY 1, 2
    ORDER BY 1, 2