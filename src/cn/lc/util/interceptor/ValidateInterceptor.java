package cn.lc.util.interceptor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.apache.struts2.ServletActionContext;

/**
 * 服务层数据拦截器
 */

@SuppressWarnings("serial")
public class ValidateInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Object actionObject = arg0.getAction();
		//System.out.println(arg0.getInvocationContext().getParameters());
		String uri=ServletActionContext.getRequest().getRequestURI();
		StringBuffer wholeUrl = new StringBuffer(uri);
		Iterator<Map.Entry<String,Object>> iterator = arg0.getInvocationContext().getParameters().entrySet().iterator();
		int x = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Object> map = iterator.next();
			if (x == 0){
				wholeUrl.append("?");
			}else {
				wholeUrl.append("&");
			}
			String str [] =(String [])map.getValue();
			wholeUrl.append(map.getKey()).append("=");
			for (String s : str){
				wholeUrl.append(s);
			}
			x++;
		}
		ServletActionContext.getRequest().setAttribute("wholeUrl",wholeUrl);
		System.out.println(wholeUrl);
		if (uri !=null) {
			uri = uri.substring(uri.lastIndexOf("!")+1,uri.lastIndexOf("."));
			//System.out.println("uri:"+uri);
			String fieldName= uri+"Rule";
			try {
			Field fieldRule = actionObject.getClass().getDeclaredField(fieldName);
			fieldRule.setAccessible(true);
			String rule = (String)fieldRule.get(actionObject);
			System.out.println("操作的验证规则"+rule);
			if(ValidateUtil.validate(actionObject, rule,
					arg0.getInvocationContext().getParameters())){
					System.out.println("验证通过");
				 return arg0.invoke();
			}
			else {
				System.out.println("验证失败");
				return uri+"VF";
			}			
		} catch (Exception e) {
		}
		}
		return arg0.invoke();
		
	}

}
