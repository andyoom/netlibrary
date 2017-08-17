package com.andy.library.net;

import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 16:58
 */
public class XUtils {

    public void get(String urlPath, MyCallback callback){
        RequestParams params=new RequestParams(urlPath);
        x.http().get(params,new MyCall(callback));
    }

    public void get(String urlPath, Map<String,Object> params, MyCallback callback){
        if(params!=null&&params.size()>0){
            get(urlPath+"?"+getParam(params),callback);
        }else {
            get(urlPath,callback);
        }
    }
    public void post(String urlPath, Map<String,Object> params, MyCallback callback){
       RequestParams paramsBody=new RequestParams(urlPath);
        if(params!=null&&params.size()>0){
           for(String key : params.keySet()){
               paramsBody.addQueryStringParameter(key,params.get(key).toString());
           }
        }
        x.http().post(paramsBody,new MyCall(callback));
    }

    public void upload(String urlPath, Map<String,Object> params, List<String> listFiles, MyCallback callback){
        RequestParams paramsBody=new RequestParams(urlPath);
        if(params!=null&&params.size()>0){
            for(String key : params.keySet()){
                paramsBody.addQueryStringParameter(key,params.get(key).toString());
            }
        }
        paramsBody.setMultipart(true);
        if(listFiles!=null&&listFiles.size()>0){
            for(String imagePath : listFiles){
                paramsBody.addBodyParameter("image",new File(imagePath));
            }
        }
        x.http().post(paramsBody,new MyCall(callback));
    }

    private String getParam(Map<String, Object> params) {
        StringBuffer sb=new StringBuffer();
        for(String key :params.keySet()){
            sb.append(key);
            sb.append("=");
            sb.append(params.get(key));
            sb.append("&");
        }
        String param = sb.substring(0,sb.length()-1);
        return param;
    }


    class MyCall implements Callback.CommonCallback<String> {
        private MyCallback callback;

        public MyCall(MyCallback callback) {
            this.callback = callback;
        }

        private Class getClass(MyCallback callback){
            Class classCls = callback.getClass();
            Method[] methods= classCls.getDeclaredMethods();
            if(methods!=null&&methods.length>0) {
                for (Method method : methods) {
                    if (method.getName().equals("success")) {
                        Type[] types = method.getGenericParameterTypes();
                        if (types != null && types.length > 0) {
                            for (Type type : types) {
                                String typeName = type.toString();
                                if (!typeName.contains("java.lang.Object")) {
                                    Log.e("getClass", "getClass: 获取解析类型成功："+type);
                                    return (Class) type;
                                }
                            }
                        }
                    }
                }
            }
            Log.e("getClass", "getClass: 获取解析类型失败："+callback);
            return null;
        }

        @Override
        public void onSuccess(String result) {
            if(callback!=null){
                if(result!=null){
                    Gson gson = new Gson();
                    Class cls =getClass(callback);
                    if (cls!=null){
                        callback.success(gson.fromJson(result, cls));
                    }else {
                        callback.success(null);
                    }
                }else {
                    callback.success(null);
                }
            }
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            if(callback!=null)
                callback.fail(ex);
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    }
}
