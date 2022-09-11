insert into carts
(user_id, fname, lname, 
 product_id, category_id, manufacturer_id, product, quantity, price_per_item, 
 category, manufacturer)
 with
 t1 as(
select user_id, fname, lname from users where user_id = 1
),
 t2 as( select 
inventories.product_id,
inventories.category_id, 
manufacturers.manufacturer_id,
products.product,
inventories.quantity,
products.price,
categories.category, 
manufacturers.manufacturer
from inventories 
inner join Categories 
on categories.category_id = inventories.category_id 
inner join products 
on inventories.product_id = products.product_id 
inner join manufacturers 
on manufacturers.manufacturer_id = products.manufacturer_id
where inventories.category_id = 1
and  inventories.product_id = 1 )
select *
from t1,t2;

select * from carts;