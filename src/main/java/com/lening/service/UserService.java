package com.lening.service;

import com.github.pagehelper.PageInfo;
import com.lening.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创作时间：2020/4/7 10:49
 * 作者：李增强
 */
public interface UserService {
    PageInfo<UserBean> findAll(Integer pageNum, Integer pageSize,UserBean userBean);

    List<PowerBean> getPowerList();

    UserBean getUserById(Integer id);

    List<DeptBean> getDeptList();

    List<RoleBean> getRoleListByDeptid(UserBean userBean);

    List<RoleBean> getRoleListByDeptid2(Integer deptid);

    void saveUserDeptRole(Integer id,Integer deptid,Integer rid);

    void deleteUserByid(UserBean userBean);

    void saveUser(UserBean userBean);

    void updateUser(UserBean userBean);

    UserBean getLogin(UserBean userBean);

    List<PowerBean> getPowerListById(Integer id);

    QueryVo jieXiStr1(String str1);

    String jieXiStr2(String str2);

    String getInfo(QueryVo vo);

    String saveData(QueryVo vo, String str2);

    void saveStuQj2(ProcessBean pb);

    List<QjVo> getStuQjListBySid(Integer id);

    List<QjVo> getQjshListByUserid(Integer id);

    void saveWdsh(Integer pid, Integer shstatus, Integer id);
}
