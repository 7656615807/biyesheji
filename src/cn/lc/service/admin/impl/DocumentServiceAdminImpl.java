package cn.lc.service.admin.impl;

import cn.lc.dao.IDoctypeDao;
import cn.lc.dao.IDocumentDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.Document;
import cn.lc.service.admin.IDocumentServiceAdmin;
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
public class DocumentServiceAdminImpl implements IDocumentServiceAdmin {
    @Autowired
    private IDocumentDao documentDao;
    @Autowired
    private IDoctypeDao doctypeDao;
    @Autowired
    private IUserDao userDao;
    @Override
    public Map<String, Object> insertPre() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allDoctypes",doctypeDao.findAll());
        return map;
    }

    @Override
    public boolean insert(Document vo) throws Exception {
        vo.setPubdate(new Date());
        return documentDao.doCreate(vo);
    }

    @Override
    public Map<String, Object> updatePre(int did) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allDoctypes",doctypeDao.findAll());
        map.put("document",documentDao.findById(did));
        return map;
    }

    @Override
    public boolean update(Document vo) throws Exception {
        return documentDao.doUpdute(vo);
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        if (ids.size()<=0){
            return false;
        }
        return documentDao.doRemoveBatch(ids);
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allDoctypes",doctypeDao.findAll());
        map.put("allDocuments",documentDao.findAllSplit(currentPage,lineSize,column,keyWord));
        map.put("documentCount",documentDao.getAllCount(column,keyWord));
        return map;
    }
}
