package cn.lc.service.emp;

import cn.lc.pojo.Notice;
import cn.lc.pojo.NoticeDto;

import java.util.Map;

public interface INoticeServiceEmp {

    /**
     * 公告的列表显示，本功能执行如下的操作：<br>
     *     <li>查询出所有的角色信息，调用INoticeDao.findAllSplit()</li>
     *     <li>统计出所有的角色信息数量，调用INoticeDao.getAllCount()</li>
     *     <li>读取所有的未读公告信息，使用IINoticeDao.findUnread()</li>
     * @param currentPage   当前所在页
     * @param lineSize  每页显示的数量
     * @param column    要模糊查的字段
     * @param keyWord   模糊查询关键字
     * @return  返回的结果以Map集合形式返回，包含如下的内容：
     * @throws Exception
     */
    Map<String, Object> list(String userid, int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 取得未读公告数量
     * @param userId
     * @param level
     * @return
     * @throws Exception
     */
    int unReadCount(String userId, Integer level)throws Exception;

    /**
     * 进行数据的异步显示，如果用户没有读取过这条公告，则使用IUserNoticeDao.daCreate()新增保存，同时进行公告阅读量的增加,利用持久态操作。
     * @param snid
     * @param userid
     * @return
     * @throws Exception
     */
    NoticeDto show(Integer snid, String userid) throws Exception;
}
