package com.lening.service.impl;

import com.lening.entity.PowerBean;
import com.lening.entity.RoleBean;
import com.lening.mapper.DeptMapper;
import com.lening.mapper.RoleMapper;
import com.lening.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创作时间：2020/4/10 10:37
 * 作者：李增强
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<RoleBean> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public RoleBean getRoleByRid(Integer rid) {
        RoleBean roleByRid = roleMapper.getRoleByRid(rid);
        return roleByRid;
    }

    @Override
    public void saveRoleDept(Integer rid, Integer deptid) {
        deptMapper.insertDeptRole(deptid,rid);
    }

    @Override
    public List<PowerBean> getRolePower(Integer rid) {
        /**
         * 1、去把权利list全部查出来
         * 2、需要把该角色原来的权限全部查出来
         * 3、回显
         */
        List<PowerBean> powerList = roleMapper.getPowerList();
        List<Integer> ids = roleMapper.getIdsByRid(rid);
        /**
         * 两个集合需要遍历的时候，要是全部遍历先后无所谓，
         * 要是里面有条件的遍历，先遍历小的，在遍历大的，效率高
         * 举例：list有100条数据，ids有10条数据
         * 先遍历  list   遍历的次数  100 -1000之间
         * 先遍历 ids    遍历次数     10-1000之间
         */
        if(ids!=null&&ids.size()>-1){
            if(powerList!=null&&powerList.size()>-1){
                for(Integer id: ids){
                    for (PowerBean pb : powerList){
                            if(id.equals(pb.getId())){
                                pb.setChecked(true);
                                /**
                                 * 在这里加break是为了提高效率，要是不加，大小集合谁先遍历无所谓，都全部比遍历
                                 * 复选框回显，当时页面的jstl里面没有break，所以出现了笛卡尔迪
                                 */
                                break;
                            }
                    }
                }
            }
        }
        return powerList;
    }

    @Override
    public void insertRolePower(Integer rid, String ids) {
        /**
         * 先删除后新增
         */
            roleMapper.deleteRolePowerByRid(rid);
            if(ids!=null&&ids.length()>-1&&ids!=""){
                String[] split = ids.split(",");
                /**
                 * 可以强转成int，可以不转，mysql可以容错，就是可以把一个string类的int直接插进int类型里面
                 */
                for (String sid:split){
                    roleMapper.insertRolePower(rid,sid);
                }
            }

    }
}
