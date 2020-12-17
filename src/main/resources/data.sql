DROP TABLE IF EXISTS coffee;

CREATE TABLE coffee (
  coffee_id INT AUTO_INCREMENT  PRIMARY KEY,
  coffee_name VARCHAR(250) NOT NULL,
  coffee_type VARCHAR(250) NOT NULL,
  coffee_rating VARCHAR(250) NOT NULL
);

insert into coffee (coffee_name, coffee_type, coffee_rating) values ('Charbucks Burnt Roast','Dark Roast',2.0);
insert into coffee (coffee_name, coffee_type, coffee_rating) values ('Blumpkin Dounts Dark Roast', 'Dark Roast', 4.0);
insert into coffee (coffee_name, coffee_type, coffee_rating) values ('Cafe Ladero','Medium Roast', 5.0);