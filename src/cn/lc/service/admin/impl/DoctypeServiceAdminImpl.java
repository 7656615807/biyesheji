package cn.lc.service.admin.impl;

import cn.lc.dao.IDoctypeDao;
import cn.lc.pojo.Doctype;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-05 23:14
 **/
@Service
public class DoctypeServiceAdminImpl implements IDoctypeServiceAdmin {
    @Autowired
    private IDoctypeDao doctypeDao;

    @Override
    public boolean insert(Doctype vo) throws Exception {
        return doctypeDao.doCreate(vo);
    }

    @Override
    public boolean update(Doctype vo) throws Exception {
        return doctypeDao.doUpdute(vo);
    }

    @Override
    public List<Doctype> list() throws Exception {
        return doctypeDao.findAll();
    }
}
