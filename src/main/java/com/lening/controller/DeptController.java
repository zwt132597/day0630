package com.lening.controller;

import com.lening.entity.DeptBean;
import com.lening.entity.RoleBean;
import com.lening.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创作时间：2020/4/9 16:26
 * 作者：李增强
 */
@Controller
public class DeptController {
    @Resource
    private DeptService deptService;

    @RequestMapping("/getDeptList")
    public String getDeptList(Model model){
        List<DeptBean> list = deptService.getDeltList();
        model.addAttribute("list", list);
        return "dept_list";
    }

    @RequestMapping("/toDeptRole")
    public String toDeptRole(Integer deptid,Model model){
        DeptBean deptByDeptid = deptService.getDeptByDeptid(deptid);
        List<RoleBean> roleList = deptService.getRoleList();
        List<Integer> integers = deptService.RidsByDeptid(deptid);
        model.addAttribute("db", deptByDeptid);
        model.addAttribute("list", roleList);
        model.addAttribute("rlist", integers);
        return "dept_role";
    }

    @RequestMapping("/saveDeptRole")
    public String saveDeptRole(Integer deptid,Integer[] rids){
        deptService.saveDeptRole(deptid,rids);
        return "redirect:getDeptList.do";
    }
}
