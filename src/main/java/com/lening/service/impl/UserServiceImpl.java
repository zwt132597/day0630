package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.*;
import com.lening.mapper.UserMapper;
import com.lening.service.UserService;
import com.lening.utils.JieXiXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<UserBean> findAll(Integer pageNum, Integer pageSize, UserBean userBean) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserBean> all = userMapper.findAll(userBean);
        PageInfo<UserBean> pageInfo = new PageInfo<UserBean>(all);
        return pageInfo;
    }

    @Override
    public List<PowerBean> getPowerList() {
        return userMapper.getPowerList();
    }

    @Override
    public UserBean getUserById(Integer id) {
        UserBean userById = userMapper.getUserById(id);
        return userById;
    }

    @Override
    public List<DeptBean> getDeptList() {
        List<DeptBean> deptList = userMapper.getDeptList();
        return deptList;
    }

    @Override
    public List<RoleBean> getRoleListByDeptid(UserBean userBean) {
        if (userBean != null) {
            if (userBean.getDeptBean() != null && userBean.getRoleBean().getRid() != null) {
                List roleListByDeptid = userMapper.getRoleListByDeptid(userBean.getDeptBean().getDeptid());
                return roleListByDeptid;
            }

        }
        return null;
    }

    @Override
    public List<RoleBean> getRoleListByDeptid2(Integer deptid) {
        List roleListByDeptid = userMapper.getRoleListByDeptid(deptid);
        return roleListByDeptid;
    }

    @Override
    public void saveUserDeptRole(Integer id, Integer deptid, Integer rid) {
        userMapper.saveUserDeptRole(id, deptid, rid);
    }

    @Override
    public void deleteUserByid(UserBean userBean) {
        userMapper.deleteUserByid(userBean.getId());
    }

    @Override
    public void saveUser(UserBean userBean) {
        userMapper.saveUser(userBean);
    }

    @Override
    public void updateUser(UserBean userBean) {
        userMapper.updateUser(userBean);
    }

    @Override
    public UserBean getLogin(UserBean userBean) {
        if (userBean != null) {
            if (userBean.getUsername() != null && !"".equals(userBean.getUsername())) {
                /**
                 * 虽然你在实际生产不可以出现，一个用户名查询出来两条数据的可能性
                 * 但是测试有可能，所以，我们写的全面点，返回为list，判断list大小，是1就OK啦，在判断密码，否则登录失败
                 */
                List<UserBean> login = userMapper.getLogin(userBean.getUsername());
                if (login.size() == 1 && login != null) {
                    UserBean ub = login.get(0);
                    if (ub.getPassword().equals(userBean.getPassword())) {
                        return ub;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<PowerBean> getPowerListById(Integer id) {
        return userMapper.getPowerListById(id);
    }

    @Override
    public QueryVo jieXiStr1(String str1) {
        return JieXiXml.jieXiStr1(str1);
    }

    @Override
    public String jieXiStr2(String str2) {
        return JieXiXml.jieXiStr2(str2);
    }

    @Override
    public String getInfo(QueryVo vo) {
        /**
         * 我们发现香烟和酒水只有一个字段不一样。
         * 我我们可以把这两个试题合并，只适合我们这项目，实际中很少
         */
        RsBean rs = null;
        if ("01".equals(vo.getCode())) {
            /**
             * 要是code是01去查询香烟，到香烟的数据库里面去查询
             */
            rs = userMapper.getRsSmoke(vo.getCardno());
        } else if ("02".equals(vo.getCode())) {
            /**
             * 要是code是02去查询酒水，到酒水的数据库里面去查询
             */
            rs = userMapper.getRsWine(vo.getCardno());
        }
        String str = JieXiXml.pinRsStr(vo.getCode(), rs);
        if (str != null) {
            return str;
        }

        return null;
    }

    @Override
    public String saveData(QueryVo vo, String str2) {
        /**
         * 01是香烟
         */
        if ("01".equals(vo.getCode())) {
            /**
             * 这个str2目前没有解析的方法，解析里面的解析str2方法是解析查询时候的str2，就一个商品编号cardno
             */
            SmokeBean smokeBean = JieXiXml.jieXiStr2Smoke(str2);
            /**
             *解析出来进行判断保存
             */
            /**
             * 判断
             */
            if (smokeBean == null) {
                return "<MEG><CODE>0</CODE><CONTENT>参数2解析失败</CONTENT></MEG>";
            } else {
                try {
                    /**
                     * 保存成功
                     */
                    userMapper.saveSmoke(smokeBean);

                    return "<MEG><CODE>1</CODE><CONTENT>保存成功了</CONTENT></MEG>";
                } catch (Exception e) {
                    return "<MEG><CODE>0</CODE><CONTENT>数据保存失败</CONTENT></MEG>";
                }
            }

        } else if ("02".equals(vo.getCode())) {
            /**
             * 02是酒水
             */
            WineBean wineBean = JieXiXml.jieXiStr2Wine(str2);
            if (wineBean == null) {
                return "<MEG><CODE>0</CODE><CONTENT>参数2解析失败</CONTENT></MEG>";
            } else {
                try {
                    /**
                     * 保存成功
                     */
                    userMapper.saveWine(wineBean);
                    return "<MEG><CODE>1</CODE><CONTENT>保存成功了</CONTENT></MEG>";
                } catch (Exception e) {
                    return "<MEG><CODE>0</CODE><CONTENT>数据保存失败</CONTENT></MEG>";
                }

            }
        }
        return null;
    }

    @Override
    public void saveStuQj2(ProcessBean pb) {

        pb.setQjstatus(0);
        userMapper.insertPorcess(pb);
        /**
         * 把假条保存好了，把保存后假条流程id来回来了
         * 接下来要保存流程明细（这个假条都需要谁来审核）
         * 需要给流程明细里面插入数据了，首先需要查询出这个学生的班级信息，
         * 最起码需要查询出讲师和班主任
         */
        UserBean ub = userMapper.getUserById(pb.getSid());
        if (ub != null && ub.getGid() != null) {

            /**
             * 需要去查询班级
             */
            GradeBean gb = userMapper.getGradeByGid(ub.getGid());
            /**
             * 班级里面就有讲师和辅导员了，
             * 要是这个班级没有讲师或者辅导员，这个学生的假条怎么提交，直接提给主任的，主任需要把讲师和班主任的都审核了
             * 钉钉就是这样子的，要是没有讲师的都是主任审核，然后辅导员审核，再到主任，因为1911这个班级
             */
            /**
             * 万一你的流程里面找不到下一个审核者，你们当时怎么处理的，直接递交给下一个审核者的领导。钉钉就是干的
             */
            PmxBean pmxBean = new PmxBean();
            /**
             * 流程id
             */
            pmxBean.setPid(pb.getId());
            /**
             * 处理人的id
             */
            pmxBean.setUserid(gb.getTid());
            /**
             * 第一个肯定要给讲师-辅导员-主任-院长
             * 流程明细里面出现两个状态码，
             * status这个表示流程处理的意见状态：0表示 正在审核中，1表示审核通过，2表示审核未通过
             * pstatus:这个是流程处理的状态码： 0没有没到我这里，  1表示该我处理了，2表示我已经处理过了
             * 需要判断流程有没有结束：需要判断的状态：处理顺序   1234
             */
            /**
             * status是是意见状态，审核的时候，才会出现
             * 设置的是  psatus
             */
            pmxBean.setStatus(0);
            pmxBean.setPstatus(1);
            /**
             * 这个是处理的顺序，讲师1
             */
            pmxBean.setPshunxu(1);
            /**
             * 保存讲师的处理流程明细
             */
            userMapper.insertProcessPmx(pmxBean);

            pmxBean.setPshunxu(2);
            pmxBean.setPstatus(0);
            pmxBean.setUserid(gb.getFid());

            userMapper.insertProcessPmx(pmxBean);

            if (pb.getQjtime() > 1) {
                /**
                 * 在这个里面没有必要在写  >1 && <=3，给明细表中，主任加一个明细
                 */
                pmxBean.setPshunxu(3);
                /**
                 * 目前我们给班级没有设置主任和院长，所以这两个写死，可以在班级里面增加主任和院长
                 */
                pmxBean.setUserid(1);
                userMapper.insertProcessPmx(pmxBean);
            }
            if (pb.getQjtime() > 3) {
                /**
                 * 给院长一个明细
                 */
                pmxBean.setPshunxu(4);
                /**
                 * 目前我们给班级没有设置主任和院长，所以这两个写死，可以在班级里面增加主任和院长
                 */
                pmxBean.setUserid(2);
                userMapper.insertProcessPmx(pmxBean);
            }
        }
    }

    @Override
    public List<QjVo> getStuQjListBySid(Integer sid) {
        List<QjVo> list = userMapper.getStuQjListBySid(sid);
        for (QjVo vo : list) {
            Integer qjstatus = vo.getQjstatus();
            Integer userid = 0;
            if (qjstatus == 0) {
                /**
                 * 正在审核中
                 */
                vo.setStatusStr("正在审核中");
                /**
                 * 需要查询目前谁正在审核，使用流程id去查询，去明细表中查询pstatus等于1的，就是正在审核
                 * 能查出来，userid
                 */
                userid = userMapper.getUserIdByPid(vo.getId());
            } else if (qjstatus == 1) {
                /**
                 * 表示审核通过
                 */
                vo.setStatusStr("审核通过");
                /**
                 * 审核通过了，肯定是最后一个人审核的，也就是审核吗最大的那个人，
                 * select userid from t_pmx where pshunxu = (select max(pshunxu) from t_pmx where pid = 11) and pid=11
                 */
                userid = userMapper.getUserIdByPidMaxShunxu(vo.getId());

            }else{
                /**
                 * 审核没通过
                 */
                vo.setStatusStr("审核未通过");
                /**
                 * 审核未通过，去好谁审核没有通过，找审核吗是2的
                 * 审核未通过
                 */
                userid = userMapper.getUserIdByPidNopass(vo.getId());
            }
            QjVo vvo = userMapper.getUnameAndRnameById(userid);
            vo.setUname(vvo.getUname());
            vo.setRname(vvo.getRname());
        }
        return list;
    }

    @Override
    public List<QjVo> getQjshListByUserid(Integer id) {
        /**
         * 去查询有没有需要我审核的流程id的集合
         */
        List<Integer> pids = userMapper.getPidsByUserid(id);
        List<QjVo> list = null;
        if(pids!=null&&pids.size()>=1){
            list = new ArrayList<QjVo>();
            for (Integer pid : pids) {
                /**
                 * 先按照流程id去查询流程表里面有的信息
                 */
                QjVo vo = userMapper.getProcessById(pid);
                /**
                 * 查询出来的vo中，只有学生的id，没有学生名字和班级的名字
                 * 又要去查询这个学生的另外两个字段
                 */
                QjVo voo = userMapper.getStuInfoBySid(vo.getSid());
                vo.setUname(voo.getUname());
                vo.setGname(voo.getGname());
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public void saveWdsh(Integer pid, Integer shstatus, Integer userid) {
        /**
         * 保存我的审核
         */
        if(shstatus==1){
            /**
             * 审核通过了，
             *  需要判断我的审核是不是最后一个，要是最后一个，就要把流程表中的状态改成  改成 审核成功，然后把流程明细里面状态改掉
             *  要是不是最后一个，把自己的流程明细里面的状态改掉，改成已处理，然后把自己审核的状态该进去，然后把流程交给下一个人
             *  怎么交给，把pstatus改成1，怎么样判断下一个人   pshunxu +1,
             *  怎么样判断自己是不是最后流程审核的最后一人，把流程审核最后一人的pshun  max（pshunxu），查出来，和自己pshunxu比较
             */

            /**
             * 接下来，我们需要使用 流程id和userid去明细表中查询，   查询目前我审核的这个流程的详细信息
             * 在查询，目前审核的这个这个整合流程的最大  顺序，是不是最后一个
             * vo里面没有顺序，那就不要用vo了，字节用一个数字表示方便
             */
            Integer pshunxu = userMapper.getQjMxInfo(pid,userid);
            Integer maxpshunxu = userMapper.getMaxPshunxu(pid);
            /**
             * 他俩要是相等，就是最后一步了，要是不相等，表示不是最后一步审核
             */
            if(pshunxu==maxpshunxu){
                userMapper.updateProcessStatus(pid,shstatus);
            }else {
                /**
                 * 不通过的话，把流程明细改成一下，然后把任务交给下一个
                 */
                /**
                 * 流程交给下一步，因为有自己的流程顺序码，+1，就是下一个人
                 */
                userMapper.updatePmxShunxu(pid,pshunxu+1);
            }
            userMapper.updatePmxStatus(pid,userid,shstatus);
        }else{

            /**
             * 审核不通过，直接把流程该更流程明细和流程就OK啦，直接结束流程
             * 直接改成两张表就ok
             * 要是想写方法复用，那么就不要在xml中把状态写死，传递给xml
             */
            userMapper.updateProcessStatus(pid,shstatus);
            userMapper.updatePmxStatus(pid,userid,shstatus);
        }
    }
}
