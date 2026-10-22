create table inventory (
    id bigserial primary key,
    code text not null,
    quantity int not null
);