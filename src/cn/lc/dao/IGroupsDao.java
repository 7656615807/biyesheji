package cn.lc.dao;

import cn.lc.pojo.Groups;
import cn.lc.util.dao.IDAO;

import java.util.List;

public interface IGroupsDao extends IDAO<Integer,Groups> {
    /**
     * 利用子查询查询一个角色对应的所有权限组的信息，这样比年掉Hibernate中自动级联时多余的查询以及性能低下多表查询
     * @param rid   角色编号
     * @return  一个角色所具备的权限组信息
     * @throws Exception
     */
    public List<Groups> findAllByRole(Integer rid) throws Exception;
}
