package cn.lc.dao;

import cn.lc.pojo.UserNotice;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-07 17:43
 **/

public interface IUserNoticeDao {
    /**
     * 增加用户的阅读记录信息
     * @param vo
     * @return
     * @throws Exception
     */
    boolean doCreate(UserNotice vo) throws Exception;

    /**
     * 判断用户的月的记录是否已经存在
     * @param userid
     * @param snid
     * @return true渡过
     * @throws Exception
     */
    boolean findByExists(String userid,Integer snid)throws Exception;
}
