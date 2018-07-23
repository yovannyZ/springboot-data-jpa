INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(1, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(2, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(3, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(4, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(5, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(6, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(7, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(8, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(9, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(10, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(11, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(12, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(13, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(14, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(15, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(16, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(17, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(18, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(19, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(20, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(21, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(22, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(23, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(24, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(25, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(26, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(27, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(28, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(29, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(30, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(31, 'Yovanny', 'Zeballos', 'zeballos.yovanny@gmail.com', '1989-05-08','');
INSERT INTO client(id, name, lastname, email, create_at, photo) VALUES(32, 'Luis', 'Zeballos', 'zeballos.luis@gmail.com', '1989-05-08','');


INSERT INTO product(name, price, create_at) VALUES('Monitor Samsumg 42', 850.00, NOW());
INSERT INTO product(name, price, create_at) VALUES('Mouse Dell', 17.00, NOW());
INSERT INTO product(name, price, create_at) VALUES('Laptop Dell 15', 1200.00, NOW());
INSERT INTO product(name, price, create_at) VALUES('Teclado Logitec', 23.50, NOW());
INSERT INTO product(name, price, create_at) VALUES('Celular ZTE', 320, NOW());

INSERT INTO invoice(description, observation, client_id, create_at) VALUES ('Factura 1', null, 1, NOW());
INSERT INTO invoice_line(qty, invoice_id, product_id) VALUES(1,1,1)
INSERT INTO invoice_line(qty, invoice_id, product_id) VALUES(5,1,2)
INSERT INTO invoice_line(qty, invoice_id, product_id) VALUES(2,1,3)
INSERT INTO invoice_line(qty, invoice_id, product_id) VALUES(3,1,4)

INSERT INTO invoice(description, observation, client_id, create_at) VALUES ('Factura 2', 'Observación', 2, NOW());
INSERT INTO invoice_line(qty, invoice_id, product_id) VALUES(1,2,1)
INSERT INTO invoice_line(qty, invoice_id, product_id) VALUES(10,2,5)




