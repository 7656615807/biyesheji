package cn.lc.service.common;

import cn.lc.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 14:51
 **/
@Service
public interface IUserServiceCommon {
    /**
     * 用户登录功能，在本操作之中要进行如下操作:<br>
     *     <li>需要使用IUserDAO.findLogin()方法进行用户名和密码的查询，如果正确则返回POJO
     *     否则返回null，利用是否为空，判断是否登录成功
     *     </li>
     *     <li>用户根据角色编号查询查询对应的权限组信息，通过IGroupsDap.findAllByRole()</li>
     *     <li>根据每一个权限组取出所有的权限操作，这一点利用Hibernate数据级联操作</li>
     *     <li>利用对象持久态更新最后一次登录信息</li>
     * @param userid 用户名
     * @param password  密码
     * @return
     * @throws Exception
     */
    public User login(String userid, String password) throws  Exception;

    /**
     * 进行密码的修改操作
     * <li>首先利用IUserDao.findLogin()方法判断密码是否正确。</li>
     * <li>如果正确，则利用IUserDao.doUpdatePassword()方法跟新密码</li>
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     * @throws Exception
     */
    boolean updatePassword(String userId,String oldPass,String newPass) throws Exception ;

    /**
     * 进行数据的Id查询操作，使用IUserDao.findById()
     * @param userif  用户编号
     * @return  数据以pojo类对象的形式返回
     * @throws Exception
     */
    User updatePre(String userif) throws Exception ;

    /**
     * 执行用户的更新操作，使用IUserDao.doUpdate()
     * @param vo
     * @return
     * @throws Exception
     */
    boolean update(User vo) throws Exception ;
}
