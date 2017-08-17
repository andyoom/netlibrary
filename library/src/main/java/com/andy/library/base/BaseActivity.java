package com.andy.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 16:11
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        loadData();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView() ;

    /**
     * 加载网络数据
     */
    protected abstract void loadData() ;

}
