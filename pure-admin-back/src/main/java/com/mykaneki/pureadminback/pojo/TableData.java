package com.mykaneki.pureadminback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableData {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String idCard;
    private String video;
    private String image;
}