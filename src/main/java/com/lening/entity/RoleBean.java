package com.lening.entity;

/**
 * 创作时间：2020/4/9 9:54
 * 作者：李增强
 */
public class RoleBean {

    private Integer rid;
    private String rname;
    private  DeptBean deptBean=new DeptBean();


    public DeptBean getDeptBean() {
        return deptBean;
    }

    public void setDeptBean(DeptBean deptBean) {
        this.deptBean = deptBean;
    }
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
