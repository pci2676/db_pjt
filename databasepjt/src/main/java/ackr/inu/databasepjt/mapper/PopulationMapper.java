package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Population;
import ackr.inu.databasepjt.dto.Tax;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PopulationMapper {

    @Insert("INSERT INTO " +
            "population(city,year,age,total,total_out,total_m,total_w,in_m,in_w,out_m,out_w,out_per) VALUES(#{population.city}, #{population.year}, #{population.age}, #{population.total}, #{population.total_out}, #{population.total_m}, #{population.total_w}, #{population.in_m}, #{population.in_w}, #{population.out_m}, #{population.out_w}), #{population.out_per})")
    @Options(useGeneratedKeys = true, keyColumn = "population.idx")
    int save(@Param("population") final Population population);

}
