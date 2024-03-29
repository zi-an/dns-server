/*日志记录表*/
create table if not exists logs
(
    id     integer primary key,
    time   text,
    type   text,
    client text,
    name   text,
    ip     text
);
/*域名表*/
create table if not exists records
(
    id     integer primary key,
    domain text,
    ip     text
);
insert or ignore
into records(id, domain, ip)
values (-1001, '1.mm', '192.168.10.1'),
       (-1002, '2.mm', '192.168.10.2'),
       (-1003, '3.mm', '192.168.10.3'),
       (-1004, '4.mm', '192.168.10.4'),
       (-1005, '5.mm', '192.168.10.5'),
       (-1006, '6.mm', '192.168.10.6'),
       (-1007, '7.mm', '192.168.10.7'),
       (-1008, '8.mm', '192.168.10.8'),
       (-1009, '9.mm', '192.168.10.9'),
       (-1010, '10.mm', '192.168.10.10'),
       (-1011, '11.mm', '192.168.10.11'),
       (-1012, '12.mm', '192.168.10.12'),
       (-1013, '13.mm', '192.168.10.13'),
       (-1014, '14.mm', '192.168.10.14'),
       (-1015, '15.mm', '192.168.10.15'),
       (-1016, '16.mm', '192.168.10.16'),
       (-1017, '17.mm', '192.168.10.17'),
       (-1018, '18.mm', '192.168.10.18'),
       (-1019, '19.mm', '192.168.10.19'),
       (-1020, '20.mm', '192.168.10.20'),
       (-1200, '200.mm', '192.168.10.200'),
       (-1201, '201.mm', '192.168.10.201'),
       (-1202, '202.mm', '192.168.10.202'),
       (-1203, '203.mm', '192.168.10.203'),
       (-1204, '204.mm', '192.168.10.204'),
       (-1205, '205.mm', '192.168.10.205'),
       (-1206, '206.mm', '192.168.10.206'),
       (-1207, '207.mm', '192.168.10.207'),
       (-1208, '208.mm', '192.168.10.208'),
       (-1209, '209.mm', '192.168.10.209'),
       (-1210, '210.mm', '192.168.10.210'),
       (-1211, '211.mm', '192.168.10.211'),
       (-1212, '212.mm', '192.168.10.212'),
       (-1213, '213.mm', '192.168.10.213'),
       (-1214, '214.mm', '192.168.10.214'),
       (-1215, '215.mm', '192.168.10.215'),
       (-1216, '216.mm', '192.168.10.216'),
       (-1217, '217.mm', '192.168.10.217'),
       (-1218, '218.mm', '192.168.10.218'),
       (-1219, '219.mm', '192.168.10.219'),
       (-1220, '220.mm', '192.168.10.220'),
       (-1221, '221.mm', '192.168.10.221'),
       (-1222, '222.mm', '192.168.10.222');
insert or ignore
into records(id, domain, ip)
values (1, 'ws.p2p.huya.com', '127.0.0.1'),
       (2, 'update.miui.com', '127.0.0.1'),
       (3, 'arm.lczzjj.cn', '127.0.0.1');