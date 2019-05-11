package cn.lc.service.manager.impl;

import cn.lc.dao.IProjectDao;
import cn.lc.dao.ITaskDao;
import cn.lc.dao.ITasttypeDao;
import cn.lc.dao.IUserDao;
import cn.lc.pojo.Project;
import cn.lc.pojo.Task;
import cn.lc.pojo.Tasktype;
import cn.lc.pojo.User;
import cn.lc.service.manager.ITaskServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-05-11 00:52
 **/
@Service
public class TaskServiceManagerImpl implements ITaskServiceManager {
    @Autowired
    private IProjectDao projectDao;
    @Autowired
    private ITaskDao taskDao;
    @Autowired
    private ITasttypeDao tasktypeDao;
    @Autowired
    private IUserDao userDao;


    @Override
    public boolean insert(Task vo, String userid) throws Exception {
        Project project = projectDao.findById(vo.getProject().getProid());
        if (project == null)return false;
        if (!userid.equals(project.getUserByMgr().getUserid()) )return false;
        vo.setCreatedate(new Date());
        vo.setStatus(0);
        return taskDao.doCreate(vo);
    }

    @Override
    public Map<String, Object> insertPre(int proid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allEmp", userDao.findAllByLevel(3));
        map.put("allTaskType", tasktypeDao.findAll());
        map.put("project", projectDao.findById(proid));
        return map;
    }

    @Override
    public Map<String, Object> list(Integer proid, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allTask", taskDao.findTaskByProject(proid, currentPage, lineSize, column, keyWord));
        map.put("count", taskDao.getTaskCountByProject(proid, column, keyWord));
        return map;
    }

    @Override
    public boolean updateByMnager(Task vo, String userId) throws Exception {
        Task task = taskDao.findById(vo.getTid());
        if (!userId.equals(task.getUserByCreator().getUserid()))return false;
        vo.setLastupdatedate(new Date());
        return taskDao.doUpdute(vo);
    }

    @Override
    public Task show(Integer tid) throws Exception {
        return taskDao.findById(tid);
    }

    @Override
    public boolean updateCancleTaskByManager(Task vo, String userId) throws Exception {
        User user = userDao.findById(userId);
        if (user.getLevel() > 1) {
            Task task = taskDao.findById(vo.getTid());
            if (!userId.equals(task.getUserByCreator().getUserid())) return false;
        }
        vo.setUserByCanceler(user);
        vo.setStatus(2);
        vo.setLastupdatedate(new Date());
        return taskDao.updateByCancle(vo);
    }

    @Override
    public Map<String, Object> listFinshTaskByManager(String userId, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allTask", taskDao.findFinishTaskBymanager(userId, currentPage, lineSize, column, keyWord));
        map.put("count", taskDao.getFinishTaskCountByManager(userId, column, keyWord));
        return map;
    }
}
