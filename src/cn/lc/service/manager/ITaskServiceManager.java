package cn.lc.service.manager;

import cn.lc.pojo.Task;

import java.util.Map;

public interface ITaskServiceManager {
    /**
     * 增加任务
     */
    boolean insert(Task vo, String userid)throws Exception;
    /**
     * 增加前
     * key=allEmp,values=IUserDao.findAllByLevel(3) 取得所有员工
     * key=allTaskType,values=ITaskTapeDao.findAll()取得所有任务类型
     */
    Map<String, Object> insertPre(int proid)throws Exception;

    /**
     * 取得某项目所有的任务
     * key=allTask,values=ITaskDao.findAllByLevel(3) 取得所有员工
     * key=count,values=ITaskTDao.findAll()取得所有任务类型
     */
    Map<String, Object> list(Integer proid, int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 任务修改
     * @param vo
     * @return
     * @throws Exception
     */
    boolean updateByMnager(Task vo, String userId)throws Exception;

    /**
     * 查看任务信息
     * @param tid
     * @return
     * @throws Exception
     */
    Task show(Integer tid)throws Exception;

    /**
     * 取消任务
     */
    boolean updateCancleTaskByManager(Task vo, String userId)throws Exception;

    /**
     * 取得自己负责且已完成所有的任务
     */
    Map<String, Object> listFinshTaskByManager(String userId, int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
