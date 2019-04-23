package cn.lc.action.admin;

import cn.lc.pojo.Doctype;
import cn.lc.pojo.Document;
import cn.lc.pojo.Project;
import cn.lc.pojo.User;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import cn.lc.service.admin.IDocumentServiceAdmin;
import cn.lc.service.admin.IProjectServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-06 11:16
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "project.list",location = "/pages/jsp/admin/admin_project_list.jsp"),
        @Result(name = "project.insert",location = "/pages/jsp/admin/admin_project_insert.jsp"),
        @Result(name = "project.update",location = "/pages/jsp/admin/admin_project_update.jsp"),
        @Result(name = "updateVF",location = "/ProjectActionAdmin!list.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/ProjectActionAdmin!insertPre.action",type = "redirectAction")})
@Action("ProjectActionAdmin")
public class ProjectActionAdmin extends AbstractAction {
    public String updateRule = "project.title:string|";
    public String insertRule = "project.note:string|project.title:string";

    private User user = new User();

    public User getUser() {
        return user;
    }

    private Project project = new Project();

    public Project getProject() {
        return project;
    }

    @Resource
    private IProjectServiceAdmin projectServiceAdmin;

    public String insert(){
        User admin = (User) super.getSession().getAttribute("admin");
        this.project.setUserByCreid(admin);
        this.project.setUserByMgr(user);
        this.project.setName(this.user.getName());
        try {
            if (this.projectServiceAdmin.insert(project)) {
                super.setMsgAndUrl("insert.success.msg", "project.insertPre.action");
            }else {
                super.setMsgAndUrl("insert.failure.msg","project.insertPre.action");
            }
        }catch (Exception e){
            super.setMsgAndUrl("insert.failure.msg","project.insertPre.action");
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String insertPre(){
        try {
            Map<String, Object> map = this.projectServiceAdmin.insertPre();
            super.getRequest().setAttribute("all",map.get("allMangers"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "project.insert";
    }

    public void updatePre(){
        try {
            Map<String, Object> map = this.projectServiceAdmin.updatePre(project.getProid());
            Project project = (Project) map.get("project");
            List<User> manages = (List<User>) map.get("allMangers");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("proid",project.getProid());
            jsonObject.put("mgr",project.getUserByMgr().getUserid());
            jsonObject.put("title",project.getTitle());
            jsonObject.put("note",project.getNote());
            JSONArray array = new JSONArray();
            for (User user : manages){
                JSONObject temp = new JSONObject();
                temp.put("userid",user.getUserid());
                temp.put("name",user.getName());
                array.put(temp);
            }
            jsonObject.put("managers",array);
            super.print(jsonObject);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        User admin = (User) super.getSession().getAttribute("admin");
        this.project.setUserByCreid(admin);
        this.project.setUserByMgr(this.user);
        this.project.setName(this.user.getName());
        try {
            super.print(this.projectServiceAdmin.update(project));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String list(){
        try{
            Map<String, Object> map = this.projectServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("projectCount"),"admin.role.split.url",null,null);
            super.getRequest().setAttribute("all",map.get("allProjects"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "project.list";
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
        return "项目";
    }
}
