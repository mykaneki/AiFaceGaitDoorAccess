package com.mykaneki.pureadminback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersImgInfo {
    private int id;
    private String faceImgPath;
    private String gaitImgPath;
}