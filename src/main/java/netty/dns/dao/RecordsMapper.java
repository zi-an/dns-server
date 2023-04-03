package netty.dns.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RecordsMapper {
    @Select("select * from records order by id")
    List<Records> selectAll();

    @Insert("insert into records(domain,ip) " +
            "values(#{domain},#{ip})")
    int insertOne(String domain, String ip);

    @Delete("delete from records where id=#{id}")
    int deleteOne(int id);

    @Update("update records set ip=#{ip},domain=#{domain} " +
            "where id =#{id}")
    int UpdateOne(Records records);
}
