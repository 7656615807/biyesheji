package cn.lc.action.admin;

import cn.lc.pojo.Groups;
import cn.lc.pojo.Role;
import cn.lc.service.admin.IRoleServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.annotation.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-25 22:31
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Results(value = {
        @Result(name = "role.insert.page",location = "/pages/jsp/admin/admin_role_insert.jsp"),
        @Result(name = "role.list",location = "/pages/jsp/admin/admin_role_list.jsp"),
})
@Namespace(value="/")
@Action("RoleActionAdmin")
public class RoleActionAdmin extends AbstractAction {
    private static String insertRule = "role.title:string";
    private static String updateRole = "role.rid:int|role.title:string";

    private Role role = new Role();
    private Integer[] gids;
    private String ugid;
    @Resource
    private IRoleServiceAdmin roleServiceAdmin;
    public void checkTitleInsert(){
        try{
            super.print(this.roleServiceAdmin.checkTitle(this.role.getTitle()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void checkTitleUpdate(){
        try{
            super.print(this.roleServiceAdmin.checkTitle(this.role.getTitle(),this.role.getRid()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String insert() {
        Set<Groups> set = new HashSet<>();
        for (int x = 0; x < this.gids.length; x++) {
            Groups gup = new Groups();
            gup.setGid(this.gids[x]);
            set.add(gup);
        }
        this.role.setGroupses(set);
        try {
            if (this.roleServiceAdmin.insert(this.role)) {
                super.setMsgAndUrl("insert.success.msg", "admin.role.insert.action");
            } else {
                super.setMsgAndUrl("insert.failure.msg", "admin.role.insert.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String insertPre(){
        try {
            Map<String, Object> map = this.roleServiceAdmin.insertPre();
            super.getRequest().setAttribute("all",map.get("all"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "role.insert.page";
    }
    public void updatePre(){
        try{
            Map<String, Object> map = this.roleServiceAdmin.updatePre(this.role.getRid());
            Role role = (Role)map.get("role");
            Map<Integer,Boolean> rgids = (Map<Integer,Boolean>)map.get("gids");
            List<Groups> gups = (List<Groups>) map.get("all");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rid",role.getRid());
            jsonObject.put("title",role.getTitle());
            jsonObject.put("note",role.getNote());
            JSONArray array = new JSONArray();
            for (Groups groups : gups){
                JSONObject temp = new JSONObject();
                temp.put("gid",groups.getGid());
                temp.put("title",groups.getTitle());
                if (rgids.get(groups.getGid()) != null){
                    temp.put("ckd","checked");
                }else {
                    temp.put("ckd","");
                }
                array.put(temp);
            }
            jsonObject.put("groups",array);
            super.print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void update(){
        Set<Groups> groupsSet = new HashSet<>();
        if (this.ugid != null){
            String result[] = this.ugid.split("\\,");
            for (int x = 0; x < result.length; x ++ ){
                Groups groups = new Groups();
                groups.setGid(Integer.parseInt(result[x]));
                groupsSet.add(groups);
            }
        }
        this.role.setGroupses(groupsSet);
        try{
            super.print(this.roleServiceAdmin.update(this.role));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String list(){
        try{
            Map<String, Object> map = this.roleServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("roleCount"),"admin.role.split.url",null,null);
            super.getRequest().setAttribute("all",map.get("allRoles"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "role.list";
    }
    public void setGids(Integer gids []){
        this.gids = gids;
    }
    public Role getRole(){
        return role;
    }
    public void setUgid(String ugid){
        this.ugid = ugid;
    }

    @Override
    public String getDefaultColumn() {
        return "title";
    }

    @Override
    public String getColumnData() {
        return "title";
    }

    @Override
    public String getTypeName() {
        return "角色";
    }
}
