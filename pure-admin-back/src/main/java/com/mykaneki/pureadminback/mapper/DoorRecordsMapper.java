package com.mykaneki.pureadminback.mapper;

import com.mykaneki.pureadminback.pojo.TableData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoorRecordsMapper {
    List<TableData> commonSelectOnBasicInfo();
}
