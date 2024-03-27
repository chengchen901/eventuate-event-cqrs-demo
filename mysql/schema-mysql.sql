USE todo_service;

DROP table IF EXISTS  todo;

create table todo (
  id varchar(255) PRIMARY KEY,
  title varchar(255),
  completed BOOLEAN,
  order_id INTEGER,
  deleted BOOLEAN
);