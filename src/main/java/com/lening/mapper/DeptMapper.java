package com.lening.mapper;

import com.lening.entity.DeptBean;
import com.lening.entity.RoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创作时间：2020/4/9 16:26
 * 作者：李增强
 */
public interface DeptMapper {
    List<DeptBean> getDeptList();

    List<Integer> RidsByDeptid(@Param("deptid") Integer deptid);

    DeptBean getDeptByDeptid(@Param("deptid") Integer deptid);

    List<RoleBean> getRoleList();

    void deleteRoleByDeptid(@Param("deptid") Integer deptid);

    void insertDeptRole(@Param("deptid") Integer deptid, @Param("rid") Integer rid);
}
