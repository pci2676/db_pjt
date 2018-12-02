package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Crime;
import ackr.inu.databasepjt.dto.CrimeRateDto;
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
    @Insert("INSERT INTO crimeRate(year,city_idx,crimeRate) VALUES(#{year},#{city_idx},#{crimeRate})")
    @Options(useGeneratedKeys = true, keyColumn = "crimeRate.idx")
    int saveCrimeRate(@Param("year") final int year, @Param("city_idx") final long city_idx, @Param("crimeRate") final double crimeRate);



    //깔끔하게 범죄율 한방에 빡!
    @Select("SELECT main.*,sub.age,sub.total FROM (SELECT city_idx,sum(event) as event FROM crime WHERE year=#{year} AND typeR='강력범죄' AND city=#{city} OR year=#{year} AND typeR='폭력범죄' AND city=#{city}) as main INNER JOIN (SELECT city_idx,age,total FROM population WHERE age = '합계' AND year = #{year}) as sub ON main.city_idx=sub.city_idx")
    CrimeRateDto crimeRateResponse(@Param("year")final int year,@Param("city")final String city);
}
