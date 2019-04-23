package cn.lc.dao.impl;

import cn.lc.dao.IProjectDao;
import cn.lc.pojo.Project;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-09 15:09
 **/
@Component
public class ProjectImpl extends AbstractDAOImpl implements IProjectDao {
    @Override
    public boolean doCreate(Project vo) throws Exception {
        return super.getSession().save(vo)!=null;
    }

    @Override
    public boolean doUpdute(Project vo) throws Exception {
        String hql = "update Project set title=?,note=?,mgr=?,name=? where proid=?";
        Query query = getQuery(hql);
        query.setParameter(0,vo.getTitle());
        query.setParameter(1,vo.getNote());
        query.setParameter(2,vo.getUserByMgr().getUserid());
        query.setParameter(3,vo.getUserByMgr().getName());
        query.setParameter(4,vo.getProid());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Project findById(Integer id) throws Exception {
        return (Project) super.getSession().get(Project.class,id);
    }

    @Override
    public List<Project> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Project> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Project.class,currentPage,lineSize,column,keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Project",column,keyWord);
    }
}
