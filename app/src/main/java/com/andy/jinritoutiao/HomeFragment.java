package com.andy.jinritoutiao;

import android.widget.ListView;

import com.andy.library.base.BaseFragment;
import com.andy.library.net.MyCallback;
import com.andy.library.net.Net;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 16:25
 */
@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment{
    @ViewInject(R.id.lv)
    private ListView lv;
    private MyAdapter mAdapter;
    private List<Data.MoviesBean> list;
    @Override
    protected void initView() {
        list=new ArrayList<>();
        mAdapter=new MyAdapter(getActivity(),list,R.layout.item);
        lv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        String url = "http://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
        Net.get(url, new MyCallback<Data>() {
            @Override
            public void success(Data data) {
                list.addAll(data.getMovies());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void fail(Throwable ex) {

            }
        });
    }
}
