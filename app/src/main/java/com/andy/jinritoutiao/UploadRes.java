package com.andy.jinritoutiao;

import java.util.List;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/17 16:23
 */
public class UploadRes {
    private int code;
    private String message;
    private List<String>  images;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
