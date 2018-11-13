package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Traffic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TrafficMapper {

    @Insert("INSERT INTO traffic(year, ymd, daynight, week, dead, injured, city, district) VALUES(#{traffic.year}, #{traffic.ymd}, #{traffic.daynight}, #{traffic.week}, #{traffic.dead}, #{traffic.injured}, #{traffic.city}, #{traffic.district})")
    @Options(useGeneratedKeys = true, keyColumn = "traffic.idx")
    int save(@Param("traffic") final Traffic traffic);

}
