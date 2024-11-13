CREATE TABLE brand
(
    id   BIGINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_brand PRIMARY KEY (id)
);

CREATE TABLE category
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE client
(
    id       VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NULL,
    lastname VARCHAR(255) NULL,
    dni      VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    phone    VARCHAR(255) NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE client_credit_cards
(
    client_id       VARCHAR(255) NOT NULL,
    credit_cards_id VARCHAR(255) NOT NULL
);

CREATE TABLE credit_card
(
    id                      VARCHAR(255) NOT NULL,
    number                  VARCHAR(255) NULL,
    cvv                     VARCHAR(255) NULL,
    year_expiration         VARCHAR(255) NULL,
    month_expiration        VARCHAR(255) NULL,
    credit_card_provider_id VARCHAR(255) NULL,
    client_id               VARCHAR(255) NULL,
    CONSTRAINT pk_creditcard PRIMARY KEY (id)
);

CREATE TABLE credit_card_provider
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_creditcardprovider PRIMARY KEY (id)
);

CREATE TABLE discount
(
    id                  VARCHAR(255) NOT NULL,
    start_date          datetime NULL,
    end_date            datetime NULL,
    discount_percentage INT          NOT NULL,
    CONSTRAINT pk_discount PRIMARY KEY (id)
);

CREATE TABLE item_product
(
    id       VARCHAR(255) NULL,
    quantity INT NOT NULL,
    code     VARCHAR(255) NULL,
    price    DECIMAL NULL
);

CREATE TABLE next_number
(
    id      BIGINT NOT NULL,
    version BIGINT NULL,
    year    INT    NOT NULL,
    current INT    NOT NULL,
    CONSTRAINT pk_nextnumber PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            VARCHAR(255) NOT NULL,
    code          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    category_id   VARCHAR(255) NULL,
    price         DECIMAL NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_discounts
(
    product_id   VARCHAR(255) NOT NULL,
    discounts_id VARCHAR(255) NOT NULL
);

CREATE TABLE sale
(
    id             VARCHAR(255) NOT NULL,
    created_on     datetime NULL,
    client_id      VARCHAR(255) NULL,
    payment_method VARCHAR(255) NULL,
    total_price    DECIMAL NULL,
    CONSTRAINT pk_sale PRIMARY KEY (id)
);

CREATE TABLE shopping_cart
(
    id        VARCHAR(255) NOT NULL,
    client_id VARCHAR(255) NULL,
    sale_id   VARCHAR(255) NULL,
    CONSTRAINT pk_shoppingcart PRIMARY KEY (id)
);

ALTER TABLE client_credit_cards
    ADD CONSTRAINT uc_client_credit_cards_creditcards UNIQUE (credit_cards_id);

ALTER TABLE product_discounts
    ADD CONSTRAINT uc_product_discounts_discounts UNIQUE (discounts_id);

ALTER TABLE credit_card
    ADD CONSTRAINT FK_CREDITCARD_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE credit_card
    ADD CONSTRAINT FK_CREDITCARD_ON_CREDITCARDPROVIDER FOREIGN KEY (credit_card_provider_id) REFERENCES credit_card_provider (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE sale
    ADD CONSTRAINT FK_SALE_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE shopping_cart
    ADD CONSTRAINT FK_SHOPPINGCART_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE shopping_cart
    ADD CONSTRAINT FK_SHOPPINGCART_ON_SALE FOREIGN KEY (sale_id) REFERENCES sale (id);

ALTER TABLE client_credit_cards
    ADD CONSTRAINT fk_clicrecar_on_client FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE client_credit_cards
    ADD CONSTRAINT fk_clicrecar_on_credit_card FOREIGN KEY (credit_cards_id) REFERENCES credit_card (id);

ALTER TABLE product_discounts
    ADD CONSTRAINT fk_prodis_on_discount FOREIGN KEY (discounts_id) REFERENCES discount (id);

ALTER TABLE product_discounts
    ADD CONSTRAINT fk_prodis_on_product FOREIGN KEY (product_id) REFERENCES product (id);