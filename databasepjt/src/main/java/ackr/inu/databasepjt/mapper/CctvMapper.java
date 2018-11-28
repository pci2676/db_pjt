package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.model.CctvReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CctvMapper {
    @Insert("INSERT INTO cctv (cctv_position,count,latitude,longitude,purpose) " +
            "VALUES (#{cctvReq.position},#{cctvReq.count},#{cctvReq.latitude},#{cctvReq.longitude},#{cctvReq.purpose})")
    @Options(useGeneratedKeys = true, keyColumn = "cctv.cctv_idx")
    void save(@Param("cctvReq") CctvReq cctvReq);

}
