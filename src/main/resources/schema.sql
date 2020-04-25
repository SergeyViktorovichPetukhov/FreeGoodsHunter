DROP SCHEMA IF EXISTS public CASCADE;
commit;
CREATE SCHEMA public;
commit;

CREATE TABLE users
(
    id                    BIGSERIAL     NOT NULL,
    login                 VARCHAR(30)   NOT NULL      UNIQUE,
    all_items_count       INTEGER,
    picked_items_count    INTEGER,
    email                 VARCHAR(30),
    phone                 VARCHAR(9),
    name                  VARCHAR(30),
    has_company           BOOLEAN       DEFAULT FALSE,
    company_id            INTEGER
);

ALTER TABLE users
    ADD CONSTRAINT users_PK PRIMARY KEY (id);


CREATE TABLE IF NOT EXISTS shares
(
    id                    BIGSERIAL     NOT NULL,
    share_id              VARCHAR(50),
    company_id            INTEGER,
    login                 VARCHAR(30),
    product_photo_url     VARCHAR(100),
    product_name          VARCHAR(100),
    product_description   VARCHAR(300),
    product_count         INTEGER,
    product_image_id      INTEGER,
    link_on_product       VARCHAR(50),
    product_price         FLOAT         NOT NULL,
    announcement_duration INTEGER,
    share_duration        INTEGER,
    after_share_duration  INTEGER,
    color                 VARCHAR(50)   NOT NULL,
    picked_items_count    INTEGER,
    all_items_count       INTEGER,
    code                  VARCHAR(15),
    date                  TIMESTAMP     NOT NULL,
    status                VARCHAR(10),
    creation_status       VARCHAR(10),
    message_for_user      VARCHAR(100),
    place_country         VARCHAR(40),
    place_region          VARCHAR(40),
    place_city            VARCHAR(40)
);


ALTER TABLE shares
    ADD CONSTRAINT shares_PK PRIMARY KEY (id);


CREATE TABLE registrations
(
    id                  BIGSERIAL     NOT NULL,
    login               VARCHAR(30)   NOT NULL   UNIQUE,
    address             VARCHAR(30),
    phone               VARCHAR(10)   NOT NULL   UNIQUE,
    code                VARCHAR(30),
    is_new              BOOLEAN,
    is_confirmed        BOOLEAN,
    reason_of_refuse    VARCHAR(50),
    user_id             INTEGER
);

ALTER TABLE registrations
    ADD CONSTRAINT registrations_PK PRIMARY KEY (id);


CREATE TABLE images
(
    id      BIGSERIAL,
    format  VARCHAR(40),
    image   BYTEA
);

ALTER TABLE images
  ADD CONSTRAINT images_PK PRIMARY KEY (id);

CREATE TABLE share_statuses
(
    id      SERIAL        NOT NULL,
    name    VARCHAR(50)   NOT NULL
);

ALTER TABLE share_statuses
  ADD CONSTRAINT share_statuses_PK PRIMARY KEY (id);

ALTER TABLE share_statuses
  ADD CONSTRAINT share_statuses_UN UNIQUE (name);

CREATE TABLE items
(
    id         BIGSERIAL  NOT NULL,
    longitude  FLOAT      NOT NULL,
    latitude   FLOAT      NOT NULL,
    share_id   INTEGER    NOT NULL,
    user_id    INTEGER
);

ALTER TABLE items
  ADD CONSTRAINT items_PK PRIMARY KEY (id);

CREATE TABLE address
(
    id            BIGSERIAL    NOT NULL,
    country       VARCHAR(50)  NOT NULL,
    region        VARCHAR(50)  NOT NULL,
    city          VARCHAR(50)  NOT NULL,
    address_line  VARCHAR(200)
);

ALTER TABLE address
  ADD CONSTRAINT address_PK PRIMARY KEY (id);

CREATE TABLE IF NOT EXISTS companies
(
    id            BIGSERIAL    NOT NULL,
    login         VARCHAR(50)  NOT NULL,
    address       VARCHAR(50),
    phone         VARCHAR(50)  NOT NULL,
    internet_shop VARCHAR(100),
    code          VARCHAR(40),
    label_path    VARCHAR(50),
    user_id       INTEGER
);

ALTER TABLE companies
    ADD CONSTRAINT companies_PK PRIMARY KEY (id);

-- create table users_shares
-- (
--     user_id INTEGER not null,
--     share_id INTEGER not null
-- );

-----------------------------------------------------------------------------------
-------------------------------Foreign keys----------------------------------------
-----------------------------------------------------------------------------------
-- ALTER TABLE users
--     ADD CONSTRAINT user_company_FK FOREIGN KEY  (company_id)
--         REFERENCES companies(id);
-- --

-- alter table users_shares
--     add constraint shares_users_FK foreign key (share_id) references shares(id);
--
-- alter table users_shares
--     add constraint users_shares_FK foreign key (user_id) references users(id);

alter table items
    add constraint items_users_FK foreign key (user_id) references users(id);

ALTER TABLE shares
    ADD CONSTRAINT company_share_FK FOREIGN KEY (company_id)
        REFERENCES companies(id);

ALTER TABLE shares
  ADD CONSTRAINT shares_image_FK FOREIGN KEY
  (
    product_image_id
  )
REFERENCES images
  (
    id
  );

ALTER TABLE items
  ADD CONSTRAINT items_share_FK FOREIGN KEY
  (
    share_id
  )
REFERENCES shares
  (
    id
  ) ON DELETE CASCADE;

ALTER TABLE items
    ADD CONSTRAINT items_user_FK FOREIGN KEY
        (
         user_id
            )
        REFERENCES users
            (
             id
                ) ON DELETE CASCADE;

ALTER TABLE registrations
    ADD CONSTRAINT registrations_FK FOREIGN KEY (user_id) REFERENCES users(id);

COMMIT;


-----------------------------------------------------------------------
------------------------------------------------------------------------
--------------       SEQUENCES    --------------------------------------

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 100 INCREMENT BY 1;

