package cn.lc.dao;

import cn.lc.pojo.User;
import cn.lc.util.dao.IDAO;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 14:31
 **/

public interface IUserDao extends IDAO<String,User> {
    /**
     * 登录验证的检查方法
     * @param userid 用户名
     * @param password 密码
     * @return  成功返回pojo对象，是返回null
     * @throws Exception
     */
    public User findLogin(String userid, String password) throws Exception;

    /**
     * 进行密码的修改操作
     * @param vo 包含了用户名和密码的信息
     * @return  修改成功了返回ture，否则返回false
     * @throws Exception
     */
    boolean doUpdatePassword(User vo)throws Exception ;

    /**
     * 查询出所有的用户
     * @return
     * @throws Exception
     */
    public List<User> findAllUser(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 查询出所有的管理员信息
     * @return
     * @throws Exception
     */
    List<User> findAllAdmin(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 修改用户角色
     * @param vo    包含了用户编号以及角色编号
     * @return
     * @throws Exception
     */
    boolean doUpdateRole(User vo) throws Exception;

    /**
     * 更新基础信息操作，不维护照片
     * @param vo    包含了要修改用户的基础信息对象
     * @return
     * @throws Exception
     */
    boolean doUpdateInfo(User vo) throws Exception;

    /**
     * 取得所有的用户数量
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    Integer getAllUserCount(String column,String keyWord) throws Exception;

    /**
     * 取得所有的管理员数量
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    Integer getAllAdminCount(String column,String keyWord) throws Exception;

    /**
     *  根据用户的锁定状态，来查询所有的用户信息
     */
    public List<User> findAllUserByLock(Integer lockflag, Integer currentPage, Integer lineSize, String column,String keyword) throws Exception;

    /**
     * 查询所有的用户数量
     */
    Integer getAllUserCountByLock(Integer lockflag, String column, String keyword) throws Exception;

    /**
     * 进行批量用户的锁定标记操作
     */
    boolean doUpdateLock(Set<String> ids, Integer lock) throws Exception;

    /**
     * 根据用户的等级查出所有的用户
     */
    List<User> findAllByLevel(int level) throws Exception;

}
