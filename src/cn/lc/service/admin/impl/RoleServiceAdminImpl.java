package cn.lc.service.admin.impl;

import cn.lc.dao.IGroupsDao;
import cn.lc.dao.IRoleDao;
import cn.lc.pojo.Role;
import cn.lc.service.admin.IRoleServiceAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-25 22:19
 **/
@Service
public class RoleServiceAdminImpl implements IRoleServiceAdmin {
    @Resource
    private IGroupsDao groupsDao;
    @Resource
    private IRoleDao roleDao;

    @Override
    public Map<String, Object> insertPre() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("all", this.groupsDao.findAll());
        return map;
    }

    @Override
    public Map<String, Object> updatePre(int rid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("all",this.groupsDao.findAll());
        map.put("role",this.roleDao.findById(rid));
        map.put("gids",this.roleDao.findRoleGroups(rid));
        return map;
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles",this.roleDao.findAllSplit(currentPage,lineSize,column,keyWord));
        map.put("roleCount",this.roleDao.getAllCount(column,keyWord));
        return map;
    }

    @Override
    public boolean update(Role vo) throws Exception {
        if (this.roleDao.findByTitleAndNotId(vo.getTitle(),vo.getRid()) == null){
            return this.roleDao.doUpdute(vo);
        }
        return false;
    }

    @Override
    public boolean insert(Role vo) throws Exception {
        if (this.roleDao.findByTitle(vo.getTitle()) == null){
            return this.roleDao.doCreate(vo);
        }
        return false;
    }

    @Override
    public boolean checkTitle(String title) throws Exception {
        return this.roleDao.findByTitle(title) == null;
    }

    @Override
    public boolean checkTitle(String title, int rid) throws Exception {
        return this.roleDao.findByTitleAndNotId(title,rid) == null;
    }

}
