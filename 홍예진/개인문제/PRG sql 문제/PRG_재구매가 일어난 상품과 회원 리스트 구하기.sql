Select User_id, product_id
from online_sale
Group by user_id, product_id
Having count(ONLINE_SALE_ID) >= 2
Order by USER_ID, PRODUCT_ID desc;
