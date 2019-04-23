package cn.lc.service.admin;

import cn.lc.pojo.Document;

import java.util.Map;
import java.util.Set;

public interface IDocumentServiceAdmin {
    /**
     * 增加文档前的查询操作
     * <li>调用IDoctypeDao.findAll()查询出全部类型</li>
     * @return  数据以Map集合的方式返回
     * <li>key=allDoctypes,value=IDoctypeDao.findAll()</li>
     * @throws Exception
     */
    Map<String,Object> insertPre() throws Exception;

    /**
     * 文档类型的增加操作，调用IDocument.doCreate(),增加前需要处理好发布日期
     * @param vo
     * @return  增加成功返回true，否则false
     * @throws Exception
     */
    boolean insert(Document vo) throws  Exception;

    /**
     * 文档增加前的查询操作，要执行如下功能：<br>
     *     <li>调用IDoctypeDao.findAll()查询全部类型</li>
     *     <li>调用IDocumentDao.findById()根据编号查询出指定的文档内容</li>
     * @return vo 数据以Map集合的方式返回
     * <li>key=Document,value=IDocumentDao.findById()</li>
     * <li>key=allDoctypes,value=IDoctypeDao.findAll()</li>
     * @throws Exception
     */
    Map<String,Object> updatePre(int did)throws Exception;

    /**
     * 文档更新操作
     * @param vo
     * @return  更新成功返回true，否则false
     * @throws Exception
     */
    boolean update(Document vo) throws Exception;

    /**
     * 文档数据的删除操作
     * @param ids
     * @return  删除成功返回true，否则false
     * @throws Exception
     */
    boolean delete(Set<Integer> ids) throws Exception;

    /**
     * 分页查询出所有的文档数据
     * <li>显示出文档类型的名字，调用IDoctyoeDao.findAll()</li>
     * <li>分页查询的文档数据，调用IDocumentDao.findAllSplit()</li>
     * <li>统计查询的个数，调用IDocumentDao.getAllcount()</li>
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return  vo 数据以Map集合的方式返回
     * <li>key=allDocuments,value=IDocumentDao.findAllSplit()</li>
     * <li>key=allDoctypes,value=IDoctypeDao.findAll()</li>
     * <li>key=documentCount,value=IDocumentDao.getAllcount()</li>
     * @throws Exception
     */
    Map<String, Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;
}
