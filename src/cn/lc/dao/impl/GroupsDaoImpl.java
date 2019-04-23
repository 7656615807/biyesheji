package cn.lc.dao.impl;

import cn.lc.dao.IGroupsDao;
import cn.lc.pojo.Groups;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-03 01:34
 **/

@Component
public class GroupsDaoImpl extends AbstractDAOImpl implements IGroupsDao  {

    @Override
    public List<Groups> findAllByRole(Integer rid) throws Exception {
        String sql = "SELECT gid,title,note FROM groups where gid in ("+
                " SELECT gid from role_groups where rid = ? )";
        Query query = super.getSQLQuery(sql);
        query.setResultTransformer(new AliasToBeanResultTransformer(Groups.class));
        query.setParameter(0,rid);
        return query.list();
    }

    @Override
    public boolean doCreate(Groups vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdute(Groups vo) throws Exception {
        String hql = "Update Groups as g set g.title = ?, g.note = ? where g.gid = ?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getTitle());
        query.setParameter(1,vo.getNote());
        query.setParameter(2,vo.getGid());
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Groups findById(Integer id) throws Exception {
        return (Groups)super.getSession().get(Groups.class, id);
    }

    @Override
    public List<Groups> findAll() throws Exception {
        return super.handleList(Groups.class);
    }

    @Override
    public List<Groups> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Groups.class,currentPage,lineSize,column,keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Groups",column,keyWord);
    }
}
