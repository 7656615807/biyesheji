package cn.lc.service.admin.impl;

import cn.lc.dao.IDoctypeDao;
import cn.lc.dao.IDocumentDao;
import cn.lc.dao.IProjectDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.Document;
import cn.lc.pojo.Project;
import cn.lc.pojo.User;
import cn.lc.service.admin.IDocumentServiceAdmin;
import cn.lc.service.admin.IProjectServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-06 11:05
 **/
@Service
public class ProjectServiceAdminImpl implements IProjectServiceAdmin {
    @Autowired
    private IProjectDao projectDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public Map<String, Object> insertPre() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allMangers",userDao.findAllByLevel(2));
        return map;
    }

    @Override
    public boolean insert(Project vo) throws Exception {
        User admin = userDao.findById(vo.getUserByCreid().getUserid());
        if (admin.getLevel()!=1&& admin.getLevel()!=0){
            return false;
        }
        vo.setPubdate(new Date());
        return projectDao.doCreate(vo);
    }

    @Override
    public Map<String, Object> updatePre(int did) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allMangers",userDao.findAllByLevel(2));
        map.put("project",projectDao.findById(did));
        return map;
    }

    @Override
    public boolean update(Project vo) throws Exception {
        User admin = userDao.findById(vo.getUserByCreid().getUserid());
        if (admin.getLevel()!=1&& admin.getLevel()!=0){
            return false;
        }
        return projectDao.doUpdute(vo);
    }



    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allProjects",projectDao.findAllSplit(currentPage,lineSize,column,keyWord));
        map.put("projectCount",projectDao.getAllCount(column,keyWord));
        return map;
    }
}
