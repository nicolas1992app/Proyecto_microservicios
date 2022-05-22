INSERT INTO TBL_CATEGORIES (ID, NAME)VALUES (1, 'shoes');
INSERT INTO TBL_CATEGORIES (ID, NAME)VALUES (2, 'books');
INSERT INTO TBL_CATEGORIES (ID, NAME)VALUES (3, 'electronics');

INSERT INTO TBL_PRODUCTS (ID, NAME, DESCRIPTION, STOCK, PRICE, STATUS, CREATE_AT, CATEGORY_ID)
VALUES (1, 'Spring Boot in Action','under armour Men '' Craig Walls is a software developer at Pivotal and is the author of Spring in Action',12,40.06,'CREATED','2018-09-05',1);

INSERT INTO TBL_PRODUCTS (ID, NAME, DESCRIPTION, STOCK, PRICE, STATUS, CREATE_AT, CATEGORY_ID)
VALUES (2, 'under armour Men ''s Micro G Assert – 7','under armour Men ''Lightweight mesh upper delivers complete breathability . Durable leather overlays for stability ',4,12.5,'CREATED','2018-09-05',1);


INSERT INTO TBL_PRODUCTS (ID, NAME, DESCRIPTION, STOCK, PRICE, STATUS, CREATE_AT, CATEGORY_ID)
VALUES (3, 'Spring Boot in Action','under armour Men '' Craig Walls is a software developer at Pivotal and is the author of Spring in Action',12,40.06,'CREATED','2018-09-05',2);

--DELETE FROM TBL_PRODUCTS;

--DELETE FROM TBL_CATEGORIES;
