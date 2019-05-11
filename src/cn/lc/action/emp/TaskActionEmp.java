package cn.lc.action.emp;

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
@InterceptorRef("empStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "task.list",location = "/pages/jsp/emp/emp_task_list.jsp"),
        @Result(name = "task.listbyemp",location = "/pages/jsp/emp/emp_task_listByUser.jsp"),
        @Result(name = "task.insert",location = "/pages/jsp/manager/manager_task_insert.jsp"),
        @Result(name = "task.update",location = "/pages/jsp/manager/manager_task_update.jsp"),
        @Result(name = "updateVF",location = "/TaskActionManager!list.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/TaskActionManager!insertPre.action",type = "redirectAction")})
@Action("TaskActionEmp")
public class TaskActionEmp extends AbstractAction {
    public String updateRule = "task.note:string|task.expend:int";
    public String insertRule = "task.note:string|task.title:string|task.estimate:int";

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

    @Resource
    private ITaskServiceEmp taskServiceEmp;





    public void updateStart(){
        User emp = (User) super.getSession().getAttribute("emp");
        try {
            super.print(this.taskServiceEmp.editStatus(task.getTid(), emp.getUserid()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateFinish(){
        User emp = (User) super.getSession().getAttribute("emp");
        try {
            super.print(this.taskServiceEmp.editfinishTask(task, emp.getUserid()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String list(){
        try{
            Map<String, Object> map = this.taskServiceManager.list(project.getProid(),super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("count"),"project.list.action",null,null);
            super.getRequest().setAttribute("all",map.get("allTask"));
            super.getRequest().setAttribute("backUrl",this.backUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "task.list";
    }

    public String listbyEmp(){
        User emp = (User) super.getSession().getAttribute("emp");
        try{
            Map<String, Object> map = this.taskServiceEmp.listTaskByEmp(emp.getUserid(), super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("count"),"task.listbyemp.action",null,null);
            super.getRequest().setAttribute("all",map.get("allTask"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "task.listbyemp";
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
