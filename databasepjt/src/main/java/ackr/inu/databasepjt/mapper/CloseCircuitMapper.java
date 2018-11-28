package ackr.inu.databasepjt.mapper;

import ackr.inu.databasepjt.model.CctvReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CloseCircuitMapper {
    @Insert("INSERT INTO cctv (cctv_position,count,latitude,longitude) VALUES (#{cctvReq.position},#{cctvRep.count},#{cctvReq.latitude},#{cctvReq.longitude})")
    @Options(useGeneratedKeys = true, keyColumn = "cctv.cctv_idx")
    void save2(@Param("cctv")CctvReq cctvReq);
}
