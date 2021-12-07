CREATE TABLE IF NOT EXISTS products (
id serial PRIMARY KEY,
name VARCHAR(200),
qty INTEGER(100),
created_at TIMESTAMP,
updated_at TIMESTAMP
);