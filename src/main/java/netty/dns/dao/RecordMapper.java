package netty.dns.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Select("select * from records order by id ")
    List<RecordEntity> selectAll();

    @Select("select * from records where domain not like '%mm' order by id ")
    List<RecordEntity> selectData();

    @Insert("insert into records(domain,ip) " +
            "values(#{domain},#{ip})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOne(RecordEntity recordEntity);

    @Delete("delete from records where id=#{id}")
    int deleteOne(int id);

    @Update("update records set ip=#{ip},domain=#{domain} " +
            "where id =#{id}")
    int UpdateOne(RecordEntity records);
}
