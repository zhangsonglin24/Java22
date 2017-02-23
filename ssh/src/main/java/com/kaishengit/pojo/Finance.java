package com.kaishengit.pojo;

import lombok.Data;

@Data
public class Finance {

    public static final String TYPE_IN = "收入";
    public static final String TYPE_OUT = "支出";
    public static final String STATE_NO = "未确认";
    public static final String STATE_OK = "已确认";


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
    private String moduleSerialNumber;

}
