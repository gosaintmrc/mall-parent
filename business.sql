-- 根据分类的父id查询所有的子节点
SELECT * FROM tb_category WHERE parent_id=0;

SELECT * FROM tb_category WHERE parent_id=558;
-- 查询父id=1的所有商品分类的id
SELECT id FROM tb_category WHERE parent_id=558;

SELECT * FROM tb_brand WHERE id in(SELECT brand_id FROM tb_category_brand WHERE category_id=558);
-- 根据商品id查询品牌信息
SELECT brand.* FROM tb_brand brand,tb_category_brand tcb WHERE brand.id=tcb.brand_id AND tcb.category_id=558