insert into categories (name)
values ('Процессоры'),
       ('Мониторы');


insert into products (name, price, category_id)
values ('Intel Core I9 9900', 249990, 1),
       ('AMD Ryzen R7 7700', 269990, 1),
       ('Samsung SU556270', 229990, 2),
       ('AOC Z215S659', 149990, 2);

insert into options (name, category_id)
values ('Производитель', 1),
       ('Количество ядер', 1),
       ('Сокет', 1),
       ('Производитель', 2),
       ('Диагональ', 2),
       ('Матрица', 2),
       ('Разрешение', 2);

insert into values (name, option_id, product_id)
values ('Intel', 1, 1),
       ('8', 2, 1),
       ('1250', 3, 1),
       ('AMD', 1, 2),
       ('12', 2, 2),
       ('1250', 3, 2),
       ('Samsung', 4, 3),
       ('27', 5, 3),
       ('TN', 6, 3),
       ('2560*1440', 7, 3),
       ('AOC', 4, 4),
       ('21.5', 5, 4),
       ('AH-IPS', 6, 4),
       ('1920*1080', 7, 4);