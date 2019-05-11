package cn.lc.service.emp.impl;

import cn.lc.dao.INoticeDao;
import cn.lc.dao.ITaskDao;
import cn.lc.service.emp.IIndexServiceEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-05-11 23:14
 **/
@Service
public class IndexServiceEmp implements IIndexServiceEmp {
    @Autowired
    private INoticeDao noticeDao;
    @Autowired
    private ITaskDao taskDao;
    @Override
    public Map<String, Integer> unReadCount(String userid) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("UnreadNum", noticeDao.getAllCountUnread(userid, 1));
        map.put("UstartNum", taskDao.getStatus0TaskCountByUser(userid));
        map.put("DoingNum", taskDao.getStatus1TaskCountByUser(userid));
        return map;
    }
}
