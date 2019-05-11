package cn.lc.action.emp;

import cn.lc.pojo.User;
import cn.lc.service.admin.IUserServiceAdmin;
import cn.lc.service.emp.IIndexServiceEmp;
import cn.lc.util.action.AbstractAction;
import cn.lc.util.util.JsonOperUtil;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-30 17:30
 **/
@Repository
@InterceptorRef("empStack")
@ParentPackage("root")
@Results(value = {
        @Result(name = "user.show",location = "/pages/jsp/emp/emp_user_show.jsp"),
})
@Namespace(value="/")
@Action("UserActionEmp")
public class UserActionEmp extends AbstractAction {

    @Resource
    private IUserServiceAdmin userServiceAdmin;
    @Resource
    private IIndexServiceEmp indexServiceEmp;

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

    public void Num(){
        User emp = (User) super.getSession().getAttribute("emp");
        try{
            Map<String, Integer> map = this.indexServiceEmp.unReadCount(emp.getUserid());
            super.print(JsonOperUtil.str(map));
        }catch (Exception e){
            e.printStackTrace();
        }
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
