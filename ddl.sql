-- BookAvailableStockInfo --
select bi.b_title, bi.isbn, bc.c_name, bi.b_publisher, bi.b_pdate, bi.b_author, count(bi.b_title) as available_stock
from book b, book_info bi, book_category bc
where b.isbn=bi.isbn and bi.c_id=bc.c_id
group by bi.isbn;