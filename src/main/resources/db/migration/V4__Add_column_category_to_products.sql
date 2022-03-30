ALTER TABLE products ADD COLUMN category_id bigint;

ALTER TABLE products
 ADD CONSTRAINT fk_category
 FOREIGN KEY (category_id)
 REFERENCES categories(id);