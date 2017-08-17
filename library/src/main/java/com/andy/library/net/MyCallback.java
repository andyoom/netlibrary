package com.andy.library.net;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 17:09
 */
public interface MyCallback<T> {
    void success(T t);
    void fail(Throwable ex);
}
