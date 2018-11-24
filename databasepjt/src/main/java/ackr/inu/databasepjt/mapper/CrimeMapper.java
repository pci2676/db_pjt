package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Crime;
import ackr.inu.databasepjt.dto.Tax;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CrimeMapper {

    //json을 이용해 범죄데이터 입력시 사용
    @Insert("INSERT INTO crime(year, typeR, typeM, city, event) VALUES(#{crime.year}, #{crime.typeR}, #{crime.typeM}, #{crime.city}, #{crime.event})")
    @Options(useGeneratedKeys = true, keyColumn = "crime.idx")
    int save(@Param("crime") final Crime crime);

    //입력받은 도시, 년도를 가지고 강력범죄와 폭력범죄의 합을 보여준다.
    @Select("SELECT SUM(event) FROM crime WHERE year = #{year} AND city LIKE #{city} AND typeR='강력범죄' OR year = #{year} AND city LIKE #{city} AND typeR='폭력범죄'")
    double countingCrime(@Param("year") int year, @Param("city") String city);

    //도시이름
    @Select("SELECT city FROM crime WHERE city LIKE #{city} LIMIT 1")
    String cityName(@Param("city")final String city);

    //시별 범죄율 저장하는 쿼리
    @Insert("INSERT INTO crimeRate(year,city,crimeRate) VALUES(#{year},#{city},#{crimeRate})")
    @Options(useGeneratedKeys = true, keyColumn = "crimeRate.idx")
    int saveCrimeRate(@Param("year") final int year, @Param("city") final String city, @Param("crimeRate") final double crimeRate);
}
