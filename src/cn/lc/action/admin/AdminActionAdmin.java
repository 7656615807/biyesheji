package cn.lc.action.admin;

import cn.lc.pojo.Role;
import cn.lc.pojo.User;
import cn.lc.service.admin.IAdminServiceAdmin;
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
        @Result(name = "admin.list",location = "/pages/jsp/admin/admin_admin_list.jsp"),
        @Result(name = "admin.insert",location = "/pages/jsp/admin/admin_admin_insert.jsp"),
        @Result(name = "admin.show",location = "/pages/jsp/admin/admin_admin_show.jsp"),
        @Result(name = "updateVF",location = "/AdminActionAdmin!list.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/AdminActionAdmin!insert.action",type = "redirectAction"),
})
@Namespace(value="/")
@Action("AdminActionAdmin")
public class AdminActionAdmin extends AbstractAction {
    public String insertRule = "user.userid:string|user.password:string|user.name:string|user.phone:phone|user.email:string|role.rid:int";
    public String updateRule = "user.userid:string|user.password:string|user.name:string|user.phone:phone|user.email:string|role.rid:int";

    @Resource
    private IAdminServiceAdmin adminServiceAdmin;

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
            if (this.adminServiceAdmin.insert(user,admin.getUserid())){
               super.setMsgAndUrl("insert.success.msg","admin.insert.action");
            }else {
                super.setMsgAndUrl("insert.failure.msg","admin.insert.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }
    public void checkUserid(){
        try {
            super.print(this.adminServiceAdmin.checkUser(this.user.getUserid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String insertPre(){
        try {
            Map<String, Object> map = this.adminServiceAdmin.insertPre();
            super.getRequest().setAttribute("all",map.get("allRoles"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin.insert";
    }

    public String delete(){
        Set<String> set = new HashSet<>();
        String result[] = super.getIds().split("\\,");
        for (int x = 0; x < result.length; x ++ ){
            set.add(result[x]);
        }
        try {
            if (this.adminServiceAdmin.delete(set)) {
                super.setMsgAndUrl("delete.success.msg", "admin.list.action");
            } else {
                super.setMsgAndUrl("delete.failure.msg", "admin.list.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }
    public void updatePre(){
        try{
            Map<String, Object> map = this.adminServiceAdmin.updatePre(this.user.getUserid());
            User user = (User) map.get("user");
            List<Role> roles = (List<Role>) map.get("allAdminRoles");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid",user.getUserid());
            jsonObject.put("name",user.getName());
            jsonObject.put("phone",user.getPhone());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("rid",user.getRole().getRid());
            JSONArray array = new JSONArray();
            for (Role role : roles){
                JSONObject temp = new JSONObject();
                temp.put("rid",role.getRid());
                temp.put("title",role.getTitle());
                array.put(temp);
            }
            jsonObject.put("roles",array);
            super.print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateRole(){
        try {
            this.user.setRole(this.role);
            super.print(this.adminServiceAdmin.updateRole(this.user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(){
        try {
            this.user.setPassword(DigestUtils.md5DigestAsHex(this.user.getPassword().getBytes()));
            super.print(this.adminServiceAdmin.updatePassword(this.user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateInfo(){
        this.user.setRole(this.role);
        try{
            super.print(this.adminServiceAdmin.update(this.user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String list(){
        try{
            Map<String, Object> map = this.adminServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("allcounts"),"admin.role.split.url",null,null);
            super.getRequest().setAttribute("all",map.get("allAdmins"));
            super.getRequest().setAttribute("allRoles",map.get("allRoles"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin.list";
    }

    public String show(){
        try{
            User user = this.adminServiceAdmin.show(this.user);
            super.getRequest().setAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin.show";
    }

    @Override
    public String getDefaultColumn() {
        return "userid";
    }

    @Override
    public String getColumnData() {
        return "用户id:userid|真实id:name|电话号码:phone|email:email";
    }

    @Override
    public String getTypeName() {
        return "管理员";
    }
}
