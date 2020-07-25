package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pl")
public class Pl {

    @Id
    private String problemid;

    @Id
    private String lableid;


    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLableid() {
        return lableid;
    }

    public void setLableid(String lableid) {
        this.lableid = lableid;
    }
}
