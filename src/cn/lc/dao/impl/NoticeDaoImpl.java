package cn.lc.dao.impl;

import cn.lc.dao.INoticeDao;
import cn.lc.pojo.Notice;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<Integer, Boolean> findUnread(String userid, Integer level) throws Exception {
        String sql = "select snid from notice where " +
                "level>=? and snid not in(select snid from user_notice where userid= ?)";
        Query query = getSQLQuery(sql);
        query.setParameter(0, level);
        query.setParameter(1, userid);
        List<Integer> unReadlist = query.list();
        Map<Integer, Boolean> result = new HashMap<>();
        for (Integer i : unReadlist){
            result.put(i, false);
        }
        return result;
    }

    @Override
    public Integer getAllCountUnread(String userid, Integer level) throws Exception {
        return findUnread(userid, level).values().size();
    }

    @Override
    public Notice findByIdAndLevel(Integer id, Integer level) throws Exception {
        String hql = "From Notice where snid=? and level=?";
        Query query = getQuery(hql);
        query.setParameter(0, id);
        query.setParameter(1, level);
        return (Notice)query.uniqueResult();
    }

    @Override
    public List<Notice> findAllbylevel(Integer currentPage, Integer lineSize, String column, String keyWord, int level) throws Exception {
        Criteria criteria = this.getCriteria(Notice.class);
        criteria.add(Restrictions.and(
                Restrictions.like(column,"%"+keyWord+ "%"),
                Restrictions.eq("level", level)));
        criteria.setFirstResult((currentPage - 1) * lineSize);
        criteria.setMaxResults(lineSize);
        return criteria.list();
    }

    @Override
    public Integer getCountBylevel(String column, String keyWord, int level) throws Exception {
        String hql = "select count(*) From Notice as p where p.level=? and p."+ column + " LIKE ?" ;
        Query query = this.getQuery(hql);
        query.setParameter(0, level);
        query.setParameter(1,"%" + keyWord +"%");
        return ((Long) query.uniqueResult()).intValue();
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
