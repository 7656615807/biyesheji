package cn.lc.action.admin;

import cn.lc.pojo.User;
import cn.lc.service.common.IUserServiceCommon;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.File;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-06 21:49
 **/

@Repository
@InterceptorRef("adminStack")
@ParentPackage("root")
@Namespace("/")
@Results(value = {@Result(name = "updatePasswordVF",location = "/pages/jsp/admin/admin_password_edit.jsp"),
                    @Result(name = "admin.updatepre",location = "/pages/jsp/admin/admin_update.jsp")})
@Action("AdminUpdateAction")
public class AdminUpdateAction extends AbstractAction {
    private static String updatePasswordRule = "newpassword:string|oldpassword:string";
    
    @Resource
    private IUserServiceCommon userServiceCommon;

    private String newpassword;
    private String oldpassword;
    private User user = new User();
    private File photo ; //得到上传的文件
    private String photoContentType; //上传的文件类型

    public void setPhotoContentType(String photoContentType){
        this.photoContentType = photoContentType ;
    }
    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public void setNewpassword(String newpassword){
        this.newpassword = newpassword;
    }
    public void setOldpassword(String oldpassword){
        this.oldpassword = oldpassword ;
    }
    public User getUser(){
        return user;
    }


    public String updatePassword(){
        User user = (User)super.getSession().getAttribute("admin");
        try {
            if (this.userServiceCommon.updatePassword(user.getUserid(),DigestUtils.md5DigestAsHex(this.oldpassword.getBytes()),
                    DigestUtils.md5DigestAsHex(this.newpassword.getBytes()))){
                super.setMsgAndUrl("user.password.update.success","login.page");
            } else{
                super.setMsgAndUrl("user.password.update.failure","login.page");
            }
            super.getSession().invalidate();
        }catch (Exception e){
            super.setMsgAndUrl("user.password.update.failure","login.page");
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String updatePre(){
        User user = (User)super.getSession().getAttribute("admin");
        try{
            super.getRequest().setAttribute("user",this.userServiceCommon.updatePre(user.getUserid()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin.updatepre" ;
    }
    public String update(){
        User user = (User)super.getSession().getAttribute("admin");
        user.setName(this.user.getName());
        user.setPhone(this.user.getPhone());
        user.setEmail(this.user.getEmail());
        if (this.photo == null) {
            user.setPhoto("nophoto.jpg");
        }else {
            user.setPhoto(super.createSingleFileName(this.photoContentType));
        }
        try {
            if (this.userServiceCommon.update(user)) {
                if (this.photo != null) {
                    String filePath = super.getApplication().getRealPath("/upload/user/") + user.getPhoto();
                    super.saveSingleFile(filePath, this.photo);
                }
                super.getSession().setAttribute("admin",user);
                super.setMsgAndUrl("update.success.msg", "admin.user.update.action");
            }else {
                super.setMsgAndUrl("update.failure.msg","admin.user.update.action");
            }
        }catch (Exception e){
            super.setMsgAndUrl("update.failure.msg","admin.user.update.action");
            e.printStackTrace();
        }
        return "forward.page";
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
        return "信息";
    }
}
