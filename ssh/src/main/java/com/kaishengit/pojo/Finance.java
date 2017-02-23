package com.kaishengit.pojo;

import lombok.Data;

@Data
public class Finance {
    private Integer id;
    private String serialNumber;
    private String type;
    private String remark;
    private Float money;
    private String state;
    private String module;
    private String createDate;
    private String createUser;
    private String confirmUser;
    private String confirmDate;

}
