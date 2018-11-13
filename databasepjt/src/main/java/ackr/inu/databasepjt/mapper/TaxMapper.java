package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.dto.Tax;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaxMapper {

    @Insert("INSERT INTO tax(year, city, district, taxType, tax) VALUES(#{tax.year}, #{tax.city}, #{tax.district}, #{tax.taxType}, #{tax.tax})")
    @Options(useGeneratedKeys = true, keyColumn = "tax.idx")
    int save(@Param("tax") final Tax tax);

}
