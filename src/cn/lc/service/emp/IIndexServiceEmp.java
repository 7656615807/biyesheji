package cn.lc.service.emp;

import java.util.Map;

public interface IIndexServiceEmp {
    /**
     * 返回统计信息
     * @param userid
     * @return  未读数量公告，未完成的任务数量，刚刚分配的任务数量
     * @throws Exception
     */
    Map<String, Integer> unReadCount(String userid)throws Exception;

}
