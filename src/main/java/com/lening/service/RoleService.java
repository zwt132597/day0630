package com.lening.service;

import com.lening.entity.PowerBean;
import com.lening.entity.RoleBean;

import java.util.List;

/**
 * 创作时间：2020/4/10 10:37
 * 作者：李增强
 */
public interface RoleService {
    List<RoleBean> getRoleList();

    RoleBean getRoleByRid(Integer rid);

    void saveRoleDept(Integer rid,Integer deptid);

    List<PowerBean> getRolePower(Integer rid);

    void insertRolePower(Integer rid, String ids);
}
