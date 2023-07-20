package com.mykaneki.pureadminback.mapper;

import com.mykaneki.pureadminback.pojo.UsersBasicInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserBasicInfoMapperTest {
    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;
    @Test
    void commonSelect() {
        List<UsersBasicInfo> usersBasicInfos = userBasicInfoMapper.commonSelect();
        for (UsersBasicInfo usersBasicInfo : usersBasicInfos) {
            System.out.println(usersBasicInfo);
        }

    }

}
