package cn.lc.service.emp.impl;

import cn.lc.dao.IProjectDao;
import cn.lc.dao.ITaskDao;
import cn.lc.dao.ITasttypeDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.Task;
import cn.lc.pojo.User;
import cn.lc.service.emp.ITaskServiceEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-05-11 21:29
 **/
@Service
public class TaskServiceImpl implements ITaskServiceEmp {

    @Autowired
    private ITaskDao taskDao;

    @Override
    public boolean editStatus(Integer tid, String userId) throws Exception {
        Task task = taskDao.findById(tid);
        if (!userId.equals(task.getUserByReceiver().getUserid())) return false;
        return taskDao.updateStartTask(tid);
    }

    @Override
    public boolean editfinishTask(Task vo, String userId) throws Exception {
        Task task = taskDao.findById(vo.getTid());
        if (!userId.equals(task.getUserByReceiver().getUserid())) return false;
        vo.setLastupdatedate(new Date());
        vo.setStatus(3);
        return taskDao.updateFinishTask(vo);
    }

    @Override
    public Map<String, Object> listTaskByEmp(String userId, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allTask", taskDao.findTaskByEmp(userId, currentPage, lineSize, column, keyWord));
        map.put("count", taskDao.getTaskCountByEmp(userId, column, keyWord));
        return map;
    }
}
