--
--  INSERT INTO share_statuses (id, name) VALUES (0, 'ACTIVE');
--  INSERT INTO share_statuses (id, name) VALUES (1, 'SOON');
--  INSERT INTO share_statuses (id, name) VALUES (2, 'COMPLETED');
--
--  INSERT INTO companies(id,login,name, placeNameOrUrl, contact, user_id) VALUES (1,'company owner','roga i kopita','placeNameOrUrl XXX',7777777,3);
-- --
--  INSERT INTO users (id, login, contact) VALUES (1,'market@magnetball.net','market@magnetball.net');
--  INSERT INTO users (id, login, contact) VALUES (2, 'sergeyp3d@rambler.ru','+7 495 333-91-44');
--  INSERT INTO users (id, login, contact, has_company) VALUES (3,'company_owner@mail.ru','7777777', TRUE);
-- --
-- --
--  INSERT INTO shares(id,share_id,company_id,login,product_photo_url,product_name,product_description,product_count, link_on_product ,
--                     product_price, announcement_duration, share_duration, after_share_duration,
--                     color, picked_items_count, all_items_count, code,date, creation_status,place_country, place_region, place_city)
--             VALUES (1, 'company_owner@mail.ru 2020-04-20 #1', 1,'company_owner@mail.ru',121,'NAMEEEE','descr',2,'link/link',
--                     25,23,56,45,2,45,57,'3Fd6ht','2020-04-20 10:45:28.437','CREATED','USA','California','Los-Angeles');
-- --
--  INSERT INTO shares(id,share_id,company_id,login,product_photo_url,product_name,product_description,product_count, link_on_product ,
--                     product_price, announcement_duration, share_duration, after_share_duration,
--                     color, picked_items_count, all_items_count, code,date, creation_status,place_country, place_region, place_city)
--             VALUES (2, 'company_owner@mail.ru 2020-04-20 #2', 1,'company_owner@mail.ru','url','NAME2','descrip',3,'link/link2',
--                     25,23,56,45,2,45,57,'5Sxvui','2020-04-20 10:50:28.437','CREATED','USA','California','Los-Angeles');
TRUNCATE TABLE items;
  INSERT INTO items(id,longitude,latitude, share_id, item_id) VALUES (1,43,25,1,'sfgsg');
  INSERT INTO items(id,longitude,latitude, share_id, item_id) VALUES (2,34,32,1,'sfgs1');
  INSERT INTO items(id,longitude,latitude, share_id, item_id) VALUES (3,41,39,1,'sf4sg');
  INSERT INTO items(id,longitude,latitude, share_id, item_id) VALUES (4,40,38,2,'sf6sg');

-- --INSERT INTO items(id,longitude,latitude, share_id, item_id) VALUES (1,46,32,2,'uixops00');
-- --
-- INSERT INTO registrations(id,login,alexa_rank,contact,is_checked,is_confirmed,is_refused,user_id) VALUES(1,'market@magnetball.net',100500,'market@magnetball.net',true,false,false,1);
-- --
--  INSERT INTO settlements(s_name, country, total_area, max_count_items) VALUES ('Moscow','Russia',1103, 20);
-- --
--  COMMIT;