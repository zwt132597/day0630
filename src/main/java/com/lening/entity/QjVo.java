package com.lening.entity;

import java.util.Date;

/**
 * 创作时间：2020/6/24 11:15
 * 作者：李增强
 */
public class QjVo {
    private  Integer id;
    private Double qjtime;
    private Date stime;
    private Date etime;
    private String qjcause;
    private Integer qjstatus;
    private String statusStr;
    private Integer userid;//你可以用这个来充当学生id，但是不建议，因为这个还有别的用，而且，和数据库不一样
    private String uname; //这个也可以用来当做学生的名字（在审核的时候，这个用来存审核者的名字的，单独写一个学生的名字也行）
    private String rname;
    private String gname;
    private Integer sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQjtime() {
        return qjtime;
    }

    public void setQjtime(Double qjtime) {
        this.qjtime = qjtime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getQjcause() {
        return qjcause;
    }

    public void setQjcause(String qjcause) {
        this.qjcause = qjcause;
    }

    public Integer getQjstatus() {
        return qjstatus;
    }

    public void setQjstatus(Integer qjstatus) {
        this.qjstatus = qjstatus;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
