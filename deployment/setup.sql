CREATE TABLE IF NOT EXISTS product(
    id INT NOT NULL AUTO_INCREMENT,
    base_price DOUBLE(10) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) NOT NULL,
    product_category_id INT(5) NOT NULL,
    product_name VARCHAR(25) NOT NULL,
    vendor_id INT(10) NOT NULL,
    FOREIGN KEY(vendor_id) REFERENCES vendor(id),
    FOREIGN KEY(product_category_id) REFERENCES product_category(id),
    KEY product_category_id_idx USING BTREE
);

CREATE TABLE IF NOT EXISTS product_category(
    id INT NOT NULL AUTO_INCREMENT,
    product_category_name VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS bid(
    id INT NOT NULL AUTO_INCREMENT,
    bid_price DOUBLE(10) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) NOT NULL,
    product_id INT(5) NOT NULL,
    bid_slot_id INT(5) NOT NULL,
    bidder_id INT(5) NOT NULL,
    FOREIGN KEY(product_id) REFERENCES product(id),
    FOREIGN KEY(bid_slot_id) REFERENCES bid_slot(id),
    FOREIGN KEY(bidder_id) REFERENCES bidder(id),
    KEY bid_slot_id_idx USING BTREE
);

CREATE TABLE IF NOT EXISTS bid_slot(
    id INT NOT NULL AUTO_INCREMENT,
    bidding_start_time datetime(6) NOT NULL,
    bidding_end_time datetime(6) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) NOT NULL,
    product_id INT(5) NOT NULL,
    vendor_id INT(5) NOT NULL,
    FOREIGN KEY(vendor_id) REFERENCES vendor(id),
    FOREIGN KEY(product_id) REFERENCES product(id),
);
