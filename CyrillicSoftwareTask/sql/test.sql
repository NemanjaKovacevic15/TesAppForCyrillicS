-- run after the project starts (passwords are the same as user names)

INSERT INTO customer(id, name) values(1, 'Dusan');
INSERT INTO customer(id, name) values(2, 'Nemanja');

INSERT INTO user(id, name, password, authority) values(1, 'dusan', '$2y$12$nxXyDxlivp6aHNPmz/muB.7aZ351bz3eIRwA4HBkEciNNnoXVpucC', 'user');
INSERT INTO user(id, name, password, authority) values(2, 'nikola', '$2y$12$JQZtaEj.MBiJzYSBOJR8COFDnmg2lOOtnbCU1sBo2GYKjrNFrM25S', 'user');
INSERT INTO user(id, name, password, authority) values(3, 'nemanja', '$2y$12$bFsh1GtSkorQexhGpVQ32Ogl58/bOOnEcLtEWb9y80LAqZPNU0oM2', 'user');

INSERT INTO account(id, name, customer_id, user_id) values(1, 'account1', 1, 1);
INSERT INTO account(id, name, customer_id, user_id) values(2, 'account2', 1, 2);
INSERT INTO account(id, name, customer_id, user_id) values(3, 'account3', 2, 3);
INSERT INTO account(id, name, customer_id, user_id) values(4, 'account4', 2, 4);
INSERT INTO account(id, name, customer_id, user_id) values(5, 'account5', 2, 4);

INSERT INTO farm(id, name, account_id) values(1, 'farm1', 1);
INSERT INTO farm(id, name, account_id) values(2, 'farm2', 2);
INSERT INTO farm(id, name, account_id) values(3, 'farm3', 3);
INSERT INTO farm(id, name, account_id) values(4, 'farm4', 4);
INSERT INTO farm(id, name, account_id) values(5, 'farm5', 5);