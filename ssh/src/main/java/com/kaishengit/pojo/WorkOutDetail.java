package com.kaishengit.pojo;

import lombok.Data;

@Data
public class WorkOutDetail {
    private Integer id;
    private String workName;
    private String workUnit;
    private Float workWage;
    private Integer num;
    private Float totalPrice;
    private Integer workId;
}
