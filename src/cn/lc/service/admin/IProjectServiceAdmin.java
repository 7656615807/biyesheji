package cn.lc.service.admin;

import cn.lc.pojo.Document;
import cn.lc.pojo.Project;

import java.util.Map;
import java.util.Set;

public interface IProjectServiceAdmin {
    /**
     * 增加项目的查询操作
     * <li>调用IUserDao.findAllByLevel(2)查询出所有的项目经理</li>
     * @return  数据以Map集合的方式返回
     * <li>key=allManagers,value=IUserDao.findAllByLevel(2)</li>
     * @throws Exception
     */
    Map<String,Object> insertPre() throws Exception;

    /**
     * 实现项目的增加操作，调用IUserDao.findById(),判断当前操作者是不是管理员
     * @param vo
     * @return  增加成功返回true，否则false
     * @throws Exception
     */
    boolean insert(Project vo) throws  Exception;

    /**
     * 项目修改前的查询操作，要执行如下功能：<br>
     *     <li>调用IProjectDao.findById()查询出指定Id的文档类型</li>
     *     <li>调用IUserDao.findAllByLevel(2)查询出所有的项目经理</li>
     * @return vo 数据以Map集合的方式返回
     * <li>key=project,value=IProjectDao.findById()</li>
     * <li>key=allManagers,value=IUserDao.findAllByLevel()</li>
     * @throws Exception
     */
    Map<String,Object> updatePre(int did)throws Exception;

    /**
     *  项目修改操作，调用IUserDao.findById(),判断当前操作者是不是管理员
     * @param vo
     * @return  更新成功返回true，否则false
     * @throws Exception
     */
    boolean update(Project vo) throws Exception;


    /**
     * 分页查询出所有的数据
     * <li>分页查询的项目数据，调用IProjectDao.findAllSplit()</li>
     * <li>统计查询的个数，调用IProjectDao.getAllcount()</li>
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return  vo 数据以Map集合的方式返回
     * <li>key=allProjects,value=IProjectDao.findAllSplit()</li>
     * <li>key=projectCount,value=IProjectDao.getAllcount()</li>
     * @throws Exception
     */
    Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
