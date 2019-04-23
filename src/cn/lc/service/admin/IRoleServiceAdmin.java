package cn.lc.service.admin;

import cn.lc.pojo.Role;

import java.util.List;
import java.util.Map;


public interface IRoleServiceAdmin {
    /**
     * 角色增加钱的数据查询，主要执行如下操作：<br>
     *     <li>因为需要权限组，所以使用IGroupsDao.findAll()查询</li>
     * @return  返回的数据以Map集合保存，包含如下内容：<br>
     *     <li>key = allGroups, value = IGroupsDao.findAll()</li>
     * @throws Exception
     */
    Map<String, Object> insertPre() throws Exception;

    /**
     * 角色修改前的数据查询准备，主要执行如下的操作：<br>
     *     <li>因为需要权限组，所以使用IGroupsDao.findAll()查询</li>
     *     <li>根据角色编号查询出已有的角色信息，使用IRoleDao.findById()查询</li>
     *     <li>为了可以进行数据的回填显示，需要使用IroleDao.findROleGroups()查询每个角色对应的权限组编号</li>
     * @param rid 角色id
     * @return  返回的数据以map集合保存，包含如下内容：<br>
     *     <li>key = allGroups , value = IGroupsDao.findAll()</li>
     *     <li>key = role,value = IRoleDao.findById()</li>
     * @throws Exception
     */
    Map<String, Object> updatePre(int rid) throws Exception;

    /**
     * 角色信息的列表显示，本功能执行如下的操作：<br>
     *     <li>查询出所有的角色信息，调用IRoleDao.findAllSplit()</li>
     *     <li>统计出所有的角色信息数量，调用IRoleDao.getAllCount()</li>
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数量
     * @param column    要模糊查的字段
     * @param keyWord   模糊查询关键字
     * @return  返回的结果以Map集合形式返回，包含如下的内容：
     * @throws Exception
     */
    Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 角色信息修改操作，修改的时候操作如下：<br>
     *     <li>要保证修改的角色名称与其它名称不重复，</li>
     *     <li>调用IRoleDao.findByTitleAndNotId()</li>
     *     <li>IRoleDao.doUpdate()方法自动维护关联数据</li>
     * @param vo    包含了要修改数据的Role对象，同时保持有所有的权限组信息
     * @return  修改成功返回true，否则返回false
     * @throws Exception
     */
    boolean update(Role vo) throws Exception;

    /**
     * 角色数据的增加操作，增加时自动维护权限组关系，包含如下操作：<br>
     *     <li>要保证修改的角色名称与其它名称不重复，调用IRoleDao.findByTitle()</li>
     *     <li>IRoleDao.doCreate()方法自动维护关联数据</li>
     * @param vo    要增加的对象
     * @return
     * @throws Exception
     */
    boolean insert(Role vo) throws Exception;

    /**
     * 检查指定的角色名称是否存在，调用IRoleDao.findByTitle()
     * @param title
     * @return
     * @throws Exception
     */
    boolean checkTitle(String title) throws Exception;

    /**
     * 检查排除制定的角色之外的其他角色名称是否存在，调用IRoleDao.findByTitleAndNotId().
     * @param title 要检查的角色名称
     * @param rid 要排除的角色编号
     * @return
     * @throws Exception
     */
    boolean checkTitle(String title, int rid) throws Exception;

}
