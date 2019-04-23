package cn.lc.service.admin.impl;

import cn.lc.dao.IActionDao;
import cn.lc.pojo.Action;
import cn.lc.service.admin.IActionServiceAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-17 12:25
 **/
@Service
public class ActionServiceAdminImpl implements IActionServiceAdmin {

    @Resource
    private IActionDao actionDao;

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String clumn, String keyWord) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("allActions", this.actionDao.findAllSplit(currentPage,lineSize,clumn,keyWord));
        resultMap.put("actionCount",this.actionDao.getAllCount(clumn,keyWord));
        return resultMap;
    }

    @Override
    public boolean update(Action vo) throws Exception {
        return actionDao.doUpdute(vo);
    }
}
