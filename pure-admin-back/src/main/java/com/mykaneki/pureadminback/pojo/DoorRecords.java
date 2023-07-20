package com.mykaneki.pureadminback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoorRecords {
    private int recordId;
    private Integer id;
    private Date time;
    private Byte method;
    private Byte result;
}
