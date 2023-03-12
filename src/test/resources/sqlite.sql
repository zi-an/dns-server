
/*
 日志数据格式
 */
1678556199081,192.168.10.12,storage,oth.eve.mdt.qq.com.,120.226.166.190
*/
/*创建日志表*/
create table logs
(
    time   integer,
    client text,
    type   text,
    name   text,
    ip     text
);
/*
插入使用idea
*/

select count(time)
from logs;

/*查同一毫秒查询的最多结果*/
select time, count(0) as count
from logs
group by time
having count(time) > 1
order by count desc;


/*查解析最多的域名*/
select name, count(0) as count
from logs
group by name
having count(name) > 1
order by count desc;

#查某个客户端在某天的日志
select datetime(time / 1000 + 2800, 'unixepoch'), *
from logs
where client = '192.168.10.45';

/*东八区时间,3.7版本不能加localtime*/
SELECT datetime(1678556935023 / 1000 + 28800, 'unixepoch');