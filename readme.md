# DNS服务器
## 使用netty-resolver-dns构建

使用方式查看test下的源码

## 前言:DNS传输协议简介
### 在RFC的规范中，DNS传输协议有很多种,如下所示：

* DNS-over-UDP/53简称”Do53″,是使用UDP进行DNS查询传输的协议。
* DNS-over-TCP/53简称”Do53/TCP”,是使用TCP进行DNS查询传输的协议。
* DNSCrypt,对DNS传输协议进行加密的方法。
* DNS-over-TLS简称”DoT”,使用TLS进行DNS协议传输。
* DNS-over-HTTPS简称”DoH”,使用HTTPS进行DNS协议传输。
* DNS-over-TOR,使用V**或者tunnels连接DNS。