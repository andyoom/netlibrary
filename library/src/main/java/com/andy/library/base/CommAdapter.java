package com.andy.library.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.xutils.x;

import java.util.List;

/**
 * 类描述：必须依赖Xutils3
 * 创建人：yekh
 * 创建时间：2017/8/7 16:57
 * T  每一个Item数据对象
 * H  ViewHolder
 */
public abstract class CommAdapter<T,H> extends BaseAdapter{
    private Context mContext;
    private List<T> list;
    private int itemLayoutId;

    public CommAdapter(Context context, List<T> list,int itemLayoutId ) {
        mContext = context;
        this.list = list;
        this.itemLayoutId=itemLayoutId;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        Log.e("getView", "getView: "+i);
        H holder = null;
        if (view == null) {
            view = View.inflate(mContext,itemLayoutId,null);
            holder = getViewHolder();
            x.view().inject(holder,view);
            view.setTag(holder);
        } else {
            holder = (H) view.getTag();
        }
        T data = list.get(i);

        convert(holder,data);

        return view;
    }

    /**
     * 设置UI的操作
     * @param holder
     * @param t
     */
    public abstract void convert(H holder,T t);

    /**
     * 提供ViewHolder的初始化对象
     * @return
     */
    public abstract H getViewHolder();
}
