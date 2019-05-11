package cn.lc.dao.impl;

import cn.lc.dao.IDocumentDao;
import cn.lc.pojo.Document;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-06 10:28
 **/
@Component
public class DocumentDaoImpl extends AbstractDAOImpl implements IDocumentDao {
    @Override
    public boolean doCreate(Document vo) throws Exception {
        return super.getSession().save(vo) != null;
    }

    @Override
    public boolean doUpdute(Document vo) throws Exception {
        String hql = "Update Document set dtid=?,title=?,content=?,file=? where did=?";
        Query query = getQuery(hql);
        query.setParameter(0,vo.getDoctype().getDtid());
        query.setParameter(1,vo.getTitle());
        query.setParameter(2,vo.getContent());
        query.setParameter(3,vo.getFile());
        query.setParameter(4,vo.getDid());
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.handleRemoveBatch("Document","did",ids);

    }

    @Override
    public Document findById(Integer id) throws Exception {
        return (Document) super.getSession().get(Document.class,id);
    }

    @Override
    public List<Document> findAll() throws Exception {
        return super.handleList(Document.class);
    }

    @Override
    public List<Document> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Document.class,currentPage,lineSize,column,keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Document",column,keyWord);
    }

    @Override
    public List<Document> findAllSplitByUser(String userid, Integer currentPage, Integer lineSize, String column, String keyWord) {
        Criteria criteria = this.getCriteria(Document.class);
        criteria.add(Restrictions.and(
                Restrictions.like(column,"%"+keyWord+ "%"),
                Restrictions.eq("user.userid",userid)));
        criteria.setFirstResult((currentPage - 1) * lineSize);
        criteria.setMaxResults(lineSize);
        return criteria.list();
    }

    @Override
    public Integer getAllCountByUser(String userid, String column, String keyWord) {
        String hql = "select count(*) From Document  as p where p."+ column + " LIKE ? and user.userid=?" ;
        Query query = this.getQuery(hql);
        query.setParameter(0,"%" + keyWord +"%");
        query.setParameter(1,userid);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean doRemoveBatchByUser(String userid, Set<Integer> ids) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("delete From ").append(" Document").append(" Where ").append("did").append(" in(");
        for (Object o : ids){
            buffer.append(o).append(",");
        }
        buffer.delete(buffer.length() -1 ,buffer.length()).append(")");
        buffer.append(" and uerid = '"+userid +"'");
        Query query = getQuery(buffer.toString());
        return query.executeUpdate() > 0;
    }
}
