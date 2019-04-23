package cn.lc.util.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @description: 作为DAO子类的公共父类，目的是简化重复代码的开发
 * @author: lc
 * @date: 2019-01-10 23:58
 **/

public abstract class AbstractDAOImpl {
    @Resource
    private SessionFactory sessionFactory;

    /**
     * 设置SessionFactory类对象，是在子类构造方法注入的时候自动完成调用
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    /**
     * 返回SessionFactory，一般只有在操作二级缓存的时候才会执行此操作
     * @return
     */
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /**
     * 负责提供当前可用的session对象
     * @return
     */
    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }

    /**
     *  利用此方法取得query对象
     * @param hql   要执行的hql
     * @return
     */
    public Query getQuery(String hql){
        return this.getSession().createQuery(hql);
    }

    /**
     * Criteria
     * @param cls
     * @return
     */
    public Criteria getCriteria(Class<?> cls){
        Criteria criteria = this.getSession().createCriteria(cls);
        return criteria;
    }
    /**
     *  创建sql查询操作
     * @param hql   要执行的sql
     * @return
             */
    public Query getSQLQuery(String hql){
        return this.getSession().createSQLQuery(hql);
    }

    /**
     * 实现数据量的查询统计
     * @param pojoName  要查询的pojo类的名字
     * @param column    进行模糊处理时的列名称
     * @param keyWord   keyWord 查询关键字
     * @return  统计的数据行数
     */
    public Integer handleCount(String pojoName,String column,String keyWord){
        String hql = "select count(*) From " + pojoName + " as p where p."+ column + " LIKE ?" ;
        Query query = this.getQuery(hql);
        query.setParameter(0,"%" + keyWord +"%");
        return ((Long) query.uniqueResult()).intValue();
    }

    /**
     * 处理分页数据的分页显示查询的操作
     * @param cls   要处理的pojo类的名字
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数据行数
     * @param column    模糊查询列
     * @param keyWord   模糊查询关键字
     * @return  查询数据以list集合返回
     */
    public List handlieListSplit(Class<?> cls, Integer currentPage,Integer lineSize,String column,String keyWord){
        Criteria criteria = this.getCriteria(cls);
        criteria.add(Restrictions.like(column,"%"+keyWord+ "%"));
        criteria.setFirstResult((currentPage - 1) * lineSize);
        criteria.setMaxResults(lineSize);
        return criteria.list();
    }

    /**
     * 进行数据的全部列表操作
     * @param cls   要操作的pojo类型
     * @return  列表以list集合返回
     */
    public List handleList(Class<?> cls){
        Criteria criteria = this.getCriteria(cls);
        return criteria.list();
    }

    public boolean handleRemoveBatch(String pojo, String idName,Set<?> ids){
        StringBuffer buffer = new StringBuffer();
        buffer.append("delete From ").append(pojo).append(" Where ").append(idName).append(" in(");
        for (Object o : ids){
            buffer.append(o).append(",");
        }
        buffer.delete(buffer.length() -1 ,buffer.length()).append(")");
        Query query = getQuery(buffer.toString());
        return query.executeUpdate() > 0;
    }
}
