select *
from schedule
where title like concat('%', /* keyword */'', '%')
order by id
limit /* limit */0
offset /* offset */0
