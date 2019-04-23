package cn.lc.service.admin;

import cn.lc.pojo.Action;

import java.util.Map;

public interface IActionServiceAdmin {
    /**
     * 数据的列表显示操作，使用分页显示
     * <li>调用IactionDao.findAllSplit()操作取得全部的数据信息</li>
     * <li>调用IActionDao.getAllCount()操作取得全部数据量</li>
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数据行
     * @param clumn 模糊查询列
     * @param keyWord   模糊查询关键字
     * @return  返回的数据主要包含两个内容，以Map集合返回组成如下：<br>
     * <li>key = allAction , value = IActionDao.findAllBySplit()</li>
     * <li>key = actionCount, value = IActionDao.getAllCount()</li>
     * @throws Exception
     */
    public Map<String, Object> list(int currentPage,int lineSize, String clumn, String keyWord) throws Exception;

    /**
     * 进行权限数据的更新操作
     * @param vo    包含有新权限内容的VO对象
     * @return  更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean update(Action vo) throws Exception;
}
