package cn.lc.service.admin;


import cn.lc.pojo.Groups;

import java.util.Map;

public interface IGroupServiceAdmin {
    /**
     * 进行权限组信息的列表操作，使用分页显示
     * <li>调用IGroupsDao.findAllSplit()操作取得全部的数据信息</li>
     * <li>调用IGroupsDao.getAllCount()操作取得全部数据量</li>
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数据行
     * @param clumn 模糊查询列
     * @param keyWord   模糊查询关键字
     * @return  返回的数据主要包含两个内容，以Map集合返回组成如下：<br>
     * <li>key = allAction , value = IGroupsDao.findAllBySplit()</li>
     * <li>key = actionCount, value = IGroupsDao.getAllCount()</li>
     * @throws Exception
     */
    public Map<String, Object> list(int currentPage, int lineSize, String clumn, String keyWord) throws Exception;

    /**
     * 进行权限组的修改操作，调用IGroupsDao.doUpdate()
     * @param vo    VO对象
     * @return  更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean update(Groups vo) throws Exception;

    /**
     * 显示一个权限组的完整内容，要求同时查询出此权限组对应的所有权限信息
     * 在查询权限信息的时候可以利用对象的持久态完成
     * @param id 权限组的编号
     * @return  如果查询到内容则返回pojo对象，否则返回null
     * @throws Exception
     */
    Groups show(int id) throws Exception;
}
