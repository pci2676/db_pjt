package ackr.inu.databasepjt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CrimeRateMapper {
    @Select("SELECT *")
    double rate(@Param("year") int crimeYear, @Param("city") String city, @Param("type1") String type1, @Param("type2") String type2);
}
