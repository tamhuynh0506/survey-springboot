INSERT INTO app_user (id, name, email, password, role)
VALUES (
  gen_random_uuid(),
  'admin',
  'admin@gmail.com',
  '$2a$12$satWdXzhlrSl/E6HOK55e.ev8KHOXT.RxvY7/atK3eBynV7Hu9gi6',
  'ADMIN'
);
