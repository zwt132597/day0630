package com.lening.controller;

import com.lening.entity.InfoBean;
import com.lening.entity.QueryVo;
import com.lening.entity.UserBean;
import com.lening.service.UserService;
import com.lening.utils.JieXiXml;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestDataController {
    @Resource
    private UserService userService;

    /**
     * 1、对应的是无参的get请求
     */
    @RequestMapping("/testGet1")
    public String testGet1(){
        System.out.println(123);
        return "123";
    }
    /**
     * 2、3、get有参的和2和3的测试
     */
    @RequestMapping("/testGet2")
    public String testGet2(String name,Integer age){
        return name+"兄弟你终于来了，你的年龄竟然是"+age+"岁了?";
    }

    /**
     * 4、对应的是无参的post请求
     */
    @RequestMapping(value = "/testPost1",method = RequestMethod.POST)
    public String testPost1(){
        System.out.println(123);
        return "123";
    }

    /**
     * 5、post的普通参数
     */
    @RequestMapping(value = "/testPost2",method = RequestMethod.POST)
    public String testPost2(String name,Integer age){
        return name+"兄弟你终于来了，你的年龄竟然是"+age+"岁了?";
    }
    /**
     * 5、post的对象参数
     */
    @RequestMapping("/testPost3")
    public String testPost3(@RequestBody InfoBean infoBean){
        return infoBean.toString();
    }

    /**
     * 6、post的对象参数
     */
    @RequestMapping("/testPost4")
    public InfoBean testPost4(@RequestBody InfoBean infoBean){
        infoBean.setUname(infoBean.getUname()+"===兄弟我看到你了");
        return infoBean;
    }
    /**
     * 7、post的对象参数
     */
    @RequestMapping("/testPost5")
    public InfoBean testPost5(@RequestBody InfoBean infoBean,String meaning,Integer flag){
        infoBean.setUname(infoBean.getUname()+"===兄弟我看到你了");
        System.out.println(meaning+flag);
        return infoBean;
    }

    @RequestMapping("/getDataInterface")
    public  String getDataInterface(String str1,String str2){
        /**
         * 这两个参数是对方传递过来的，模拟真实，在开发总部的时候，不要去考虑分部，按照文档来
         */
       /* str1="<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
        str2="<CONTENT><CARDNO>xy0003</CARDNO></CONTENT>";*/
        QueryVo vo = userService.jieXiStr1(str1);
        if(vo==null){
            /**
             * 解析失败，几乎不可能，第一次项目对接有可能，后续就不能了
             */
            return "<result><MEG><CODE>0</CODE></MEG></result>";
        }else{
            /**
             * 参数1解析成功
             */
            /**
             * 去登录
             */
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);
            if(ru==null){
                return "<result><MEG><CODE>1</CODE></MEG></result>";
            }else{
                /**
                 * 登录成功了，去解析参数2
                 */
                String cardno = userService.jieXiStr2(str2);
                if(cardno==null){
                    return "<result><MEG><CODE>0</CODE></MEG></result>";
                }else{
                    /**
                     * 都解析成功了，然后去查询，用户code和cardno去查询
                     */
                    vo.setCardno(cardno);
                    /**
                     * 查询返回，返回要是空，就没有查到，返回要是不是空，把返回的接口，拼成需要的mxl返回回去
                     * 总厂这边开始建立两个数据库，一个香烟库，一个酒水的库，对应的实体类等
                     */
                    String rs = userService.getInfo(vo);
                    if(rs==null){
                        /**
                         * 没有查询到
                         */
                        return "<result><MEG><CODE>2</CODE></MEG></result>";
                    }else{
                        return "<result><MEG><CODE>3</CODE></MEG>"+rs+"</result>";
                    }
                }
            }
        }
    }
    /**
     *2第二个接口了，接收分厂发来的数据的
     */
    @RequestMapping("/reciDataInterface")
    public String reciDataInterface(String str1,String str2){
    /**
    * 接收到分厂发过来的数据后，我们先解析第一个参数
     */
        QueryVo vo = JieXiXml.jieXiStr1(str1);
        /**
         * 判断vo为空，参数一解析失败
         */
        if(vo==null){
            return "<MEG><CODE>0</CODE><CONTENT>参数一解析失败</CONTENT></MEG>";
        }else{
            /**
             * 解析成功，判断登录，鉴权（判断有没有资格）
             */
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);
            if(ru==null){
                return "<MEG><CODE>0</CODE><CONTENT>用户名或者密码错误</CONTENT></MEG>";
            }else{
                /**
                 * 登录成功了，根据参数一解析出来的code去解析参数2，
                 * code不一样，参数二不一样
                 */
                return userService.saveData(vo,str2);
            }
        }
    }


}
