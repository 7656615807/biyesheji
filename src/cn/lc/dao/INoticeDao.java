package cn.lc.dao;

import cn.lc.pojo.Notice;
import cn.lc.util.dao.IDAO;

import java.util.List;
import java.util.Map;

public interface INoticeDao extends IDAO<Integer,Notice> {
    /**
     * 公告级别修改
     * @param snid  公告编号
     * @param level 修改级别
     * @return  修改成功返回true 否则返回false
     * @throws Exception
     */
    boolean doupdateLevel(Integer snid,Integer level) throws Exception;

    /**
     * 取出没有阅读过的公告的编号数据，目的是在页面显示
     * @param userid    用户id
     * @param level 阅读级别
     * @return  key = 公告的编号，value = 是否阅读
     */
    Map<Integer, Boolean> findUnread(String userid, Integer level) throws  Exception;

    /**
     * 根据用户编号以及用户级别取得未读公告的数量
     */
    Integer getAllCountUnread(String userid, Integer level) throws Exception;

    /**
     * 根据公告编号以及用户的级别读取信息
     * @param id
     * @param level
     * @return
     * @throws Exception
     */
    Notice findByIdAndLevel(Integer id, Integer level) throws Exception;


    List<Notice> findAllbylevel(Integer currentPage, Integer lineSize, String column, String keyWord, int level) throws Exception;

    Integer getCountBylevel(String column, String keyWord, int level)throws Exception;
}
