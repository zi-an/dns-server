package netty.dns.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select id,datetime(time,'unixepoch','localtime') as time"+
            ",type,client,name,ip from logs where id > #{id} order by id desc limit 20 ")
    List<LogEntity> selectAll(int id);

    @Select("select id,datetime(time,'unixepoch','localtime') as time"+
            ",type,client,name,ip from logs where id > #{id} and client = #{ip} order by id desc limit 20 ")
    List<LogEntity> selectByIP(int id, String ip);

    @Select("select * from logs order by id limit 100 desc")
    List<LogEntity> select100();

    @Insert("insert into logs(time,type,client,name,ip) values(#{time},#{type},#{client},#{name},#{ip})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
        //返回自增主键到logs.id,否则只有成功失败
    boolean insertOne(LogEntity logs);
}
