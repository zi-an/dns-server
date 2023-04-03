/*仅仅为测试用SQL*/
drop table if exists logs;
create table if not exists logs
(
    id     integer primary key autoincrement,
    time   text,
    type   text,
    client text,
    name   text,
    ip     text
);

insert into logs(time, type, client, name, ip)
values ('2', '2', '2', '2', '2');

select *
from logs;

select id, time, type, client, name, ip
from logs;

select datetime(time, 'unixepoch', 'localtime')
from logs
order by id desc;
select time
from logs;

SELECT datetime(1679976919, 'unixepoch', 'localtime') as utc8;

select id, datetime(time, 'unixepoch', 'localtime'), type, client, name, ip
from logs;

select id, datetime(time, 'unixepoch', 'localtime'), type, client, name, ip
from logs;

/*记录表*/
select *
from logs;

insert or ignore into logs
select *
from logs;

insert or ignore into logs
values (5, 5, 5, 5, 5, 5);

create table if not exists records
(
    id     integer primary key autoincrement,
    domain text,
    ip     text
);

select*
from records;

/*sqlite3.7.11以上才支持多行插入*/
select * from records order by id;

select * from records where domain not like '%.mm' order by id ;