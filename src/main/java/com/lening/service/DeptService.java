package com.lening.service;

import com.lening.entity.DeptBean;
import com.lening.entity.RoleBean;

import java.util.List;

/**
 * 创作时间：2020/4/9 16:27
 * 作者：李增强
 */
public interface DeptService {
    List<DeptBean> getDeltList();

    DeptBean getDeptByDeptid(Integer deptid);

    List<RoleBean> getRoleList();

    List<Integer> RidsByDeptid(Integer deptid);

    void saveDeptRole(Integer deptid, Integer[] rids);
}
