DROP SCHEMA IF EXISTS public CASCADE;
commit;
CREATE SCHEMA public;
commit;

CREATE TABLE shares
(
    id                    BIGSERIAL     NOT NULL,
    description           VARCHAR(300),
    count_of_product      INTEGER       NOT NULL,
    product_image_id      INTEGER,
    product_name          VARCHAR(100),
    link_on_product_url   VARCHAR(100),
    product_price         FLOAT         NOT NULL,
    announcement_duration INTEGER,
    share_duration        INTEGER,
    after_share_duration  INTEGER,
    color                 VARCHAR(40)   NOT NULL,
    picked_items_count    INTEGER       NOT NULL,
    all_items_count       INTEGER       NOT NULL,
    code_for_winner       VARCHAR(100),
    date                  TIMESTAMP     NOT NULL,
    status_id             INTEGER,
    place_address_id      INTEGER,
    company_id            INTEGER,
    user_id               INTEGER
);

ALTER TABLE shares
  ADD CONSTRAINT shares_PK PRIMARY KEY (id);


CREATE TABLE users
(
    id                    BIGSERIAL     NOT NULL,
    login                 VARCHAR(30)   NOT NULL      UNIQUE,
    win_count             INTEGER,
    email                 VARCHAR(30),
    phone                 VARCHAR(9),
    name                  VARCHAR(30),
    is_confirmed          BOOLEAN       DEFAULT FALSE,
    company_id            INT
);

ALTER TABLE users
    ADD CONSTRAINT users_PK PRIMARY KEY (id);

CREATE TABLE registrations
(
    id                  BIGSERIAL     NOT NULL,
    login               VARCHAR(30)   NOT NULL   UNIQUE,
    address             VARCHAR(30)   NOT NULL,
    phone               VARCHAR(10)   NOT NULL   UNIQUE,
    code                VARCHAR(30),
    is_new              BOOLEAN,
    is_confirmed        BOOLEAN,
    reason_of_refuse    VARCHAR(50),
    user_id             INTEGER
);

ALTER TABLE registrations
    ADD CONSTRAINT registrations_PK PRIMARY KEY (id);

ALTER TABLE registrations
    ADD CONSTRAINT registrations_FK FOREIGN KEY (user_id) REFERENCES users(id);

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

-----------------------------------------------------------------------------------
-------------------------------Foreign keys----------------------------------------
-----------------------------------------------------------------------------------

ALTER TABLE shares
  ADD CONSTRAINT shares_address_FK FOREIGN KEY
  (
    place_address_id
  )
REFERENCES address
  (
    id
  );


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

COMMIT;
