package cn.lc.dao;

import cn.lc.pojo.Task;
import cn.lc.util.dao.IDAO;

import java.util.List;

public interface ITaskDao extends IDAO<Integer, Task> {
    /**
     * 取消任务
     */
    boolean updateByCancle(Task vo)throws Exception;

    /**
     * 取得管理员负责并且已经完成的任务
     */
    List<Task> findFinishTaskBymanager(String userid,Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 取得自己负责并且已经完成的任务总数
     */
    Integer getFinishTaskCountByManager(String userid, String column, String keyWord) throws Exception;


    /**
     * 开始任务
     */
    boolean updateStartTask(int tid)throws Exception;

    /**
     * 完成任务
     */
    boolean updateFinishTask(Task vo)throws Exception;

    /**
     * 取得员工负责的任务
     */
    List<Task> findTaskByEmp(String userid,Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 取得员工负责并且已经完成的任务总数
     */
    Integer getTaskCountByEmp(String userid, String column, String keyWord) throws Exception;

    /**
     * 取得未开始的数量
     */
    Integer getStatus0TaskCountByUser(String userid) throws Exception;
    /**
     * 取得进行中的数量
     */
    Integer getStatus1TaskCountByUser(String userid) throws Exception;

    /**
     * 取得某项目所有的任务
     */
    List<Task> findTaskByProject(Integer proid,Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 取得某项目所有的任务总数
     */
    Integer getTaskCountByProject(Integer proid, String column, String keyWord) throws Exception;
}
