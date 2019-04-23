package cn.lc.action.admin;

import cn.lc.pojo.Doctype;
import cn.lc.pojo.Tasktype;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import cn.lc.service.admin.ITasktypeServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-17 12:50
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "tasktype.list",location = "/pages/jsp/admin/tasktypeList.jsp")})
@Action("TasktypeActionAdmin")
public class TasktypeActionAdmin extends AbstractAction {

    private static String updateRule = "tasktype.title:string";

    @Resource
    private ITasktypeServiceAdmin tasktypeServiceAdmin;

    private Tasktype tasktype = new Tasktype();

    public Tasktype getTasktype() {
        return tasktype;
    }

    public String list(){
        try{
            List<Tasktype> list = this.tasktypeServiceAdmin.list();
            super.getRequest().setAttribute("all",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "tasktype.list";
    }

    public void update(){
        try {
            super.print(this.tasktypeServiceAdmin.update(this.tasktype));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String insert(){
        try {
            if (this.tasktypeServiceAdmin.insert(this.tasktype)) {
                super.setMsgAndUrl("insert.success.msg", "tasktype.list.action");
            } else {
                super.setMsgAndUrl("insert.failure.msg", "tasktype.list.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
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
        return "任务类型";
    }
}
