package cn.lc.service.admin.impl;

import cn.lc.dao.INoticeDao;
import cn.lc.dao.IUserDao;

import cn.lc.pojo.Notice;
import cn.lc.pojo.User;
import cn.lc.service.admin.INoticeServiceAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-07 19:21
 **/
@Service
public class NoticeServiceImpl implements INoticeServiceAdmin {
    @Resource
    private IUserDao userDao;
    @Resource
    private INoticeDao noticeDao;
    @Override
    public boolean insert(Notice vo) throws Exception {
        User user = userDao.findById(vo.getUser().getUserid());
        if (user.getLevel()!=0 && user.getLevel()!=1){
            return false;
        }
        vo.setPubdate(new Date());
        vo.setRnum(0);
        return noticeDao.doCreate(vo);
    }

    @Override
    public Notice updatePre(Integer snid) throws Exception {
        return noticeDao.findById(snid);
    }

    @Override
    public boolean update(Notice vo) throws Exception {
        User user = userDao.findById(vo.getUser().getUserid());
        if (user.getLevel()!=0 && user.getLevel()!=1){
            return false;
        }
        return noticeDao.doUpdute(vo);
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        if (ids.size()<=0){
            return false;
        }
        return noticeDao.doRemoveBatch(ids);
    }

    @Override
    public boolean updateLevel(int snid, int level) throws Exception {
        return noticeDao.doupdateLevel(snid,level);
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allNotices",this.noticeDao.findAllSplit(currentPage,lineSize,column,keyWord));
        map.put("noticeCount",this.noticeDao.getAllCount(column,keyWord));
        return map;
    }
}
