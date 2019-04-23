package cn.lc.util.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-24 22:43
 **/

public class EncodingInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        return actionInvocation.invoke();
    }
}
