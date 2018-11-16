package ackr.inu.databasepjt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CrimeRateMapper {
    @Select("SELECT *")
    double rate(@Param("year") int crimeYear, @Param("city") String city);
}
