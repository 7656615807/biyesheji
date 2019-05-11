package cn.lc.action.admin;

import cn.lc.pojo.Role;
import cn.lc.pojo.User;
import cn.lc.service.admin.IAdminServiceAdmin;
import cn.lc.service.admin.IUserServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-30 17:30
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Results(value = {
        @Result(name = "user.listActive",location = "/pages/jsp/admin/admin_user_listActive.jsp"),
        @Result(name = "user.listLock",location = "/pages/jsp/admin/admin_user_listLock.jsp"),
        @Result(name = "user.insert",location = "/pages/jsp/admin/admin_user_insert.jsp"),
        @Result(name = "user.show",location = "/pages/jsp/admin/admin_user_show.jsp"),
        @Result(name = "updateVF",location = "/AdminActionAdmin!listLock.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/pages/jsp/admin/admin_user_insert.jsp"),
})
@Namespace(value="/")
@Action("UserActionAdmin")
public class UserActionAdmin extends AbstractAction {
    public String insertRule = "user.userid:string|user.password:string|user.name:string|user.phone:phone|user.email:string|role.rid:int|user.level:int";
    public String updateRule = "user.userid:string|user.password:string|user.name:string|user.phone:phone|user.email:string|role.rid:int|user.level:int";

    @Resource
    private IUserServiceAdmin userServiceAdmin;

    private User user = new User();

    public User getUser() {
        return user;
    }

    private Role role = new Role();

    public Role getRole() {
        return role;
    }

    public String insert() {
        this.user.setPassword(DigestUtils.md5DigestAsHex(this.user.getPassword().getBytes()));
        this.user.setRole((this.role));
        User admin = (User)super.getSession().getAttribute("admin");
        try {
            if (this.userServiceAdmin.insert(user,admin.getUserid())){
               super.setMsgAndUrl("insert.success.msg","user.insert.action");
            }else {
                super.setMsgAndUrl("insert.failure.msg","user.insert.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }
    public void checkUserid(){
        try {
            super.print(this.userServiceAdmin.checkUser(this.user.getUserid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String batchLock(){
        Set<String> set = new HashSet<>();
        String result[] = super.getIds().split("\\,");
        for (int x = 0; x < result.length; x ++ ){
            set.add(result[x]);
        }
        try {
            if (this.userServiceAdmin.updateLock(set,1)) {
                super.setMsgAndUrl("lock.success.msg", "user.listActive.action");
            } else {
                super.setMsgAndUrl("lock.failure.msg", "user.listActive.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String batchActive(){
        Set<String> set = new HashSet<>();
        String result[] = super.getIds().split("\\,");
        for (int x = 0; x < result.length; x ++ ){
            set.add(result[x]);
        }
        try {
            if (this.userServiceAdmin.updateLock(set,0)) {
                super.setMsgAndUrl("active.success.msg", "user.listLock.action");
            } else {
                super.setMsgAndUrl("active.failure.msg", "user.listLock.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }

    public void updatePre(){
        try{
            Map<String, Object> map = this.userServiceAdmin.updatePre(this.user.getUserid());
            User user = (User) map.get("user");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid",user.getUserid());
            jsonObject.put("name",user.getName());
            jsonObject.put("phone",user.getPhone());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("level",user.getLevel());
            super.print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateRole(){
        try {
            this.user.setRole(this.role);
            super.print(this.userServiceAdmin.updateRole(this.user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(){
        try {
            this.user.setPassword(DigestUtils.md5DigestAsHex(this.user.getPassword().getBytes()));
            super.print(this.userServiceAdmin.updatePassword(this.user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateInfo(){
        try{
            super.print(this.userServiceAdmin.update(this.user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String listActive(){
        try{
            Map<String, Object> map = this.userServiceAdmin.list(0,super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("allcounts"),"user.listActive.action",null,null);
            super.getRequest().setAttribute("all",map.get("allAdmins"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user.listActive";
    }
    public String listLock(){
        try{
            Map<String, Object> map = this.userServiceAdmin.list(1,super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("allcounts"),"user.listLock.action",null,null);
            super.getRequest().setAttribute("all",map.get("allAdmins"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user.listLock";
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
