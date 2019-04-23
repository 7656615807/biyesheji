package cn.lc.action.admin;

import cn.lc.pojo.Groups;
import cn.lc.service.admin.IGroupServiceAdmin;
import cn.lc.util.action.AbstractAction;

import org.apache.struts2.convention.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-24 15:11
 **/
@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "groups.list",location = "/pages/jsp/admin/groupsList.jsp")})
@Action("GroupsActionAdmin")
public class GroupsActionAdmin extends AbstractAction {
    private static String updateRule = "groups.gid:int|groups.title:string|groups.note:string";
    private static String showRule = "groups.gid:int";
    @Resource
    private IGroupServiceAdmin groupServiceAdmin;

    private Groups groups = new Groups();

    public Groups getGroups() {
        return groups;
    }

    public String list(){
        try{
            Map<String, Object> map = this.groupServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("count"),"admin.action.split.url",null,null);
            super.getRequest().setAttribute("all",map.get("all"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "groups.list";
    }

    public void update(){
        try {
            super.print(this.groupServiceAdmin.update(this.groups));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(){
        try {
            Groups groups = this.groupServiceAdmin.show(this.groups.getGid());
            JSONObject json = new JSONObject();
            json.put("gid",groups.getGid());
            json.put("title",groups.getTitle());
            json.put("note",groups.getNote());
            JSONArray array = new JSONArray();
            for (cn.lc.pojo.Action action : groups.getActions()){
                JSONObject temp = new JSONObject();
                temp.put("actid",action.getActid());
                temp.put("title",action.getTitle());
                temp.put("url",action.getUrl());
                array.put(temp);
            }
            json.put("actions",array);
            super.print(json);
        }catch (Exception e){
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
        return "权限组";
    }
}
