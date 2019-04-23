package cn.lc.dao;

import cn.lc.pojo.Role;
import cn.lc.util.dao.IDAO;

import java.util.List;
import java.util.Map;

public interface IRoleDao extends IDAO<Integer, Role> {
    /**
     * 根据名称取得角色信息，目的是进行Ajax异步验证
     * @param title 角色名称
     * @return  如果找到指定的角色信息返回pojo对象，否则返回null
     * @throws Exception
     */
    Role findByTitle(String title) throws Exception;

    /**
     * 根据角色名称取得角色信息，但是要排除掉指定的角色编号，给更新操作使用的
     * @param title 角色名称
     * @param rid   要排除的角色编号
     * @return  如果找到指定的角色信息返回pojo对象，否则返回null
     * @throws Exception
     */
    Role findByTitleAndNotId(String title, Integer rid) throws  Exception;

    /**
     * 使用原生的jdbc查询出一个角色对应的所有的权限组编号
     * @param rid 角色编号
     * @return 如果使用原生的权限sql查询，那么返回的就是Object数组对象，要将对象转换为Map集合
     * @throws Exception
     */
    public Map<Integer, Boolean> findRoleGroups(Integer rid) throws Exception;

    /**
     *  取得管理员角色
     * @return
     * @throws Exception
     */
    List<Role> findAllAdmin() throws Exception;
}
