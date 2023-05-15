INSERT INTO company (id, uuid, address, tva)
VALUES (1, '85108c1e-c8b3-43c9-859c-1ba73f132d49', 'Paris', '20%');
INSERT INTO company (id, uuid, address, tva)
VALUES (2, 'cc4514c0-298b-44d9-b208-d70da05f7c11', 'Rennes', '10%');
INSERT INTO contact (id, uuid, first_name, last_name, address, tva, statu)
VALUES (1, 'fb3f586b-7281-44d7-9bb4-5c341a3d4503', 'Mehdi', 'BENBLIDIA', 'Paris', '9%', 'FREELANCE');
INSERT INTO contact (id, uuid, first_name, last_name, address, tva, statu)
VALUES (2, '2649cf5c-6c9f-4034-8ff0-ac3c5b89ec82', 'Ghilas', 'OUAREZ', 'Paris', '8%', 'EMPLOYEE');
INSERT INTO company_contact (company_id, contact_id)
VALUES (1, 1);