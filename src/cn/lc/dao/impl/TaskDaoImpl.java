package cn.lc.dao.impl;

import cn.lc.dao.ITaskDao;
import cn.lc.pojo.Notice;
import cn.lc.pojo.Task;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-05-10 23:56
 **/
@Component
public class TaskDaoImpl extends AbstractDAOImpl implements ITaskDao {
    @Override
    public boolean doCreate(Task vo) throws Exception {
        return super.getSession().save(vo) != null;
    }

    @Override
    public boolean doUpdute(Task vo) throws Exception {
        String hql ="update Task set expiredate=?, status=?, lastupdatedate=?, estimate=?, priority=?, note=? where tid=?";
        Query query = getQuery(hql);
        query.setParameter(0, vo.getExpiredate());
        query.setParameter(1, vo.getStatus());
        query.setParameter(2, vo.getLastupdatedate());
        query.setParameter(3, vo.getEstimate());
        query.setParameter(4, vo.getPriority());
        query.setParameter(5, vo.getNote());
        query.setParameter(6, vo.getTid());
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Task findById(Integer id) throws Exception {
        return (Task) getSession().get(Task.class,id);
    }

    @Override
    public List<Task> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Task> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Task.class,currentPage,lineSize,column,keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Task", column, keyWord);
    }

    @Override
    public boolean updateByCancle(Task vo) throws Exception {
        String hql ="update Task set userByCanceler.userid=?, status=2, lastupdatedate=?, cnote=? where tid=?";
        Query query = getQuery(hql);
        query.setParameter(0, vo.getUserByCanceler().getUserid());
        query.setParameter(1, vo.getLastupdatedate());
        query.setParameter(2, vo.getCnote());
        query.setParameter(3, vo.getTid());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Task> findFinishTaskBymanager(String userid, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        Criteria criteria = this.getCriteria(Task.class);
        criteria.add(Restrictions.and(
                Restrictions.like(column,"%"+keyWord+ "%"),
                Restrictions.eq("userByCreator.userid", userid),
                Restrictions.eq("status", 3)));
        criteria.setFirstResult((currentPage - 1) * lineSize);
        criteria.setMaxResults(lineSize);
        return criteria.list();
    }

    @Override
    public Integer getFinishTaskCountByManager(String userid, String column, String keyWord) throws Exception {
        String hql = "select count(*) From Task as T where T.userByCreator.userid=? and T.status=2 and T."+ column + " LIKE ?" ;
        Query query = this.getQuery(hql);
        query.setParameter(0, userid);
        query.setParameter(1,"%" + keyWord +"%");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean updateStartTask(int tid) throws Exception {
        String hql ="update Task set  status=1, lastupdatedate=? where tid=?";
        Query query = getQuery(hql);
        query.setParameter(0, new Date());
        query.setParameter(1, tid);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateFinishTask(Task vo) throws Exception {
        String hql ="update Task set finishdate=?, status=3, lastupdatedate=?, expend=?, rnote=? where tid=?";
        Query query = getQuery(hql);
        query.setParameter(0, vo.getFinishdate());
        query.setParameter(1, vo.getLastupdatedate());
        query.setParameter(2, vo.getExpend());
        query.setParameter(3, vo.getRnote());
        query.setParameter(4, vo.getTid());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Task> findTaskByEmp(String userid, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        Criteria criteria = this.getCriteria(Task.class);
        criteria.add(Restrictions.and(
                Restrictions.like(column,"%"+keyWord+ "%"),
                Restrictions.eq("userByReceiver.userid", userid)));
        criteria.setFirstResult((currentPage - 1) * lineSize);
        criteria.setMaxResults(lineSize);
        return criteria.list();
    }

    @Override
    public Integer getTaskCountByEmp(String userid, String column, String keyWord) throws Exception {
        String hql = "select count(*) From Task as T where T.userByReceiver.userid=? and T."+ column + " LIKE ?" ;
        Query query = this.getQuery(hql);
        query.setParameter(0, userid);
        query.setParameter(1,"%" + keyWord +"%");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer getStatus0TaskCountByUser(String userid) throws Exception {
        String hql = "select count(*) From Task as T where T.userByReceiver.userid=? and T.status=0" ;
        Query query = this.getQuery(hql);
        query.setParameter(0, userid);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer getStatus1TaskCountByUser(String userid) throws Exception {
        String hql = "select count(*) From Task as T where T.userByReceiver.userid=? and T.status=1" ;
        Query query = this.getQuery(hql);
        query.setParameter(0, userid);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public List<Task> findTaskByProject(Integer proid, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        Criteria criteria = this.getCriteria(Task.class);
        criteria.add(Restrictions.and(
                Restrictions.like(column,"%"+keyWord+ "%"),
                Restrictions.eq("project.proid", proid)));
        criteria.setFirstResult((currentPage - 1) * lineSize);
        criteria.setMaxResults(lineSize);
        return criteria.list();
    }

    @Override
    public Integer getTaskCountByProject(Integer proid, String column, String keyWord) throws Exception {
        String hql = "select count(*) From Task as T where T.project.proid=? and T."+ column + " LIKE ?" ;
        Query query = this.getQuery(hql);
        query.setParameter(0, proid);
        query.setParameter(1,"%" + keyWord +"%");
        return ((Long) query.uniqueResult()).intValue();
    }
}
