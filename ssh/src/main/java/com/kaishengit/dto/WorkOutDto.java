package com.kaishengit.dto;

import java.util.List;

public class WorkOutDto {

    private String companyName;
    private String tel;
    private String linkMan;
    private String cardNum;
    private String address;
    private String wageNumber;
    private String companyTel;
    private Float preCost;
    private Float lastCost;
    private List<WorkOutDto.WorkOutBean> workArray;
    private List<WorkOutDto.DocBean> fileArray;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWageNumber() {
        return wageNumber;
    }

    public void setWageNumber(String wageNumber) {
        this.wageNumber = wageNumber;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public Float getPreCost() {
        return preCost;
    }

    public void setPreCost(Float preCost) {
        this.preCost = preCost;
    }

    public Float getLastCost() {
        return lastCost;
    }

    public void setLastCost(Float lastCost) {
        this.lastCost = lastCost;
    }

    public List<WorkOutBean> getWorkArray() {
        return workArray;
    }

    public void setWorkArray(List<WorkOutBean> workArray) {
        this.workArray = workArray;
    }

    public List<DocBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocBean> fileArray) {
        this.fileArray = fileArray;
    }


    public static class WorkOutBean {
        private Integer id;
        private String workName;
        private String unit;
        private Float wage;
        private Integer outNum;
        private Float total;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getWorkName() {
            return workName;
        }

        public void setWorkName(String workName) {
            this.workName = workName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Float getWage() {
            return wage;
        }

        public void setWage(Float wage) {
            this.wage = wage;
        }

        public Integer getOutNum() {
            return outNum;
        }

        public void setOutNum(Integer outNum) {
            this.outNum = outNum;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

    }

    public static class DocBean {
        private String sourceName;
        private String newName;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
