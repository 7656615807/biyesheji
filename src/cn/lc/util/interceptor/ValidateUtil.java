package cn.lc.util.interceptor;


import java.lang.reflect.Method;
import java.util.Map;

public class ValidateUtil {
	/**
	 * 进行数据验证操作的方法
	 * @param 	actionObject 操作的action
	 * @param 	rule 验证的规则
	 * @param 	parms 输入的参数
	 * @return验证是否通过
	 */
	public static boolean validate(Object actionObject,String rule,
			Map<String, Object> parms){
		/*Iterator<Map.Entry<String,Object>> iterator = parms.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> map = iterator.next();
			String str [] =(String [])map.getValue();
			System.out.println(map.getKey()+"="+Arrays.toString(str));
		}
*/		
		boolean flag = false;
		try {
			//保存错误信息
			Method addfieldErrorMethod = actionObject.getClass().getMethod(
					"addFieldError", String.class,String.class);
			Method textMethod = actionObject.getClass().getMethod(
					"getText", String.class);
			String result[] =rule.split("\\|");
			for (int i = 0; i < result.length; i++) {
				//System.out.println(result[i]);
				String temp[] = result[i].split(":");
				//System.out.println(temp[0]);
				String parmValue[] = (String []) parms.get(temp[0]);
				String text=null;
				for (int j = 0; j < parmValue.length; j++) {
					switch (temp[1]) {
					case "string":
						flag=ValidateUtil.validateString(parmValue[j]);
						//System.out.println("string+"+flag);
						if(!flag){
							 text = (String)textMethod.invoke(actionObject,
									"string.validate.error.msg");
						}
						break;
					case "int":
						flag=ValidateUtil.validateInt(parmValue[j]);
						if(!flag){
							 text = (String)textMethod.invoke(actionObject,
									"number.validate.error.msg");
						}
						break;
					case "double":
						flag=ValidateUtil.validateDouble(parmValue[j]);
						if(!flag){
							 text = (String)textMethod.invoke(actionObject,
									"number.validate.error.msg");
						}
						break;
					case "date":
						flag=ValidateUtil.validateDate(parmValue[j]);
						if(!flag){
							 text = (String)textMethod.invoke(actionObject,
									"date.validate.error.msg");
						}
						break;
					}
					if (!flag) {
						addfieldErrorMethod.invoke(actionObject, temp[0],text);
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return flag;
	}
	public static boolean validateString(String str){
		if(str ==null||"".equals(str)){
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateInt(String str){
		if(validateString(str)){
			return str.matches("\\d+");
		}else {
			return false;
		}
	}
	public static boolean validateDouble(String str){
		if(validateString(str)){
			return str.matches("\\d+(\\.\\d+)?");
		}else {
			return false;
		}
	}
	public static boolean validateDate(String str){
		if(validateString(str)){
			if (str.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return true;
			}else {
				return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
			}
		}
		return false;
	}
}
