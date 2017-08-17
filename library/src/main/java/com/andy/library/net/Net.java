package com.andy.library.net;

import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 16:57
 */
public class Net {
    private static XUtils sUtils;
    static {
        sUtils=new XUtils();
    }
    public static void get(String urlPath,MyCallback callback){
        sUtils.get(urlPath,callback);
    }

    public static void get(String urlPath, Map<String,Object> map
            ,MyCallback callback){
        sUtils.get(urlPath,map,callback);
    }
    public static void post(String urlPath, Map<String,Object> params, MyCallback callback){
       sUtils.post(urlPath,params,callback);
    }
    public static void upload(String urlPath, Map<String,Object> params, List<String> listFiles, MyCallback callback){
        sUtils.upload(urlPath,params,listFiles,callback);
    }
}
