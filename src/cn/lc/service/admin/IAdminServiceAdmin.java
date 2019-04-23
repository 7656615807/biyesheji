package cn.lc.service.admin;

import cn.lc.pojo.User;


import java.util.Map;
import java.util.Set;

public interface IAdminServiceAdmin {
    /**
     * 表示在管理员增加前取出所有管理员的角色，执行如下操作：<br>
     *     <li>调用IROleDao.findAllAdmin</li>
     * @return  返回结果以Map形式取得，包含的数据如下：
     * <li>key = allRoles,value = IRoleDao.findAllAdmin()</li>
     * @throws Exception
     */
    Map<String,Object> insertPre() throws Exception;

    /**
     *  管理员增加，但是在本操作中必须要注意一下情况：<br>
     *      <li>只有超级管理员可以增加新的管理员，那么必须根据用户的编号判断当前操作用户是否为超级管理员，调用IUserDao.findById()</li>
     *      <li>保证用户名称userid不能够重复，所以需要使用IUserDao.findById()确定用户是否已经存在</li>
     *      <li>如果用户不存在就进行保存，但是级别只能为1</li>
     * @param vo   包含新的管理员的信息对象
     * @param userid    当前操作的管理员的编号
     * @return
     * @throws Exception
     */
    boolean insert(User vo, String userid) throws Exception;

    /**
     表示在管理员修改前取出所有相关信息，执行如下操作：<br>
     *     <li>调用IROleDao.findAllAdmin()取得所有管理员角色</li>
     *     <li>调用IUserDao.findById()取得当前管理员的信息</li>
     * @param userid
     * @return
     * @throws Exception
     */
    Map<String, Object> updatePre(String userid) throws Exception;

    /**
     * 修改管理员信息，本功能执行的操作步骤如下：<br>
     *     <li>如果要修改的数据时超级管理员，那么不允许修改，调用IUserDao.findById()确定</li>
     *     <li>如果不是超级管理员，是普通管理员，则执行修改操作</li>
     * @param vo 要修改的用户数据
     * @return  修改成功返回true，否则返回false
     * @throws Exception
     */
    boolean update(User vo) throws Exception;

    /**
     * 用户数据的批量删除操作，如果没有数据则返回false，如果有数据则调用IUserDao.doRemoveBatch()方法
     * @param ids 包含了所有要删除的用户编号
     * @return 删除成功返回true 否则返回false
     * @throws Exception
     */
    boolean delete(Set<String> ids) throws Exception;

    /**
     * 修改用户的角色信息，调用IUserDao.doUpdateRole()方法
     * @param vo 包含有用户编号以及角色编号
     * @return  修改成功返回True 否则返回false
     * @throws Exception
     */
    boolean updateRole(User vo) throws Exception;

    /**
     * 管理员修改用户的密码操作，调用IUserDao.doUpdatePassword()
     * @param vo 包含有用户编号以及新的密码的信息
     * @return 修改成功返回true，否则返回false
     * @throws Exception
     */
    boolean updatePassword(User vo) throws Exception;

    /**
     * 检查用户编号是否已经存在，调用IUserDao.findById()
     * @param userId 要检查的用户编号
     * @return 如果不存在则返回true，否则返回false
     * @throws Exception
     */
    boolean checkUser(String userId) throws Exception;

    /**
     * 进行数据的分页显示操作
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数据量
     * @param column    模糊查询列
     * @param keyWord   模糊查询字段
     * @return  返回的内容
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;

    /**
     * 查询一个用户的完整信息，这个信息包含有管理员的权限，角色，权限组
     * @param id
     * @return
     * @throws Exception
     */
    User show(User vo) throws Exception;
}
