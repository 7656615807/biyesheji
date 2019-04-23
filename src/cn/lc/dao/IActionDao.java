package cn.lc.dao;

import cn.lc.pojo.Action;

import cn.lc.util.dao.IDAO;

import java.util.List;

public interface IActionDao extends IDAO<Integer,Action> {
    /**
     * 根据权限组编号查询出所有的对应权限信息
     * @param gid   权限组编号
     * @return  List<Action>
     * @throws Exception
     */
     List<Action> findAllByGroups(Integer gid) throws Exception;
}
