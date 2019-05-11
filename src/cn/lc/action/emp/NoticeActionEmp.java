package cn.lc.action.emp;

import cn.lc.pojo.Notice;
import cn.lc.pojo.NoticeDto;
import cn.lc.pojo.User;
import cn.lc.service.emp.INoticeServiceEmp;
import cn.lc.service.manager.INoticeServiceManager;
import cn.lc.util.action.AbstractAction;
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
@Results(value = {@Result(name = "notice.list",location = "/pages/jsp/emp/emp_notice_list.jsp")})
@Action("NoticeActionEmp")
public class NoticeActionEmp extends AbstractAction {

    private Notice notice = new Notice();

    public Notice getNotice() {
        return notice;
    }

    @Resource
    private INoticeServiceEmp noticeServiceEmp;



    public void show(){
        User user = (User) super.getSession().getAttribute("emp");
        if (user == null)return;
        try {
            NoticeDto noticeDto = this.noticeServiceEmp.show(this.notice.getSnid(), user.getUserid());
            Notice notice = noticeDto.getNotice();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user",notice.getUser());
            jsonObject.put("title",notice.getTitle());
            jsonObject.put("content",notice.getContent());
            jsonObject.put("snid",notice.getSnid());
            jsonObject.put("pubDate",notice.getPubdate());
            jsonObject.put("level",notice.getLevel());
            jsonObject.put("isFirstRead",noticeDto.isFirstRead());
            super.print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String list(){
        User user = (User) super.getSession().getAttribute("emp");
        try{
            Map<String, Object> map = this.noticeServiceEmp.list(user.getUserid(), super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("count"),"emp_notice.list.action",null,null);
            super.getRequest().setAttribute("all",map.get("allNotices"));
            super.getRequest().setAttribute("unread",map.get("unread"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "notice.list";
    }

    public void unReadNum(){
        User user = (User) super.getSession().getAttribute("emp");
        if (user == null)return;
        try {
            int num = this.noticeServiceEmp.unReadCount(user.getUserid(), 1);
            super.print(num);
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
        return "公告标题:title";
    }

    @Override
    public String getTypeName() {
        return "系统公告";
    }
}
