package com.tensquare.base.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_label")
public class Label {


    private String id;
    private String lablelname;
    private String state;
    private Long count;
    private Long fans;
    private String recommend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLablelname() {
        return lablelname;
    }

    public void setLablelname(String lablelname) {
        this.lablelname = lablelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
