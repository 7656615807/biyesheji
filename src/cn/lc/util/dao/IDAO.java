package cn.lc.util.dao;

import java.util.List;
import java.util.Set;

/**定义公共的DAO操作接口标准，基本的功能包括：增加，修改全部，删除数据，根据编号查询，查询全部，分页显示，数据统计
 * @author liucong
 * @param <K>表示要操作的主键类型，由子接口实现
 * @param <V>镖师要操作的vo类型，由子接口实现
 */
public interface IDAO<K,V> {
    /**
     * 实现数据的增加操作
     * @param vo 包含了要增加数据的vo对象
     * @return 数据保存成功返回true，否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean doCreate(V vo) throws Exception;

    /**
     * 实现数据的修改操作 本册修改的是根据id进行全部字段数据的修改
     * @param vo 包含了要修改的数据的信息，一定要根据有id内容
     * @return 数据修改成功返回true 否则返回false
     * @throws Exception sql执行异常
     */
    public boolean doUpdute(V vo) throws Exception;

    /**
     * 执行数据的批量删除操作，所有要删除的数据以Set集合的形式保存
     * @param ids 包含了所有要删除的id，不包含有重复的内容
     * @return 删除成功返回true(删除的数据个数与要删除的数据个数相同），否则返回false
     * @throws Exception 执行sql异常
     */
    public boolean doRemoveBatch(Set<K> ids) throws Exception;

    /**
     * 根据编号查询指定的信息
     * @param id 要查询的编号
     * @return 如果信息存在，则将数据以vo类对象的行驶返回，如果信息数据不存在则返回null
     * @throws Exception 执行sql异常
     */
    public V findById(K id) throws Exception;

    /**
     * 查询指定数据表的全部记录，并且以合集的形式返回
     * @return 如果表中有数据，则返回的数据会封装为VO对象而后利用List集合返回，<br>
     *     如果没有数据，那么集合的长度为0（size（） == 0，不是null）
     * @throws Exception sql执行异常
     */
    public List<V> findAll() throws Exception;

    /**
     * 分页进行数据的模糊查询，查询结果以集合的形式返回
     * @param currentPage 当前所在的页
     * @param lineSize 每页显示数据的行数
     * @param column 要进行模糊查询的数据列
     * @param keyWord 模糊查询的关键字
     * @return 如果表中有数据，则所有的数据会封装为VO对象而后利用LIst集合返回，<br>
     *     如果没有数据，那么集合的长度0（size（）==0，不是null）
     * @throws Exception 执行sql异常
     */
    public List<V> findAllSplit(K currentPage, K lineSize, String column, String keyWord) throws Exception;

    /**
     * 进行模糊查询数据量的统计，如果表中没有记录统计的结果就是0
     * @param column 要进行模糊查询的数据列
     * @param keyWord 模糊查询的关键字
     * @return 返回表中的数据量，如果没有数据返回0
     * @throws Exception 执行SQL异常
     */
    public K getAllCount(String column, String keyWord) throws Exception;
}
