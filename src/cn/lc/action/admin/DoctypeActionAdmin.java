package cn.lc.action.admin;

import cn.lc.pojo.Doctype;
import cn.lc.service.admin.IActionServiceAdmin;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
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
@Results(value = {@Result(name = "doctype.list",location = "/pages/jsp/admin/doctypeList.jsp")})
@Action("DoctypeActionAdmin")
public class DoctypeActionAdmin extends AbstractAction {

    private static String updateRule = "doctype.title:string";

    @Resource
    private IDoctypeServiceAdmin doctypeServiceAdmin;

    private Doctype doctype = new Doctype();

    public Doctype getDoctype() {
        return doctype;
    }

    public String list(){
        try{
            List<Doctype> list = this.doctypeServiceAdmin.list();
            super.getRequest().setAttribute("all",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "doctype.list";
    }

    public void update(){
        try {
            super.print(this.doctypeServiceAdmin.update(this.doctype));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String insert(){
        try {
            if (this.doctypeServiceAdmin.insert(this.doctype)) {
                super.setMsgAndUrl("insert.success.msg", "doctype.list.action");
            } else {
                super.setMsgAndUrl("insert.failure.msg", "doctype.list.action");
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
        return "文档类型";
    }
}
