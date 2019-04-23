package cn.lc.service.admin.impl;

import cn.lc.dao.IGroupsDao;
import cn.lc.pojo.Action;
import cn.lc.pojo.Groups;
import cn.lc.service.admin.IGroupServiceAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-24 15:04
 **/
@Service
public class GroupsServiceAdminImpl implements IGroupServiceAdmin {

    @Resource
    private IGroupsDao groupsDao;

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String clumn, String keyWord) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("all", this.groupsDao.findAllSplit(currentPage,lineSize,clumn,keyWord));
        resultMap.put("count",this.groupsDao.getAllCount(clumn,keyWord));
        return resultMap;
    }

    @Override
    public boolean update(Groups vo) throws Exception {
        return groupsDao.doUpdute(vo);
    }

    /**
     *  默认延时加载时打开的
     *  vo.getActions().size()表示要加载多方数据
     */
    @Override
    public Groups show(int id) throws Exception {
        Groups vo = this.groupsDao.findById(id);
        if (vo != null){
            vo.getActions().size();
        }
        return vo;
    }
}
