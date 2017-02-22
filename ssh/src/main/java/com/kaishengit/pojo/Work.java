package com.kaishengit.pojo;

import lombok.Data;

@Data
public class Work {

    private Integer id;
    private String name;
    private String unit;
    private Integer totalNum;
    private Integer currentNum;
    private Float wage;
}
