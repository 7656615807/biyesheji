package cn.lc.dao;

import cn.lc.pojo.Notice;
import cn.lc.util.dao.IDAO;

public interface INoticeDao extends IDAO<Integer,Notice> {
    /**
     * 公告级别修改
     * @param snid  公告编号
     * @param level 修改级别
     * @return  修改成功返回true 否则返回false
     * @throws Exception
     */
    boolean doupdateLevel(Integer snid,Integer level) throws Exception;
}
