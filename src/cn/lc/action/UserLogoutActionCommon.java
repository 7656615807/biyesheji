package cn.lc.action;

import cn.lc.pojo.User;
import cn.lc.service.common.IUserServiceCommon;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 15:03
 **/
@Repository
@ParentPackage("root")
@Namespace("/")
@Action("UserLogout")
@SuppressWarnings("serial")
public class UserLogoutActionCommon extends AbstractAction {

    public String logout(){
        super.getSession().invalidate();
        super.setMsgAndUrl("user.logout.msg","login.page");
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
        return null;
    }
}
