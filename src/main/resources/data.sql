
INSERT INTO share_statuses (id, name) VALUES (0, 'ACTIVE');
INSERT INTO share_statuses (id, name) VALUES (1, 'SOON');
INSERT INTO share_statuses (id, name) VALUES (2, 'COMPLETED');

INSERT INTO users (id, login, email, phone) VALUES (1, 'vasya pupkin','pupkin@mail.ru','123456789');
INSERT INTO users (id, login, email, phone) VALUES (2, 'sergeyp3d@rambler.ru','ps@mail.ru','987654321');
INSERT INTO users (id, login, email, phone, has_company, company_id) VALUES (3, 'company owner','company_owner@mail.ru','7777777', TRUE, 1);

INSERT INTO companies(id,login, address, phone, user_id) VALUES (1,'company owner','address XXX',7777777,3);

INSERT INTO shares(id,share_id,company_id,login,product_photo_url,product_name,product_description,count_of_product, link_on_product ,
                   product_price, announcement_duration, share_duration, after_share_duration,
                   color, picked_items_count, all_items_count, code,date, creation_status,place_country, place_region, place_city)
                   VALUES (1, 'company_owner@mail.ru 2020-04-20 #1', 1,'company owner',121,'NAMEEEE','descr',2,'link/link',
                           25,23,56,45,2,45,57,'3Fd6ht','2020-04-20 10:45:28.437','CREATED','USA','California','Los Angeles');

INSERT INTO items(id,lon,lat, share_id) VALUES (1,43,25,1);
INSERT INTO items(id,lon,lat, share_id) VALUES (2,34,32,1);

-- INSERT INTO users_shares VALUES (1,1);

COMMIT;
