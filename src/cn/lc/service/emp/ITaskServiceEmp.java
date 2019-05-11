package cn.lc.service.emp;

import cn.lc.pojo.Task;

import java.util.Map;

public interface ITaskServiceEmp {
    /**
     * 开始任务
     */
    boolean editStatus(Integer tid, String userId)throws Exception;

    /**
     * 完成任务
     */
    boolean editfinishTask(Task task, String userId)throws Exception;

    /**
     * 取得所有自己任务
     */
    Map<String, Object> listTaskByEmp(String userId, int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
