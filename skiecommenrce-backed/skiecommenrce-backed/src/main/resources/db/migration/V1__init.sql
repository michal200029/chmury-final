CREATE TABLE address (
    id  UUID PRIMARY KEY,
    city TEXT NOT NULL,
    street TEXT NOT NULL,
    country TEXT NOT NULL,
    zip_code TEXT NOT NULL
);

CREATE TABLE customer (
    id  UUID PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL
);

CREATE TABLE product_category (
    id  NUMERIC PRIMARY KEY,
    category_name TEXT NOT NULL
);

CREATE TABLE product (
    id  NUMERIC PRIMARY KEY,
    brand TEXT NOT NULL,
    name TEXT NOT NULL,
    size DECIMAL(10,2) NOT NULL,
    condition TEXT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    units_in_stock int NOT NULL,
    special TEXT,
    image_url TEXT NOT NULL,
    product_key TEXT NOT NULL,
    category_id NUMERIC NOT NULL,
    CONSTRAINT fk__category_id FOREIGN KEY (category_id) REFERENCES product_category (id)
);

CREATE TABLE order_detail (
    id  UUID PRIMARY KEY,
    order_tracking_number TEXT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    status TEXT NOT NULL,
    date_created TIMESTAMP NOT NULL,
    customer_id UUID NOT NULL,
    address_id UUID NOT NULL,
    shipping_method TEXT NOT NULL,
    payment_method TEXT NOT NULL,
    CONSTRAINT fk__customer_id FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT fk__address_id FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE order_item (
    id  UUID PRIMARY KEY,
    unit_price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    order_detail_id UUID NOT NULL,
    product_id NUMERIC NOT NULL,
    CONSTRAINT fk__product_id FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk__order_detail_id FOREIGN KEY (order_detail_id) REFERENCES order_detail (id)
);

CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL
);

