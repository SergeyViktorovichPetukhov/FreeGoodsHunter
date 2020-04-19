
INSERT INTO share_statuses (id, name) VALUES (0, 'ACTIVE');
INSERT INTO share_statuses (id, name) VALUES (1, 'SOON');
INSERT INTO share_statuses (id, name) VALUES (2, 'COMPLETED');

INSERT INTO users (id, login, email, phone) VALUES (1, 'vasya pupkin','pupkin@mail.ru','123456789');
INSERT INTO users (id, login, email, phone) VALUES (2, 'petukhov sergey','ps@mail.ru','987654321');

INSERT INTO companies(id,login, address, phone, user_id) VALUES (1,'company owner','address XXX',7777777,3);

INSERT INTO users (id, login, email, phone, is_confirmed, company_id) VALUES (3, 'company owner','company_owner@mail.ru','7777777', TRUE, 1);


COMMIT;
