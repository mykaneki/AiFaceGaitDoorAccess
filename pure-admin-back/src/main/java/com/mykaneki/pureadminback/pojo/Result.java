package com.mykaneki.pureadminback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    Integer code;
    String msg;
    Object data;
    public static Result success(Object data){
        return new Result(0,"success",data);
    }

    public static Result fail(Object data){
        return new Result(400,"fail",data);
    }
}
