package cn.lc.action.admin;

import cn.lc.service.admin.IActionServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-17 12:50
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "action.list",location = "/pages/jsp/admin/actionList.jsp")})
@Action("ActionActionAdmin")
public class ActionActionAdmin extends AbstractAction {

    private static String updateRole = "action.title:string|action.url:string";

    @Resource
    private IActionServiceAdmin actionServiceAdmin;

    private cn.lc.pojo.Action action = new cn.lc.pojo.Action();

    public cn.lc.pojo.Action getAction() {
        return action;
    }

    public String list(){
        try{
            Map<String, Object> map = this.actionServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("actionCount"),"admin.action.split.url",null,null);
            super.getRequest().setAttribute("allActions",map.get("allActions"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "action.list";
    }

    public void update(){
        try {
            super.print(this.actionServiceAdmin.update(this.action));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return "权限";
    }
}
