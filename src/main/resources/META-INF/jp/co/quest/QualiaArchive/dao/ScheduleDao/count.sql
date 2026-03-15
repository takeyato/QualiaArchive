select count(*)
from schedule	
where title like concat('%', /* keyword */'', '%')
