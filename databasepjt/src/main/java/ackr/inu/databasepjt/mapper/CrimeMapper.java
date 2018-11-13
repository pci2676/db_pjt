package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Crime;
import ackr.inu.databasepjt.dto.Tax;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CrimeMapper {

    @Insert("INSERT INTO crime(year, typeR, typeM, city, event) VALUES(#{crime.year}, #{crime.typeR}, #{crime.typeM}, #{crime.city}, #{crime.event})")
    @Options(useGeneratedKeys = true, keyColumn = "crime.idx")
    int save(@Param("crime") final Crime crime);

}
