package cn.lc.util.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.alibaba.fastjson.util.TypeUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JSON操作工具
 */
public class JsonOperUtil {

    public static <T> T get(String str, Class<T> clazz){
        if( null == str ){
            return null;
        }
        return JSONObject.parseObject(str, clazz);
    }

    public static <T,K> HashMap<T, K> map(String str){
        if( StringUtils.isEmpty(str) ){
            return new HashMap<>();
        }
        return JSONObject.parseObject(str, new TypeReference<HashMap<T, K>>(){});
    }

    public static <T> List<T> list(String str, Class<T> clazz){
        if( StringUtils.isEmpty(str) ){
            return new ArrayList<>();
        }
        return JSONObject.parseArray(str, clazz);
    }

    public static <T> String str(T t){
        return JSONObject.toJSONString(t);
    }

    public static <T,K> HashMap<T,K> map(JSONObject obj, Class<T> kClass, Class<K> vClass){
        HashMap<T, K> m = new HashMap<>();
        if(null == obj){
            return m;
        }
        for(String key : obj.keySet()){
            T k = TypeUtils.castToJavaBean(key, kClass);
            K v = obj.getObject(key, vClass);
            m.put(k, v);
        }
        return m;
    }

    public static <T> ArrayList<T> list(JSONArray items, Class<T> clazz){
        ArrayList<T> li = new ArrayList<>();
        if(null == items){
            return li;
        }
        for(int i = 0; i < items.size(); i++){
            T t = items.getObject(i, clazz);
            li.add(t);
        }
        return li;
    }

}
