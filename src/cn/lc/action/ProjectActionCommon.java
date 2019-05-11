package cn.lc.action;

import cn.lc.pojo.Project;
import cn.lc.pojo.Task;
import cn.lc.pojo.Tasktype;
import cn.lc.pojo.User;
import cn.lc.service.admin.IProjectServiceAdmin;
import cn.lc.service.manager.ITaskServiceManager;
import cn.lc.util.action.AbstractAction;
import cn.lc.util.util.JsonOperUtil;
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
 * @date: 2019-05-11 20:31
 **/
@Repository
@InterceptorRef("loginStack")
@ParentPackage("root")
@Namespace(value="/")
@Action("ProjectActionCommon")
public class ProjectActionCommon extends AbstractAction {
    private User user = new User();

    public User getUser() {
        return user;
    }

    private Task task = new Task();

    public Task getTask() {
        return task;
    }
    private Tasktype taskType = new Tasktype();

    public Tasktype getTasktype() {
        return taskType;
    }

    private Project project = new Project();

    public Project getProject() {
        return project;
    }


    @Resource
    private ITaskServiceManager taskServiceManager;
    @Resource
    private IProjectServiceAdmin projectServiceAdmin;

    public void showProject(){
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

    public void showTask(){
        try {
            Task task = this.taskServiceManager.show(this.task.getTid());
            Project project =new Project();
            if (task.getProject() != null)
                project.setTitle(task.getProject().getTitle());
            Tasktype tasktype = new Tasktype();
            if (task.getTasktype() != null)
                tasktype.setTitle(task.getTasktype().getTitle());
            User userByCreator = new User();
            if (task.getUserByCreator() != null)
                userByCreator.setName(task.getUserByCreator().getName());
            User userByCancle = new User();
            if (task.getUserByCanceler() != null)
                userByCancle.setName(task.getUserByCanceler().getName());
            User userByReciiver = new User();
            if (task.getUserByReceiver() != null)
                userByReciiver.setName(task.getUserByReceiver().getName());
            Task task1 = (Task) task.clone();
            task1.setProject(project);
            task1.setTasktype(tasktype);
            task1.setUserByReceiver(userByReciiver);
            task1.setUserByCreator(userByCreator);
            task1.setUserByCanceler(userByCancle);
            String usesdf = JsonOperUtil.str(task1);
            System.out.println(usesdf);
            JSONObject jsonObject = new JSONObject(usesdf);
            super.print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
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
        return null;
    }
}
