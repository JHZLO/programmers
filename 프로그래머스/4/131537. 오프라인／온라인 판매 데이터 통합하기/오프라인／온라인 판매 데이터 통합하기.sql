SELECT DATE_FORMAT(N.SALES_DATE,'%Y-%m-%d') AS SALES_DATE , N.PRODUCT_ID, N.USER_ID, N.SALES_AMOUNT
FROM ONLINE_SALE AS N
LEFT JOIN OFFLINE_SALE AS F
ON N.PRODUCT_ID = F.PRODUCT_ID  AND N.SALES_DATE = F.SALES_DATE
WHERE DATE_FORMAT(N.SALES_DATE , '%Y-%m') = '2022-03'

UNION ALL

SELECT DATE_FORMAT(F.SALES_DATE,'%Y-%m-%d') AS SALES_DATE , F.PRODUCT_ID, NULL AS USER_ID, F.SALES_AMOUNT
FROM ONLINE_SALE AS N
RIGHT JOIN OFFLINE_SALE AS F
ON N.PRODUCT_ID = F.PRODUCT_ID   AND N.SALES_DATE = F.SALES_DATE
WHERE DATE_FORMAT(F.SALES_DATE , '%Y-%m') = '2022-03' 

ORDER BY SALES_DATE ASC, PRODUCT_ID ASC, USER_ID ASC;