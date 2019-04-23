package cn.lc.action;

import cn.lc.pojo.User;
import cn.lc.service.common.IUserServiceCommon;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 15:03
 **/
@Repository
@ParentPackage("root")
@Namespace("/")
@Action("UserLogin")
@InterceptorRef(value = "loginStack")
@Results(value={
        @Result(name = "loginVF",location = "/login.jsp")
})
@SuppressWarnings("serial")
public class UserLoginActionCommon extends AbstractAction {
    private String loginRule="user.userid:string|user.password:string";

    @Resource
    private IUserServiceCommon userServiceCommon;
    private User user = new User();
    public User getUser(){
        return user;
    }

    /**
     * 用户登录，成功信息可以正常返回<br>
     * @return
     */
    public String login(){
        try {
            super.getSession().removeAttribute("admin");
            super.getSession().removeAttribute("admin");
            super.getSession().removeAttribute("emp");
            User resultUser = userServiceCommon.login(this.user.getUserid(),
                    DigestUtils.md5DigestAsHex(this.user.getPassword().getBytes()));
            if(resultUser !=null){
                if(resultUser.getLevel()==0||resultUser.getLevel()==1){
                    super.getSession().setAttribute("admin",resultUser);
                    super.setMsgAndUrl("user.login.success","admin.index.page");
                }
                else if(resultUser.getLevel()==2){
                    super.getSession().setAttribute("manager",resultUser);
                    super.setMsgAndUrl("user.login.success","manager.index.page");
                }
                else if(resultUser.getLevel()==3){
                    super.getSession().setAttribute("emp",resultUser);
                    super.setMsgAndUrl("user.login.success","emp.index.page");
                }
            }
            else {
                super.setMsgAndUrl("user.login.failure","login.page");
            }
        } catch (Exception e) {
            super.setMsgAndUrl("user.login.failure","login.page");
            e.printStackTrace();
        }
        return "forward.page";
    }

    @Override
    public String getDefaultColumn() {
        return null;
    }

    @Override
    public String getColumnData() {
        return null;
    }

    @Override
    public String getTypeName() {
        return "信息";
    }
}
