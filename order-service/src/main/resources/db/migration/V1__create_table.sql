create table orders (
    id bigserial not null primary key,
    code text not null,
    quantity int not null,
    price decimal not null,
    order_number text not null
);