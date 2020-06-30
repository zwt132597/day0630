package com.lening.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 创作时间：2020/4/7 10:49
 * 作者：李增强
 */
public class UserBean {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String birthday1;
    private String birthday2;

    private Integer gid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(String birthday1) {
        this.birthday1 = birthday1;
    }

    public String getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(String birthday2) {
        this.birthday2 = birthday2;
    }



    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    /**
     * 在这里，建议大家直接对部门进行初始化，
     * 区别：不初始化，要是用户没有部门的时候，deptBean对象为空  null，
     * 在前台页面我们展示的时候，u.deptBean.dname,在部分前端语言里面，会报错
     * 不初始化，u.deptBean = null
     */
    private DeptBean deptBean = new DeptBean();

    private RoleBean roleBean = new RoleBean();

    public DeptBean getDeptBean() {
        return deptBean;
    }

    public void setDeptBean(DeptBean deptBean) {
        this.deptBean = deptBean;
    }

    public RoleBean getRoleBean() {
        return roleBean;
    }

    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }



    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
