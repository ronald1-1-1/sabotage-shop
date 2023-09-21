create table order_on_variant_tb (
    amount integer not null,
    id bigserial not null,
    order_id bigint not null,
    variant_id bigint not null,
    primary key (id));


create table orders_tb (
    date timestamp(6) not null,
    id bigserial not null,
    primary key (id));

create table products_tb (
    price float4 not null,
    show boolean not null,
    description varchar(255),
    name varchar(255) not null,
    vendor_code varchar(255) not null,
    primary key (vendor_code));

create table variant_changes_tb (
    amount_difference integer not null,
    date timestamp(6) not null,
    id bigserial not null,
    variant_id bigint not null,
    comment varchar(255), primary key (id));

create table variants_tb (
    amount integer not null,
    show boolean not null,
    id bigserial not null,
    name varchar(255) not null,
    product_vendor_code varchar(255) not null,
    primary key (id));

alter table if exists order_on_variant_tb
    add constraint order_on_variant_order_fk foreign key (order_id) references orders_tb
alter table if exists order_on_variant_tb
    add constraint order_on_variant_variant_fk foreign key (variant_id) references variants_tb
alter table if exists variant_changes_tb
    add constraint variant_changes_variant_fk foreign key (variant_id) references variants_tb
alter table if exists variants_tb
    add constraint variant_product_fk foreign key (product_vendor_code) references products_tb