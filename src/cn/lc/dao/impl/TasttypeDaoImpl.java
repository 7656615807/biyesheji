package cn.lc.dao.impl;

import cn.lc.dao.ITasttypeDao;
import cn.lc.pojo.Tasktype;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-09 01:48
 **/
@Component
public class TasttypeDaoImpl extends AbstractDAOImpl implements ITasttypeDao {
    @Override
    public boolean doCreate(Tasktype vo) throws Exception {
        return super.getSession().save(vo)!=null;
    }

    @Override
    public boolean doUpdute(Tasktype vo) throws Exception {
        String hql = "Update Tasktype set title=? where ttid=?";
        Query query =getQuery(hql);
        query.setParameter(0,vo.getTitle());
        query.setParameter(1,vo.getTtid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Tasktype findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Tasktype> findAll() throws Exception {
        return super.handleList(Tasktype.class);
    }

    @Override
    public List<Tasktype> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
