create database postgres;
create table if not exists categories
(
    id   serial
    primary key,
    name varchar(255) not null
    unique
    );

create table if not exists options
(
    id          serial
    primary key,
    name        varchar(255) not null,
    category_id int       not null
    references categories(id)
    );

create table if not exists products
(
    id          serial
    primary key,
    name        varchar(255)     not null,
    price       double precision not null,
    category_id int           not null
    references categories(id)
    );

create table if not exists "values"
(
    id         int generated always as identity ,
    name       varchar(255) not null,
    product_id int       not null
    references products(id),
    option_id  int       not null
    references options(id),
    unique (product_id, option_id)
    );

create table if not exists users
(
    id       serial
    primary key,
    role     integer not null,
    login    varchar not null,
    password varchar not null,
    created  date    not null
);

create table if not exists orders
(
    id      serial
    primary key,
    status  integer not null,
    address varchar not null,
    created date    not null,
    user_id integer not null
    references users
);


create table if not exists reviews
(
    id          serial
    primary key,
    published   boolean not null,
    rate        integer not null,
    review_text varchar(1000),
    created     date    not null,
    product_id  bigint  not null
    references products(id),
    user_id     bigint  not null
    references users(id),
    unique (product_id, user_id)
    );

create table if not exists orders_products
(
    id         serial
    primary key,
    product_id bigint  not null
    references products(id),
    order_id   bigint  not null
    references orders(id),
    quantity   integer not null,
    unique (product_id, order_id)
    );
