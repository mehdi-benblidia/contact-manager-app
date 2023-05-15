CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS company
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    uuid    UUID DEFAULT RANDOM_UUID(),
    address VARCHAR(250)          NOT NULL,
    tva     VARCHAR(250)          NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS company_contact
(
    company_id  BIGINT NOT NULL,
    contact_id BIGINT NOT NULL,
    CONSTRAINT pk_company_contact PRIMARY KEY (company_id, contact_id)
);

CREATE TABLE IF NOT EXISTS contact
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    uuid       UUID DEFAULT RANDOM_UUID(),
    first_name VARCHAR(250)          NOT NULL,
    last_name  VARCHAR(250)          NOT NULL,
    address    VARCHAR(250)          NOT NULL,
    tva        VARCHAR(250),
    statu      VARCHAR(250),
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

ALTER TABLE company_contact
    ADD CONSTRAINT fk_company_contact_on_company FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE company_contact
    ADD CONSTRAINT fk_company_contact_on_contact FOREIGN KEY (contact_id) REFERENCES contact (id);