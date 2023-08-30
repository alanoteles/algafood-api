insert into kitchen (id, name) values (1, 'Tai');
insert into kitchen (id, name) values (2, 'Indian');

insert into payment_method(id, name) values (1, 'Cash');
insert into payment_method(id, name) values (2, 'Card');

insert into estate(id, name) values(1, 'California');
insert into estate(id, name) values(2, 'Utah');

insert into city(id, name, estate_id) values(1, 'San Francisco', 1);
insert into city(id, name, estate_id) values(2, 'Los Angeles', 1);
insert into city(id, name, estate_id) values(3, 'Santa Monica', 1);
insert into city(id, name, estate_id) values(4, 'Salt Lake City', 2);

insert into restaurant (name, delivery_fee, kitchen_id, payment_method_id) values ('Thailand Food', 10.5, 1, 1);
insert into restaurant (name, delivery_fee, kitchen_id, payment_method_id) values ('Indian Food', 9.5, 1, 1);
insert into restaurant (name, delivery_fee, kitchen_id, payment_method_id) values ('Chinese Food', 5.5, 2, 2);

