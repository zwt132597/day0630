package com.lening.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lening.entity.*;
import com.lening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 创作时间：2020/4/7 10:48
 * 作者：周伟桐
 */
@Controller
public class UserController {

    /**
     * 这个注解虽然不是spring的但是他由Spring来管理啊
     */
    @Resource
    private UserService userService;

    /**
     * 保存我的审核
     */
    /**
     * 使用git的注释
     *
     * */
    @RequestMapping("/saveWdsh")
    public String saveWdsh(HttpServletRequest request,Integer pid,Integer shstatus){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        userService.saveWdsh(pid,shstatus,ub.getId());
        return "redirect:getQjShList.do";
    }


    /**
     * 1号员工是 乐柠主任 22号角色
     * 2号员工是 乐柠院长 21号角色
     *
     *
     * 乐柠讲师  23号角色
     * 乐柠的辅导员 24号角色
     * 乐柠的学生是 25号角色
     */

    /**
     * 我的审核 23 号权限 需要乐柠的讲师、辅导员、主任和院长
     * 还有把23的父亲也赋给  乐柠的这些角色、还有爷爷
     */

    /**
     * 管理登录上来查询我的审核
     */

    @RequestMapping("/getQjShList")
    public String getQjShList(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        /**
         * 先拿着我的id去流程明细表中查询一下，有没有需要我审核的流程，有的话，再去流程表中把流程查出来，
         * 和学生是相反的，学生是先查流程，再查明细，老师是先查明细，再查流程
         * select pid from t_pmx a where a.pstatus =1 and userid=37 其实我就需要pid，查询流程id，因为我的页面展示的时候，
         * 我需要知道这个是谁请假的，请了多次时间，什么时候开始的，那个班级的
         */

        List<QjVo> list = userService.getQjshListByUserid(ub.getId());
        model.addAttribute("list", list);
        return "qjsh_list";
    }


    /**
     * 这个注解虽然不是spring的但是他由Spring来管理啊
     * 400错误，说明类型对不上，日期类型不一样，需要日累类型转换
     * 直接使用实体类来接收，需不需要来转换
     * 因为我么带过来的日期有小时，还有日期需要处理
     * 在控制层处理，也可以在entity里面处理
     */
    @RequestMapping("/saveStuQj2")
    public String saveStuQj2(ProcessBean pb){
        /**
         * 接下来则么处理，开始走我们的业务流程，三个流程，属于业务型的代码，所以我们可以全部在sevice层搞定
         */

        userService.saveStuQj2(pb);
        return "redirect:getStuJtList.do";
    }

    /**
     *
     */
    @RequestMapping("/getStuJtList")
    public String getStuJtList(HttpServletRequest request,Model model){
        /**
         * 需要把这个学生的情况请查询到，然后再去查询这个学的请假情况，我们先直接去去页面，发起请假
         */
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");

        /**
         *   <th>编号</th>            流程表
         *   <th>请假时长</th>         流程表
         *   <th>请假时间</th>         流程表
         *   <th>开始时间</th>         流程表
         *   <th>结束时间</th>         流程表
         *   <th>角色</th>
         *   <th>操作者</th>
         *   <th>审批状态</th>
         */

        /**
         * 角色名称怎么出来，在流程表中，要查询这个流程有没有结束，
         * 要是结束了，我们需要去明细表中查询，是谁结束的这个流程
         *  通过的话，那就是顺序码最大的那个人的的用户id
         *  没有通过的话，我去查询谁的在明细表中，查询谁把我决绝的，操作的的id，查询他的审批码是2
         *  流程没有结束的情况下
         *  直接去明细表中，查询流程状态等于1的这个人就OK啦
         */

        /**
         * 我们写一个请假的vo然后需要什么数据就往里面扔里面就OK啦
         */

        /**
         * vo里面第一次只能查出来一部分字段，最好不要去联查，出来之后在判断查询
         */
        List<QjVo> list = userService.getStuQjListBySid(ub.getId());
        System.out.println(list);
        model.addAttribute("list", list);

        return "stuqj_list";
    }




    /**
     * 转发到学生请假的页面
     */
    @RequestMapping("/toStuQj")
    public String toStuQj(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        model.addAttribute("stu", ub);
        return "stu_qj";
    }


    @RequestMapping("/getLogin")
    public String getLogin(UserBean userBean, HttpServletRequest request){
        /**
         * 登录的返回，要是想明确告诉用户是用户名为空，密码错误等详细信息，那么就不能这样用，
         * 一般登录是告诉登录失败
         */
        UserBean ub = userService.getLogin(userBean);
        if(ub!=null){
            request.getSession().setAttribute("ub",ub);
            return "main";
        }
        return "../../index";
    }

    @RequestMapping("/getRlistJosn")
    @ResponseBody
    public List<RoleBean> getRlistJosn(Integer deptid){
        List<RoleBean> rlist = userService.getRoleListByDeptid2(deptid);
        /**
         * ajax回传值，可以只用response打回去，这是servlet的做法。
         * SpringMVC可以支持，直接打回去，需要自己转json，springMVC也可以转json
         */
        return rlist;
    }

   /* @RequestMapping("/findAll")
    @ResponseBody
    public void findAll(){
        List<UserBean> list =  userService.findAll();
        System.out.println(list);
    }*/

    /**
     * 去给员工分配部门和角色
     * 传过来的参数只有员工id
     * 需要的参数
     * 1、员工的全部信息，需要查询出员工已有的部门和角色（其实只需要部门和角色的id就OK啦，用来回显）
     * 2、全部部门列表
     * 3、角色列表（用户现在所在的部门的所有角色列表）
     */
    @RequestMapping("/toUserDeptRole")
    public String toUserDeptRole(Integer id,Model model){
        UserBean userById = userService.getUserById(id);
        List<DeptBean> deptList = userService.getDeptList();
        List<RoleBean> roleListByDeptid = userService.getRoleListByDeptid(userById);
        model.addAttribute("ub", userById);
        model.addAttribute("dlist", deptList);
        model.addAttribute("rlist", roleListByDeptid);
        return "user_deptrole";
    }

    @RequestMapping("/getUserList")
    public String getUserList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Model model, UserBean userBean){
        PageInfo<UserBean> userList = userService.findAll(pageNum, pageSize, userBean);
        List<UserBean> list = userList.getList();
        int pages = userList.getPages();
        long total = userList.getTotal();

        model.addAttribute("list", list);
        model.addAttribute("count", total);
        model.addAttribute("totalPage", pages);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "user_list";
    }

    @RequestMapping("/getPowerJson")
    public String getPowerJson(Model model,HttpServletRequest request){
        /**
         * 获取sesion的时候，可以传递boolean类型的参数，不写默认是true，
         * 要是有就返回原来的，要是没有重新创建
         * 要是设置成false，有的话，返回，没有的话，返回null
         */
        UserBean ub = (UserBean) request.getSession(true).getAttribute("ub");
        if(ub!=null){
        List<PowerBean> list = userService.getPowerListById(ub.getId());
            Set<String> urls = new HashSet<String>();
            for (int i = 0; i <list.size() ; i++) {
                PowerBean powerBean = list.get(i);
                if(powerBean.getUrl()!=null){
                    urls.add(powerBean.getUrl().trim());
                }
                if("是".equals(powerBean.getIsbutton())){
                    list.remove(powerBean);
                    i--;
                }
            }
            request.getSession().setAttribute("urls",urls);
        String json = JSON.toJSONString(list);
        model.addAttribute("json", json);
        }
        return "left";

    }
    @RequestMapping("/saveUserDeptRole")
    public String saveUserDeptRole(Integer id,Integer deptid,Integer rid){
        userService.saveUserDeptRole(id, deptid, rid);
        return "redirect:getUserList.do";
    }
    //删除用户
    @RequestMapping("/deleteUserByid")
    public  String deleteUserByid(UserBean userBean){
                userService.deleteUserByid(userBean);
         return "redirect:getUserList.do";
    }
    //添加用户
    @RequestMapping("/toinsertUser")
    public  String toinsertUser(Model model){
        List<DeptBean> deptList = userService.getDeptList();
        model.addAttribute("dlist", deptList);
        return "user_add";
    }
    @RequestMapping("/saveUser")
    public String saveUser(UserBean userBean){
        userService.saveUser(userBean);
        return "redirect:getUserList.do";
    }
    //修改
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Integer id,Model model){
        UserBean userById = userService.getUserById(id);
        List<DeptBean> deptList = userService.getDeptList();
        List<RoleBean> roleListByDeptid = userService.getRoleListByDeptid(userById);
        model.addAttribute("ub", userById);
        model.addAttribute("dlist", deptList);
        model.addAttribute("rlist", roleListByDeptid);
        return "user_update";
    }
    @RequestMapping("/updateUser")
    public  String updateUser(UserBean userBean){
        userService.updateUser(userBean);
        return "redirect:getUserList.do";
    }
    //工员权限
    @RequestMapping("/getPowerUserByid")
    public String getPowerUserByid(Integer id,Model model){
        List<PowerBean> powerListById = userService.getPowerListById(id);
        model.addAttribute("power",powerListById);
        return "user_power";

    }

}
