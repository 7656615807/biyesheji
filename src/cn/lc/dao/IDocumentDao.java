package cn.lc.dao;

import cn.lc.pojo.Document;
import cn.lc.util.dao.IDAO;

import java.util.List;
import java.util.Set;

public interface IDocumentDao extends IDAO<Integer, Document> {
    /**
     * 根据用户的名称查询自己所发的所有文档信息
     * @param userid
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     */
    List<Document> findAllSplitByUser(String userid,Integer currentPage,Integer lineSize,String column,String keyWord);

    /**
     * 根据用户名称查询出用户所发布的文档数量
     * @param userid
     * @param column
     * @param keyWord
     * @return
     */
    Integer getAllCountByUser(String userid, String column,String keyWord);

    /**
     * 删除指定用户的文档数据
     * @param userid
     * @param ids
     * @return
     */
    boolean doRemoveBatchByUser(String userid, Set<Integer> ids);
}
