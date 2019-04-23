package cn.lc.dao.impl;

import cn.lc.dao.IUserDao;
import cn.lc.pojo.User;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 14:32
 **/
@Component
public class UserDaoImpl extends AbstractDAOImpl implements IUserDao {


    @Override
    public User findLogin(String userid, String password) throws Exception {
        String hql = "FROM User AS u WHERE u.userid=? AND u.password=? AND lockflag=0";
        Query query = super.getQuery(hql);
        query.setParameter(0,userid);
        query.setParameter(1,password);
        return (User)query.uniqueResult();
    }

    @Override
    public boolean doUpdatePassword(User vo) throws Exception {
        String hql = "update User as u set u.password=? where u.userid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getPassword());
        query.setParameter(1,vo.getUserid());
        return query.executeUpdate()>0;
    }

    @Override
    public List<User> findAllUser(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        String hql = "From User as u where u." + column + " like ? and u.level in(2,3)";
        Query query = getQuery(hql);
        query.setParameter(0,"%"+keyWord+"%");
        query.setFirstResult((currentPage - 1)* lineSize);
        query.setMaxResults(lineSize);
        return query.list();
    }
    @Override
    public List<User> findAllUserByLock(Integer lockflag,Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        String hql = "From User as u where u." + column + " like ? and u.level in(2,3) and u.lockflag=?";
        Query query = getQuery(hql);
        query.setParameter(0,"%"+keyWord+"%");
        query.setParameter(1,lockflag);
        query.setFirstResult((currentPage - 1)* lineSize);
        query.setMaxResults(lineSize);
        return query.list();
    }
    @Override
    public List<User> findAllAdmin(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        String hql = "From User as u where u." + column + " like ? and u.level in(0,1)";
        Query query = getQuery(hql);
        query.setParameter(0,"%"+keyWord+"%");
        query.setFirstResult((currentPage - 1)* lineSize);
        query.setMaxResults(lineSize);
        return query.list();
    }

    @Override
    public boolean doUpdateRole(User vo) throws Exception {
        String hql = "update User as u set u.role.rid=?,u.level=? where u.userid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getRole().getRid());
        query.setParameter(1,vo.getLevel());
        query.setParameter(2,vo.getUserid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doUpdateInfo(User vo) throws Exception {
        String hql = "update User as u set u.name=? ,u.phone=?,u.email=?,u.role.rid=?,u.level=? where u.userid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getName());
        query.setParameter(1,vo.getPhone());
        query.setParameter(2,vo.getEmail());
        query.setParameter(3,vo.getRole().getRid());
        query.setParameter(4,vo.getLevel());
        query.setParameter(5,vo.getUserid());
        return query.executeUpdate()>0;
    }

    @Override
    public Integer getAllUserCount(String column, String keyWord) throws Exception {
        String hql = "select count(*) from User u where u."+column+" like ? and u.level in(2,3)";
        Query query = getQuery(hql);
        query.setParameter(0,"%"+keyWord+"%");
        return ((Long)query.uniqueResult()).intValue();
    }

    @Override
    public Integer getAllUserCountByLock(Integer lockflag,String column, String keyWord) throws Exception {
        String hql = "select count(*) from User u where u."+column+" like ? and u.level in(2,3) and u.lockflag=?";
        Query query = getQuery(hql);
        query.setParameter(0,"%"+keyWord+"%");
        query.setParameter(1,lockflag);
        return ((Long)query.uniqueResult()).intValue();
    }

    @Override
    public boolean doUpdateLock(Set<String> ids, Integer lock) throws Exception {
        StringBuffer buffer = new StringBuffer();
        buffer.append("update User ").append("set lockflag=? ").append("Where ").append("userid ").append("in(");
        for (Object o : ids){
            buffer.append("'").append(o).append("'").append(",");
        }
        buffer.delete(buffer.length() -1 ,buffer.length()).append(")").append(" and level > 1");
        Query query = getQuery(buffer.toString());
        query.setParameter(0,lock);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<User> findAllByLevel(int level) throws Exception {
        String hql = "From User as u where u.level=? and u.lockflag=0";
        Query query = getQuery(hql);
        query.setParameter(0,level);
        return query.list();
    }

    @Override
    public Integer getAllAdminCount(String column, String keyWord) throws Exception {
        String hql = "select count(*) from User u where u."+column+" like ? and u.level in(0,1)";
        Query query = getQuery(hql);
        query.setParameter(0,"%"+keyWord+"%");
        return ((Long)query.uniqueResult()).intValue();
    }

    @Override
    public boolean doCreate(User vo) throws Exception {
        return super.getSession().save(vo) != null;
    }

    @Override
    public boolean doUpdute(User vo) throws Exception {
        String hql = "update User as u set u.name=? ,u.phone=?,u.email=?,u.photo=? where u.userid=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,vo.getName());
        query.setParameter(1,vo.getPhone());
        query.setParameter(2,vo.getEmail());
        query.setParameter(3,vo.getPhoto());
        query.setParameter(4,vo.getUserid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        StringBuffer buffer = new StringBuffer();
        buffer.append("delete From ").append("User ").append("Where ").append("userid ").append("in(");
        for (Object o : ids){
            buffer.append("'").append(o).append("'").append(",");
        }
        buffer.delete(buffer.length() -1 ,buffer.length()).append(")").append(" and level > 0");
        Query query = getQuery(buffer.toString());
        return query.executeUpdate() > 0;
    }

    @Override
    public User findById(String id) throws Exception {
        return (User)super.getSession().get(User.class,id);
    }

    @Override
    public List<User> findAll() throws Exception {
        return null;
    }

    @Override
    public List<User> findAllSplit(String currentPage, String lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public String getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
