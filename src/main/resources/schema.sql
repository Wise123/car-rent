drop database if exists car_rent;

  create database car_rent;

  use car_rent;

  create table car_manufacturer (
  	id bigint not null auto_increment,
  	name varchar(255) not null,
  	primary key(id)
  );

  create table car_model (
  	id bigint not null auto_increment,
  	name varchar(255) not null,
  	primary key(id)
  );

  create table car_manufacturer_car_model (
  	car_manufacturer_id bigint not null,
  	car_model_id bigint not null,
  	constraint primary key (car_manufacturer_id, car_model_id),
  	constraint foreign key (car_manufacturer_id) references car_manufacturer (id),
  	constraint foreign key (car_model_id) references car_model (id)
  );

  create table car (
  	id bigint not null auto_increment,
  	reg_num varchar(255) not null,
  	car_model_id bigint not null,
  	primary key(id),
  	constraint foreign key (car_model_id) references car_model (id)
  );

  create table rent_point (
  	id bigint not null auto_increment,
  	address varchar(255) not null,
  	primary key(id)
  );

  create table renter (
  	id bigint not null auto_increment,
  	first_name varchar(255) not null,
  	surname varchar(255) not null,
  	patronymic varchar(255) not null,
  	primary key(id)
  );

  create table rental_session (
  	id bigint not null auto_increment,
  	session_start date not null,
  	session_end date not null,
  	start_rent_point_id bigint not null,
  	end_rent_point_id bigint,
  	renter_id bigint not null,
  	car_id bigint not null,
  	primary key(id),
  	constraint foreign key (start_rent_point_id) references rent_point (id),
  	constraint foreign key (end_rent_point_id) references rent_point (id),
  	constraint foreign key (renter_id) references renter (id),
  	constraint foreign key (car_id) references car (id)
  );


  insert into car_manufacturer (name) values
  ('lada'),
  ('uaz'),
  ('niva'),
  ('chevrolet'),
  ('lamborghini');


  insert into car_model (name) values
  ('priora'),
  ('patriot'),
  ('chevrolet-niva'),
  ('gallardo');

  insert into car_manufacturer_car_model (car_manufacturer_id, car_model_id) values
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 3),
  (5, 4);

  insert into car (reg_num, car_model_id) values
  ('с065мк78rus', 1),
  ('с066мк78rus', 1),
  ('с067мк78rus', 1),
  ('с065ок78rus', 2),
  ('с065ок78rus', 3),
  ('а777аа59rus', 4);


  insert into rent_point (address) values
  ('Ленина 22'),
  ('Ленина 502'),
  ('Мира 1'),
  ('Комсомольский проспект 5'),
  ('Рязанская 7');

  insert into renter (surname, first_name, patronymic) values
  ('Иванов', 'Иван', 'Иванович'),
  ('Сидоров', 'Сидор', 'Сидорович'),
  ('Петров', 'Петр', 'Петрович'),
  ('Андреев', 'Андрей', 'Андрееви');

  insert into rental_session (
      session_start,
      session_end,
      start_rent_point_id,
      end_rent_point_id,
      renter_id,
      car_id
  ) values (
      '2019-01-01',
      '2019-01-02',
      1,
      1,
      1,
      1
  ),(
      '2019-01-01',
      '2019-03-02',
      2,
      3,
      1,
      2
  ),(
      '2019-01-01',
      '2019-01-15',
      3,
      2,
      2,
      2
  ),(
      '2019-01-01',
      '2019-01-02',
      3,
      3,
      3,
      3
  ),(
      '2019-01-01',
      '2019-01-02',
      2,
      2,
      2,
      2
  );