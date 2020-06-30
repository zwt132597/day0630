package com.lening.service.impl;

import com.lening.entity.DeptBean;
import com.lening.entity.RoleBean;
import com.lening.mapper.DeptMapper;
import com.lening.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创作时间：2020/4/9 16:27
 * 作者：李增强
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;


    @Override
    public List<DeptBean> getDeltList() {
        return deptMapper.getDeptList();
    }

    @Override
    public DeptBean getDeptByDeptid(Integer deptid) {
        return deptMapper.getDeptByDeptid(deptid);
    }

    @Override
    public List<RoleBean> getRoleList() {
        /**
         * 是去查询角色列表的，可以把角色的mapper注入进来，直接查询，
         * 自己查询也行，但是角色的里面肯定会用这个
         */
        return deptMapper.getRoleList();
    }

    /**
     * 也可以直接查询一个roleList没问题，也可以直接查询一个integer的list
     * @param deptid
     * @return
     */
    @Override
    public List<Integer> RidsByDeptid(Integer deptid) {
        return deptMapper.RidsByDeptid(deptid);
    }

    @Override
    public void saveDeptRole(Integer deptid, Integer[] rids) {
        /**
         * 把这个部门原来的rid全部清空，其实就是去Role里面把deptid=穿过来的这个deptid的删掉
         */
        if(deptid!=null){
            deptMapper.deleteRoleByDeptid(deptid);
            if(rids!=null&&rids.length>-1){
                for (Integer i:rids) {
                    deptMapper.insertDeptRole(deptid,i);
                }
            }
        }
    }
}
