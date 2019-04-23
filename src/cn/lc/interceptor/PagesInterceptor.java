package cn.lc.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-05 22:55
 **/

public class PagesInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //进入到pages目录下的可能有三种保存信息：admin,admin，emp
        Map<String,Object> map = actionInvocation.getInvocationContext().getSession();
        ServletActionContext.getRequest().setAttribute("msg","你还未登录，请先登录！");
        ServletActionContext.getRequest().setAttribute("url","/login.jsp");
        if (map.get("admin") == null){ //现在没有admin的属性
                if (map.get("admin") == null ){
                    if (map.get("emp") == null ){
                        return "forward.page";
                    }else {
                        return actionInvocation.invoke();
                    }
                }else {
                    return actionInvocation.invoke();
                }
        }else {
            return actionInvocation.invoke();
        }
    }
}
