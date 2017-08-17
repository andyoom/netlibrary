package com.andy.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 16:16
 */
public abstract class BaseFragment extends Fragment{
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = x.view().inject(this,inflater,container);
        initView();
        loadData();
        return rootView;
    }

    /**
     * 初始化控件
     */
    protected abstract void initView() ;

    /**
     * 加载网络数据
     */
    protected abstract void loadData() ;

    public View getRootView() {
        return rootView;
    }
}
