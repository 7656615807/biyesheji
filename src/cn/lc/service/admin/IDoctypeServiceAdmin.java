package cn.lc.service.admin;

import cn.lc.pojo.Doctype;

import java.util.List;

public interface IDoctypeServiceAdmin {
    /**
     * 定义文档类型的增加操作
     * @param vo
     * @return
     * @throws Exception
     */
    boolean insert(Doctype vo) throws Exception;

    /**
     * 文档类型修改操作
     * @param vo
     * @return
     * @throws Exception
     */
    boolean update(Doctype vo) throws Exception;

    /**
     * 文档类型的列表显示
     * @return
     * @throws Exception
     */
    List<Doctype> list() throws Exception;
}
