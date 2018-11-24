package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Traffic;
import ackr.inu.databasepjt.dto.TrafficTop;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrafficMapper {

    @Insert("INSERT INTO traffic(year, ymd, daynight, week, dead, injured, city) VALUES(#{traffic.year}, #{traffic.ymd}, #{traffic.daynight}, #{traffic.week}, #{traffic.dead}, #{traffic.injured}, #{traffic.city})")
    @Options(useGeneratedKeys = true, keyColumn = "traffic.idx")
    int save(@Param("traffic") final Traffic traffic);

    //년도, 도시입력 -> 당시 교통사고 출력
    @Select("SELECT count(idx) FROM traffic WHERE year=#{year} AND city LIKE #{city}")
    int getTrafficCity(@Param("year") final int year, @Param("city") final String city);

    @Select("SELECT * " +
            "FROM (SELECT city,count(city) as '총량'" +
                "FROM traffic " +
                "WHERE city LIKE #{city} AND year = #{year}" +
                "GROUP BY (#{city}))c " +
            "ORDER BY (c.총량) desc LIMIT 3")
    List<TrafficTop> getTopTraffic(@Param("year")final int year, @Param("city") final String city);

}
