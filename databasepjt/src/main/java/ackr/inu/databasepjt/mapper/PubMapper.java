package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.model.PubReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PubMapper {
    @Insert("INSERT INTO pub (pubName,city,openDate,status) VALUES (#{pubReq.pubName},#{pubReq.city},#{pubReq.openDate},#{pubReq.status})")
    @Options(useGeneratedKeys = true, keyColumn = "pub.idx")
    int save(@Param("pubReq") final PubReq pubReq);
}
