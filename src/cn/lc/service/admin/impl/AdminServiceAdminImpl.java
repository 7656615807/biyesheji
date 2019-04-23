package cn.lc.service.admin.impl;

import cn.lc.dao.IActionDao;
import cn.lc.dao.IGroupsDao;
import cn.lc.dao.IRoleDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.Action;
import cn.lc.pojo.Groups;
import cn.lc.pojo.Role;
import cn.lc.pojo.User;
import cn.lc.service.admin.IAdminServiceAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-28 21:55
 **/
@Service
public class AdminServiceAdminImpl implements IAdminServiceAdmin {
    @Resource
    private IUserDao userDao;
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IGroupsDao groupsDao;
    @Resource
    private IActionDao actionDao;

    @Override
    public Map<String, Object> insertPre() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles",roleDao.findAllAdmin());
        return map;
    }

    @Override
    public boolean insert(User vo, String userid) throws Exception {
        User admin = userDao.findById(userid);
        if (admin.getLevel() == 0 ){
            if (userDao.findById(vo.getUserid()) == null){
                vo.setLevel(1);
                vo.setLockflag(0);
                vo.setPhoto("nophoto.jpg");
                return userDao.doCreate(vo);
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> updatePre(String userid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allAdminRoles",roleDao.findAllAdmin());
        map.put("user",userDao.findById(userid));
        return map;
    }

    @Override
    public boolean update(User vo) throws Exception {
        if (this.userDao.findById(vo.getUserid()).getLevel() == 1){
            vo.setLevel(1);
            return this.userDao.doUpdateInfo(vo);
        }
        return false;
    }

    @Override
    public boolean delete(Set<String> ids) throws Exception {
        if (ids.size() == 0){
            return false;
        }
        return userDao.doRemoveBatch(ids);
    }

    @Override
    public boolean updateRole(User vo) throws Exception {
        if (this.userDao.findById(vo.getUserid()).getLevel() == 1){
            vo.setLevel(1);
            return this.userDao.doUpdateRole(vo);
        }
        return false;
    }

    @Override
    public boolean updatePassword(User vo) throws Exception {
        if (this.userDao.findById(vo.getUserid()).getLevel() == 1){
            return this.userDao.doUpdatePassword(vo);
        }
        return false;
    }

    @Override
    public boolean checkUser(String userId) throws Exception {
        if (userDao.findById(userId) == null){
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allAdmins",userDao.findAllAdmin(currentPage,lineSize,column,keyWord));
        map.put("allcounts",userDao.getAllAdminCount(column,keyWord));
        map.put("allRoles",roleDao.findAllAdmin());
        return map;
    }

    //TODO 有毛病，userDao.findById()传入相同的值查出的结果不一样
    //最终测试结果要先查一下role表开启持久化
    @Override
    public User show(User vo) throws Exception {
        roleDao.findAllAdmin();
        User pojo = userDao.findById(vo.getUserid());
        User retUser = new User();
        if (pojo != null){
            retUser.setUserid(vo.getUserid());
            retUser.setLastlogin(pojo.getLastlogin());
            retUser.setLevel(pojo.getLevel());
            retUser.setName(pojo.getName());
            retUser.setPhone(pojo.getPhone());
            retUser.setPhoto(pojo.getPhoto());
            retUser.setEmail(pojo.getEmail());
            pojo.setLastlogin(new Date());
            Role role = this.roleDao.findById(pojo.getRole().getRid());
            List<Groups> allGroups = this.groupsDao.findAllByRole(pojo.getRole().getRid());
            for (Groups groups : allGroups){
                Set<Action> set = new HashSet<>();
                set.addAll(this.actionDao.findAllByGroups(groups.getGid()));
                groups.setActions(set);
            }
            Set<Groups> groupsSet = new HashSet<>();
            groupsSet.addAll(allGroups);
            role.setGroupses(groupsSet);
            retUser.setRole(role);
        }
        return retUser;
    }
}
