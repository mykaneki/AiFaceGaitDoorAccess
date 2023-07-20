package com.mykaneki.pureadminback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersBasicInfo {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String idNumber;
}