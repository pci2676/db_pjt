package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.model.SuicideReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SuicideMapper {

    @Insert("INSERT INTO suicide (year,city,suicide_rate) VALUES (#{suicideReq.year},#{suicideReq.city},#{suicideReq.rate})")
    @Options(useGeneratedKeys = true, keyColumn = "suicide.suicide_idx")
    void save(@Param("suicideReq") SuicideReq suicideReq);
}
