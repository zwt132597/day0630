package com.lening.mapper;

import com.lening.entity.PowerBean;
import com.lening.entity.RoleBean;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * 创作时间：2020/4/10 10:37
 * 作者：李增强
 */
public interface RoleMapper {
    List<RoleBean> getRoleList();

    RoleBean getRoleByRid(Integer rid);

    List<PowerBean> getPowerList();

    List<Integer> getIdsByRid(@Param("rid") Integer rid);

    void deleteRolePowerByRid(@Param("rid") Integer rid);

    void insertRolePower(@Param("rid") Integer rid, @Param("sid") String sid);


}
