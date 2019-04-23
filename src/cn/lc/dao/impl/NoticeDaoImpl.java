package cn.lc.dao.impl;

import cn.lc.dao.INoticeDao;
import cn.lc.pojo.Notice;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-07 17:16
 **/
@Component
public class NoticeDaoImpl extends AbstractDAOImpl implements INoticeDao {

    @Override
    public boolean doupdateLevel(Integer snid, Integer level) throws Exception {
        String hql = "update Notice set level=? where snid=?";
        Query query = getQuery(hql);
        query.setParameter(0,level);
        query.setParameter(1,snid);
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doCreate(Notice vo) throws Exception {
        return super.getSession().save(vo)!=null;
    }

    @Override
    public boolean doUpdute(Notice vo) throws Exception {
        String hql = "update Notice set title=?,content=?,level=? where snid=?";
        Query query = getQuery(hql);
        query.setParameter(0,vo.getTitle());
        query.setParameter(1,vo.getContent());
        query.setParameter(2,vo.getLevel());
        query.setParameter(3,vo.getSnid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.handleRemoveBatch("Notice","snid",ids);
    }

    @Override
    public Notice findById(Integer id) throws Exception {
        return (Notice) super.getSession().get(Notice.class,id);
    }

    @Override
    public List<Notice> findAll() throws Exception {
        return super.handleList(Notice.class);
    }

    @Override
    public List<Notice> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Notice.class,currentPage,lineSize,column,keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Notice",column,keyWord);
    }
}
