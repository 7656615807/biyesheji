package cn.lc.service.admin;

import cn.lc.pojo.Notice;

import java.util.Map;
import java.util.Set;

public interface INoticeServiceAdmin {
    /**
     * 公告的发布操作，只有管理员可以进行公告的发布，业务逻辑如下<br>
     *     <li>利用IUserDao.findById()判断操作用户是不是管理员，返回结果为0或者1</li>
     *     <li>利用INoticeDao.doCreate()进行公告的保存</li>
     * @param vo
     * @return  成功则返回true，如果不是管理员或者保存失败则返回false
     * @throws Exception
     */
    boolean insert(Notice vo) throws  Exception;

    /**
     * 公告修改前的数据查询操作
     * @param snid
     * @return
     * @throws Exception
     */
    Notice updatePre(Integer snid)throws Exception;

    /**
     * 公告的维护操作，只有管理员才可以进行修改业务逻辑如下<br>
     *     <li>利用IUserDao.findById()判断操作用户是不是管理员，返回结果为0或者1</li>
     *      <li>利用INoticeDao.doupdate()进行公告的修改</li>
     * @param vo
     * @return
     * @throws Exception
     */
    boolean update(Notice vo)throws Exception;

    /**
     * 进行公告的删除
     * @param ids
     * @return
     * @throws Exception
     */
    boolean delete(Set<Integer> ids)throws Exception;

    /**
     * 公告级别的修改操作
     * @param snid
     * @param level
     * @return
     * @throws Exception
     */
    boolean updateLevel(int snid,int level)throws Exception;
    /**
     * 公告的列表显示，本功能执行如下的操作：<br>
     *     <li>查询出所有的角色信息，调用INoticeDao.findAllSplit()</li>
     *     <li>统计出所有的角色信息数量，调用INoticeDao.getAllCount()</li>
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数量
     * @param column    要模糊查的字段
     * @param keyWord   模糊查询关键字
     * @return  返回的结果以Map集合形式返回，包含如下的内容：
     * @throws Exception
     */
    Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
