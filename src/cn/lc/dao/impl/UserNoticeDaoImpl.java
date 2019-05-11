package cn.lc.dao.impl;

import cn.lc.dao.IUserNoticeDao;
import cn.lc.pojo.UserNotice;
import cn.lc.util.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: lc
 * @date: 2019-04-07 17:46
 **/
@Component
public class UserNoticeDaoImpl extends AbstractDAOImpl implements IUserNoticeDao {
    @Override
    public boolean doCreate(UserNotice vo) throws Exception {
        String sql = "insert into user_notice values(?,?,?)";
        Query query = getSQLQuery(sql);
        query.setParameter(0, vo.getSnid());
        query.setParameter(1, vo.getUserid());
        query.setParameter(2, vo.getRdate());
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean findByExists(String userid, Integer snid) throws Exception {
        String sql = "select count(*) from user_notice where userid=? and snid=?";
        Query query = getSQLQuery(sql);
        query.setParameter(0,userid);
        query.setParameter(1,snid);
        return Integer.valueOf(query.uniqueResult().toString()) > 0;
    }
}
