package cn.lc.service.common.impl;

import cn.lc.dao.IActionDao;
import cn.lc.dao.IGroupsDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.*;
import cn.lc.service.common.IUserServiceCommon;
import cn.lc.util.util.SortUtil;
import org.springframework.stereotype.Service;
        import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 14:57
 **/
@Service
public class UserServiceCommonImpl implements IUserServiceCommon {
    @Resource
    private IUserDao userDao;

    @Resource
    private IGroupsDao groupsDao;

    @Resource
    private IActionDao actionDao;

    @Override
    public User login(String userid, String password) throws Exception {
        User retObject = new User();
        User pojo = this.userDao.findLogin(userid,password);
        if (pojo !=null){
            List<Groups> groupsList = groupsDao.findAllByRole(pojo.getRole().getRid());
            for (Groups groups : groupsList){
                Set<Action> set = new HashSet<>();
                set.addAll(actionDao.findAllByGroups(groups.getGid()));
                groups.setActions(set);
            }
            Set<Groups> gset = new HashSet<>();
            gset.addAll(groupsList);
            Role role = new Role();
            role.setGroupses(gset);
            retObject.setRole(role);
            retObject.setLastlogin(pojo.getLastlogin());
            retObject.setLevel(pojo.getLevel());
            retObject.setName(pojo.getName());
            retObject.setPhoto(pojo.getPhoto());
            retObject.setUserid(pojo.getUserid());
            pojo.setLastlogin(new Date());
        }
        return retObject;
    }

    @Override
    public boolean updatePassword(String userId, String oldPass, String newPass) throws Exception {
        if (this.userDao.findLogin(userId,oldPass)!=null){
            User vo = new User();
            vo.setUserid(userId);
            vo.setPassword(newPass);
            return this.userDao.doUpdatePassword(vo);
        }
        return false;
    }

    @Override
    public User updatePre(String userif) throws Exception {
        return this.userDao.findById(userif);
    }

    @Override
    public boolean update(User vo) throws Exception {
        return this.userDao.doUpdute(vo);
    }

}
