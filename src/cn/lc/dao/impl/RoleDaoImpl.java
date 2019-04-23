package cn.lc.dao.impl;

import cn.lc.dao.IRoleDao;
import cn.lc.pojo.Role;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-25 00:11
 **/
@Component
public class RoleDaoImpl extends AbstractDAOImpl implements IRoleDao {


    @Override
    public Role findByTitle(String title) throws Exception {
        String hql = "from Role as r where r.title=?";
        Query query = super.getQuery(hql);
        query.setParameter(0,title);
        return (Role)query.uniqueResult();
    }

    @Override
    public Role findByTitleAndNotId(String title, Integer rid) throws Exception {
        String hql = "From Role As r where r.title=? and r.rid!=?";
        Query query = super.getQuery(hql);
        query.setParameter(0, title);
        query.setParameter(1, rid);
        return (Role)query.uniqueResult();
    }

    @Override
    public Map<Integer, Boolean> findRoleGroups(Integer rid) throws Exception {
        Map<Integer, Boolean> map = new HashMap<>();
        String sql = "select gid from role_groups where rid=?";
        Query query = super.getSQLQuery(sql);
        query.setParameter(0,rid);
        List<Integer> all = query.list();
        for (Integer i : all){
            map.put(i,true);
        }
        return map;
    }

    @Override
    public boolean doCreate(Role vo) throws Exception {
        return super.getSession().save(vo) != null;
    }

    @Override
    public boolean doUpdute(Role vo) throws Exception {
        super.getSession().update(vo);
        return true;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Role findById(Integer id) throws Exception {
        return (Role)super.getSession().get(Role.class, id);
    }

    @Override
    public List<Role> findAll() throws Exception {
        return super.handleList(Role.class);
    }

    @Override
    public List<Role> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return super.handlieListSplit(Role.class, currentPage, lineSize, column, keyWord);
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.handleCount("Role", column, keyWord);
    }
    @Override
    public List<Role> findAllAdmin() throws Exception {
        String hql = "From Role r where r.rid not in(4,5)";
        Query query =super.getQuery(hql);
        return query.list();
    }
}
