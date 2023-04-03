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
values (1, '1.mm', '192.168.10.1'),
       (2, '2.mm', '192.168.10.2'),
       (3, '3.mm', '192.168.10.3'),
       (4, '4.mm', '192.168.10.4'),
       (5, '5.mm', '192.168.10.5'),
       (6, '6.mm', '192.168.10.6'),
       (7, '7.mm', '192.168.10.7'),
       (8, '8.mm', '192.168.10.8'),
       (9, '9.mm', '192.168.10.9'),
       (10, '10.mm', '192.168.10.10'),
       (11, '11.mm', '192.168.10.11'),
       (12, '12.mm', '192.168.10.12'),
       (13, '13.mm', '192.168.10.13'),
       (14, '14.mm', '192.168.10.14'),
       (15, '15.mm', '192.168.10.15'),
       (16, '16.mm', '192.168.10.16'),
       (17, '17.mm', '192.168.10.17'),
       (18, '18.mm', '192.168.10.18'),
       (19, '19.mm', '192.168.10.19'),
       (20, '20.mm', '192.168.10.20'),
       (201, '201.mm', '192.168.10.201'),
       (202, '202.mm', '192.168.10.202'),
       (203, '203.mm', '192.168.10.203'),
       (204, '204.mm', '192.168.10.204'),
       (205, '205.mm', '192.168.10.205'),
       (206, '206.mm', '192.168.10.206'),
       (207, '207.mm', '192.168.10.207'),
       (208, '208.mm', '192.168.10.208'),
       (209, '209.mm', '192.168.10.209'),
       (200, '210.mm', '192.168.10.210'),
       (211, '211.mm', '192.168.10.211'),
       (212, '212.mm', '192.168.10.212'),
       (213, '213.mm', '192.168.10.213'),
       (214, '214.mm', '192.168.10.214'),
       (215, '215.mm', '192.168.10.215'),
       (216, '216.mm', '192.168.10.216'),
       (217, '217.mm', '192.168.10.217'),
       (218, '218.mm', '192.168.10.218'),
       (219, '219.mm', '192.168.10.219'),
       (220, '220.mm', '192.168.10.220'),
       (221, '221.mm', '192.168.10.221'),
       (222, '222.mm', '192.168.10.222');
insert or ignore
into records(id, domain, ip)
values (21, 'ws.p2p.huya.com', '127.0.0.1'),
       (22, 'update.miui.com', '127.0.0.1'),
       (23, 'arm.lczzjj.cn', '127.0.0.1');