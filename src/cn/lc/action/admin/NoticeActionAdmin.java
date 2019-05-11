package cn.lc.action.admin;

import cn.lc.pojo.Doctype;
import cn.lc.pojo.Document;
import cn.lc.pojo.Notice;
import cn.lc.pojo.User;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import cn.lc.service.admin.IDocumentServiceAdmin;
import cn.lc.service.admin.INoticeServiceAdmin;
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
@Results(value = {@Result(name = "notice.list",location = "/pages/jsp/admin/admin_notice_list.jsp"),
        @Result(name = "notice.insert",location = "/pages/jsp/admin/admin_notice_insert.jsp"),
        @Result(name = "updateVF",location = "/NoticeActionAdmin!list.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/pages/jsp/admin/admin_notice_insert.jsp")})
@Action("NoticeActionAdmin")
public class NoticeActionAdmin extends AbstractAction {
    public String updateRule = "notice.snid:int|notice.title:string|notice.content:string|notice.level:int";
    public String insertRule = "notice.title:string|notice.content:string|notice.level:int";

    private Notice notice = new Notice();

    public Notice getNotice() {
        return notice;
    }

    @Resource
    private INoticeServiceAdmin noticeServiceAdmin;


    private String ids;

    @Override
    public void setIds(String ids) {
        this.ids = ids;
    }

    public String insert(){
        User admin = (User) super.getSession().getAttribute("admin");
        this.notice.setUser(admin);
        try {
            if (this.noticeServiceAdmin.insert(notice)) {
                super.setMsgAndUrl("insert.success.msg", "notice.insertPre.action");
            }else {
                super.setMsgAndUrl("insert.failure.msg","notice.insertPre.action");
            }
        }catch (Exception e){
            super.setMsgAndUrl("insert.failure.msg","notice.insertPre.action");
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String insertPre(){
        return "notice.insert";
    }

    public String delete(){
        Set<Integer> set = new HashSet<>();
        String result[] = this.ids.split("\\,");
        for (int x = 0; x < result.length; x ++ ){
            set.add(Integer.parseInt(result[x]));
        }
        try {
            if (this.noticeServiceAdmin.delete(set)) {
                super.setMsgAndUrl("delete.success.msg", "notice.list.action");
            } else {
                super.setMsgAndUrl("delete.failure.msg", "notice.list.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }

    public void updatePre(){
        try {
            Notice notice = this.noticeServiceAdmin.updatePre(this.notice.getSnid());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user",notice.getUser());
            jsonObject.put("title",notice.getTitle());
            jsonObject.put("content",notice.getContent());
            jsonObject.put("snid",notice.getSnid());
            jsonObject.put("pubDate",notice.getPubdate());
            jsonObject.put("level",notice.getLevel());
            super.print(jsonObject);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        try {
            User admin = (User) super.getSession().getAttribute("admin");
            if (admin !=null){
                this.notice.setUser(admin);
            }
            super.print(noticeServiceAdmin.update(this.notice));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateLevel(){
        try {
            super.print(noticeServiceAdmin.updateLevel(this.notice.getSnid(),this.notice.getLevel()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String list(){
        try{
            Map<String, Object> map = this.noticeServiceAdmin.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("noticeCount"),"notice.list.action",null,null);
            super.getRequest().setAttribute("all",map.get("allNotices"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "notice.list";
    }

    @Override
    public String getDefaultColumn() {
        return "title";
    }

    @Override
    public String getColumnData() {
        return "公告标题:title";
    }

    @Override
    public String getTypeName() {
        return "系统公告";
    }
}
