package cn.lc.service.admin.impl;

import cn.lc.dao.IDoctypeDao;
import cn.lc.dao.ITasttypeDao;
import cn.lc.pojo.Doctype;
import cn.lc.pojo.Tasktype;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import cn.lc.service.admin.ITasktypeServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-05 23:14
 **/
@Service
public class TasktypeServiceAdminImpl implements ITasktypeServiceAdmin {
    @Autowired
    private ITasttypeDao tasttypeDao;

    @Override
    public boolean insert(Tasktype vo) throws Exception {
        return tasttypeDao.doCreate(vo);
    }

    @Override
    public boolean update(Tasktype vo) throws Exception {
        return tasttypeDao.doUpdute(vo);
    }

    @Override
    public List<Tasktype> list() throws Exception {
        return tasttypeDao.findAll();
    }
}
