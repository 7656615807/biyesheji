package cn.lc.action.admin;

import cn.lc.pojo.Project;
import cn.lc.pojo.Task;
import cn.lc.pojo.Tasktype;
import cn.lc.pojo.User;
import cn.lc.service.emp.ITaskServiceEmp;
import cn.lc.service.manager.ITaskServiceManager;
import cn.lc.util.action.AbstractAction;
import cn.lc.util.util.JsonOperUtil;
import org.apache.struts2.convention.annotation.*;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-06 11:16
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "task.list",location = "/pages/jsp/admin/admin_task_list.jsp")})
@Action("TaskActionAdmin")
public class TaskActionAdmin extends AbstractAction {

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

    private String backUrl;

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
    public String getBackUrl() {
        return backUrl;
    }

    @Resource
    private ITaskServiceManager taskServiceManager;


    public String list(){
        try{
            Map<String, Object> map = this.taskServiceManager.list(project.getProid(),super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("count"),"task.list.action",null,null);
            super.getRequest().setAttribute("all",map.get("allTask"));
            super.getRequest().setAttribute("backUrl",this.backUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "task.list";
    }

    @Override
    public String getDefaultColumn() {
        return "title";
    }

    @Override
    public String getColumnData() {
        return "任务标题:title";
    }

    @Override
    public String getTypeName() {
        return "任务";
    }
}
