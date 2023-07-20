package com.mykaneki.pureadminback.mapper;

import com.mykaneki.pureadminback.pojo.TableData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableDataMapper {

    List<TableData> list();
    List<TableData> list(Integer id);
    Integer editRow(TableData data);
    Integer deleteRowFromBasicInfo(Integer id);
    Integer deleteRowFromImgInfo(Integer id);

    Integer insertIntoBasicInfo(TableData formData);

    Integer insertIntoImgInfo(TableData formData);
    Integer updateImageAndVideo(Integer id);

    Integer selectMaxId();

}
