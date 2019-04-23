package cn.lc.dao.impl;

import cn.lc.dao.IDoctypeDao;
import cn.lc.pojo.Doctype;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-05 22:59
 **/
@Component
public class DoctypeDaoImpl extends AbstractDAOImpl implements IDoctypeDao {
    @Override
    public boolean doCreate(Doctype vo) throws Exception {
        return super.getSession().save(vo) !=null;
    }

    @Override
    public boolean doUpdute(Doctype vo) throws Exception {
        String hql = "update Doctype set title=? where dtid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getTitle());
        query.setParameter(1,vo.getDtid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Doctype findById(Integer id) throws Exception {
        return null;
    }


    @Override
    public List<Doctype> findAll() throws Exception {
        return super.handleList(Doctype.class);
    }

    @Override
    public List<Doctype> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
