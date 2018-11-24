package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Population;
import ackr.inu.databasepjt.dto.Tax;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PopulationMapper {

    @Insert("INSERT INTO " +
            "population(city,year,age,total,total_out,total_m,total_w,in_m,in_w,out_m,out_w,out_per) " +
            "VALUES(#{population.city}, #{population.year}, #{population.age}, #{population.total}, #{population.total_out}, #{population.total_m}, #{population.total_w}, #{population.in_m}, #{population.in_w}, #{population.out_m}, #{population.out_w}, #{population.out_per})")
    @Options(useGeneratedKeys = true, keyColumn = "population.idx")
    int save(@Param("population") final Population population);

    //해당년도 입력받은 도시, 총 인구수 출력
    @Select("SELECT total FROM population WHERE year=#{year} AND age='합계' AND city LIKE #{city}")
    double total(@Param("year")final int year,@Param("city")final String city);

    //해당년도 입력받은 도시, 연령대별 인구수 출력
    @Select("SELECT total FROM population WHERE year=#{year} AND age='#{age}' AND city LIKE #{city}")
    int selectedAgeTotal(@Param("year")final int year,@Param("city")final String city, @Param("age")final String age);
}
