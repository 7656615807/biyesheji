package cn.lc.service.emp.impl;

import cn.lc.dao.INoticeDao;
import cn.lc.dao.IUserDao;
import cn.lc.dao.IUserNoticeDao;
import cn.lc.pojo.Notice;
import cn.lc.pojo.NoticeDto;
import cn.lc.pojo.User;
import cn.lc.pojo.UserNotice;
import cn.lc.service.emp.INoticeServiceEmp;
import cn.lc.service.manager.INoticeServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description:
 * @author: lc
 * @date: 2019-05-09 00:01
 **/
@Service
public class NoticeServiceEmpImpl implements INoticeServiceEmp {

    @Autowired
    private INoticeDao noticeDao;
    @Autowired
    private IUserNoticeDao userNoticeDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public Map<String, Object> list(String userid, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<Notice> list = noticeDao.findAllbylevel(currentPage, lineSize, column, keyWord, 1);
        result.put("allNotices", list);
        result.put("count", noticeDao.getCountBylevel(column, keyWord, 1));
        result.put("unread", noticeDao.findUnread(userid, 1));
        return result;
    }

    @Override
    public int unReadCount(String userId, Integer level) throws Exception {
        return noticeDao.getAllCountUnread(userId, level);
    }


    @Override
    public NoticeDto show(Integer snid, String userid) throws Exception {
        NoticeDto noticeDto = new NoticeDto();
        Notice notice = noticeDao.findByIdAndLevel(snid, 1);
        if (notice == null) return noticeDto;
        boolean flag = userNoticeDao.findByExists(userid, snid);
        if (!flag){
            UserNotice userNotice = new UserNotice();
            userNotice.setRdate(new Date());
            userNotice.setSnid(snid);
            userNotice.setUserid(userid);
            //之前因为事务只读害死人
            userNoticeDao.doCreate(userNotice);
            notice.setRnum(notice.getRnum() + 1);
            noticeDto.setFirstRead(true);
        }
        noticeDto.setNotice(notice);
        return noticeDto;
    }
}
