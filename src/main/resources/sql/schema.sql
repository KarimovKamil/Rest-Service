DROP TABLE IF EXISTS visit;
DROP TABLE IF EXISTS rs_user;
DROP TABLE IF EXISTS site_page;

CREATE TABLE rs_user (
  user_id  BIGSERIAL,
  username VARCHAR,
  PRIMARY KEY (user_id)
);

CREATE TABLE site_page (
  page_id   BIGSERIAL,
  page_name VARCHAR,
  PRIMARY KEY (page_id)
);

CREATE TABLE visit (
  visit_id   BIGSERIAL,
  user_id    BIGINT,
  page_id    BIGINT,
  visit_date TIMESTAMP,
  PRIMARY KEY (visit_id)
);

ALTER TABLE visit
  ADD CONSTRAINT visit_fk_1 FOREIGN KEY (user_id) REFERENCES rs_user (user_id) ON DELETE SET NULL;
ALTER TABLE visit
  ADD CONSTRAINT visit_fk_2 FOREIGN KEY (page_id) REFERENCES site_page (page_id) ON DELETE SET NULL;
