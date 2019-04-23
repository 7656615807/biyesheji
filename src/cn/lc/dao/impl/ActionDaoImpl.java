package cn.lc.dao.impl;

import cn.lc.dao.IActionDao;
import cn.lc.dao.IGroupsDao;
import cn.lc.pojo.Action;
import cn.lc.pojo.Groups;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-03 01:34
 **/
@Component
public class ActionDaoImpl extends AbstractDAOImpl implements IActionDao {

    @Override
    public List<Action> findAllByGroups(Integer gid) throws Exception {
        String hql = "FROM Action AS a where a.groups.gid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,gid);
        return query.list();
    }

    @Override
    public boolean doCreate(Action vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdute(Action vo) throws Exception {
        String hql = "update Action as a set a.title=?,a.url=? where a.actid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getTitle());
        query.setParameter(1,vo.getUrl());
        query.setParameter(2,vo.getActid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Action findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Action> findAll() throws Exception {
        return super.handleList(Action.class);
    }

    @Override
    public List<Action> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Action.class,currentPage,lineSize,column,keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Action",column,keyWord);
    }

}
