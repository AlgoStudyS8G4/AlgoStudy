SELECT BOOK_ID, AUTHOR_NAME, DATE_FORMAT(PUBLISHED_DATE, '%y
                                         -%M-%D') AS PUBLISHED_DATE
FROM (
    SELECT BOOK_ID, AUTHOR_ID, PUBLISHED_DATE
    FROM BOOK
    WHERE CATEGORY = '경제'
) B JOIN AUTHOR A ON B.AUTHOR_ID = A.AUTHOR_ID
ORDER BY PUBLISHED_DATE; 
