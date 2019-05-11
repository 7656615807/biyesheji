package cn.lc.action.manager;

import cn.lc.pojo.*;
import cn.lc.service.admin.IProjectServiceAdmin;
import cn.lc.service.manager.ITaskServiceManager;
import cn.lc.util.action.AbstractAction;
import cn.lc.util.util.JsonOperUtil;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.convention.annotation.Action;
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
@Results(value = {@Result(name = "task.list",location = "/pages/jsp/manager/manager_task_list.jsp"),
        @Result(name = "task.listbymgr",location = "/pages/jsp/manager/manager_task_listbymgr.jsp"),
        @Result(name = "task.insert",location = "/pages/jsp/manager/manager_task_insert.jsp"),
        @Result(name = "task.update",location = "/pages/jsp/manager/manager_task_update.jsp"),
        @Result(name = "updateVF",location = "/TaskActionManager!list.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/TaskActionManager!insertPre.action",type = "redirectAction")})
@Action("TaskActionManager")
public class TaskActionManager extends AbstractAction {
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

    public String insert(){
        User manager = (User) super.getSession().getAttribute("manager");
        this.task.setUserByCreator(manager);
        this.task.setProject(this.project);
        this.task.setUserByReceiver(this.user);
        this.task.setTasktype(this.taskType);
        try {
            if (this.taskServiceManager.insert(task, manager.getUserid())) {
                super.setMsgAndUrl("insert.success.msg", "task.insertPre.action","project.proid", this.project.getProid());
            }else {
                super.setMsgAndUrl("insert.failure.msg","task.insertPre.action", "project.proid", this.project.getProid());
            }
        }catch (Exception e){
            super.setMsgAndUrl("insert.failure.msg","task.insertPre.action","project.proid", this.project.getProid());
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String insertPre(){
        try {
            Map<String, Object> map = this.taskServiceManager.insertPre(project.getProid());
            super.getRequest().setAttribute("allEmp",map.get("allEmp"));
            super.getRequest().setAttribute("allTaskType",map.get("allTaskType"));
            super.getRequest().setAttribute("project",map.get("project"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "task.insert";
    }

    public void updatePre(){
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
//            jsonObject.put("proId",task.getProject().getProid());
//            jsonObject.put("proName",task.getProject().getNote());
//            jsonObject.put("tid",task.getTid());
//            jsonObject.put("title",task.getTitle());
//            jsonObject.put("note",task.getNote());
//            jsonObject.put("creatorId",task.getUserByCreator().getUserid());
//            jsonObject.put("creatorName",task.getUserByCreator().getName());
//            jsonObject.put("ttid",task.getTasktype().getTtid());
//            jsonObject.put("ttitle",task.getTasktype().getTitle());
//            jsonObject.put("expiredate",task.getExpiredate());
//            jsonObject.put("createdate",task.getCreatedate());
//            jsonObject.put("finishdate",task.getFinishdate());
//            jsonObject.put("status",task.getStatus());
//            jsonObject.put("lastupdatedate",task.getLastupdatedate());
//            jsonObject.put("priority",task.getPriority());
//            jsonObject.put("expend",task.getExpend());
//            jsonObject.put("estimate",task.getEstimate());
//            jsonObject.put("priority",task.getPriority());
//            jsonObject.put("expend",task.getExpend());
//            jsonObject.put("status",task.getStatus());
//            JSONArray array = new JSONArray();
//            jsonObject.put("managers",array);
            super.print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void update(){
        User manager = (User) super.getSession().getAttribute("manager");
        try {
            super.print(this.taskServiceManager.updateByMnager(task, manager.getUserid()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateCancel(){
        User manager = (User) super.getSession().getAttribute("manager");
        try {
            super.print(this.taskServiceManager.updateCancleTaskByManager(task, manager.getUserid()));
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

    public String listbymgr(){
        User manager = (User) super.getSession().getAttribute("manager");
        try{
            Map<String, Object> map = this.taskServiceManager.listFinshTaskByManager(manager.getUserid(), super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("count"),"task.listbymgr.action",null,null);
            super.getRequest().setAttribute("all",map.get("allTask"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "task.listbymgr";
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
