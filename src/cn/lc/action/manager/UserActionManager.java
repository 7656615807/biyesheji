package cn.lc.action.manager;

import cn.lc.pojo.Role;
import cn.lc.pojo.User;
import cn.lc.service.admin.IUserServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-30 17:30
 **/
@Repository
@InterceptorRef("managerStack")
@ParentPackage("root")
@Results(value = {
        @Result(name = "user.show",location = "/pages/jsp/manager/manager_user_show.jsp"),
})
@Namespace(value="/")
@Action("UserActionManager")
public class UserActionManager extends AbstractAction {

    @Resource
    private IUserServiceAdmin userServiceAdmin;

    private User user = new User();

    public User getUser() {
        return user;
    }


    public String show(){
        try{
            User user0 = this.userServiceAdmin.show(this.user);
            super.getRequest().setAttribute("user",user0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user.show";
    }

    @Override
    public String getDefaultColumn() {
        return "userid";
    }

    @Override
    public String getColumnData() {
        return "用户id:userid|姓名:name|电话号码:phone|email:email";
    }

    @Override
    public String getTypeName() {
        return "用户";
    }
}
