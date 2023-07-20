package com.mykaneki.pureadminback.mapper;

import com.mykaneki.pureadminback.pojo.UsersBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserBasicInfoMapper {
    @Select("select id,name,gender,age,id_number from users_basic_info")
    public List<UsersBasicInfo> commonSelect();
}
