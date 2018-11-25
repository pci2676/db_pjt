package ackr.inu.databasepjt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityMapper {

    @Insert("INSERT INTO city(city_name) VALUES (#{city_name})")
    @Options(useGeneratedKeys = true, keyColumn = "city.city_id")
    void save(@Param("city_name")String city_name);
}
