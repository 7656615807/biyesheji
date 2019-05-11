package cn.lc.action.manager;

import cn.lc.pojo.Project;
import cn.lc.pojo.User;
import cn.lc.service.admin.IProjectServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-06 11:16
 **/
@Repository
@InterceptorRef("managerStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "project.list",location = "/pages/jsp/manager/manager_project_list.jsp")})
@Action("ProjectActionManager")
public class ProjectActionManager extends AbstractAction {


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

    public void show(){
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

    public String list(){
        try{
            Map<String, Object> map = this.projectServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("projectCount"),"manager.project.list.action",null,null);
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
        return "项目标题:title";
    }

    @Override
    public String getTypeName() {
        return "项目";
    }
}
