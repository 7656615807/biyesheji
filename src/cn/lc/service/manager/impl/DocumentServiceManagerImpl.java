package cn.lc.service.manager.impl;

import cn.lc.dao.IDoctypeDao;
import cn.lc.dao.IDocumentDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.Document;
import cn.lc.service.admin.IDocumentServiceAdmin;
import cn.lc.service.manager.IDocumentServiceManager;
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
public class DocumentServiceManagerImpl implements IDocumentServiceManager {
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
    public boolean update(Document vo,String userid) throws Exception {
        Document pojo = documentDao.findById(vo.getDid());
        if (!userid.equals(pojo.getUser().getUserid())){
            return false;
        }
        return documentDao.doUpdute(vo);
    }

    @Override
    public boolean delete(Set<Integer> ids,String userid) throws Exception {
        if (ids.size()<=0){
            return false;
        }
        return documentDao.doRemoveBatchByUser(userid,ids);
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allDoctypes",doctypeDao.findAll());
        map.put("documentCount",documentDao.getAllCount(column,keyWord));
        map.put("allDocuments",documentDao.findAllSplit(currentPage,lineSize,column,keyWord));
        return map;
    }

    @Override
    public Map<String, Object> listByUser(String userid, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allDoctypes",doctypeDao.findAll());
        map.put("allDocuments",documentDao.findAllSplitByUser(userid,currentPage,lineSize,column,keyWord));
        map.put("documentCount",documentDao.getAllCountByUser(userid,column,keyWord));
        return map;
    }

    @Override
    public Document show(int did) throws Exception {
        return documentDao.findById(did);
    }
}
