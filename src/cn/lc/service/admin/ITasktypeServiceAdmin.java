package cn.lc.service.admin;

import cn.lc.pojo.Doctype;
import cn.lc.pojo.Tasktype;

import java.util.List;

public interface ITasktypeServiceAdmin {
    /**
     * 定义任务类型的增加操作
     * @param vo
     * @return
     * @throws Exception
     */
    boolean insert(Tasktype vo) throws Exception;

    /**
     * 任务类型修改操作
     * @param vo
     * @return
     * @throws Exception
     */
    boolean update(Tasktype vo) throws Exception;

    /**
     * 任务类型的列表显示
     * @return
     * @throws Exception
     */
    List<Tasktype> list() throws Exception;
}
