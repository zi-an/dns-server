create table if not exists logs
(
    id     integer primary key,
    time   text,
    type   text,
    client text,
    name   text,
    ip     text
);