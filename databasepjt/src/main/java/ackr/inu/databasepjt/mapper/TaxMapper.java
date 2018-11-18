package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Tax;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TaxMapper {

    @Insert("INSERT INTO tax(year, city, district, taxType, tax) VALUES(#{tax.year}, #{tax.city}, #{tax.district}, #{tax.taxType}, #{tax.tax})")
    @Options(useGeneratedKeys = true, keyColumn = "tax.idx")
    int save(@Param("tax") final Tax tax);


    @Select("SELECT SUM(tax) FROM tax WHERE year = #{year} AND city LIKE #{city}")
    double getTax(@Param("year")final int year,@Param("city")final String city);
}
