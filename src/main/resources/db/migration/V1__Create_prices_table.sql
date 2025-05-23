CREATE TABLE PRICES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID BIGINT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    PRIORITY INT NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURR VARCHAR(3) NOT NULL
);