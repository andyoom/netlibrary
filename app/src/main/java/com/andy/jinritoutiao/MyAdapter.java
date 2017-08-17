package com.andy.jinritoutiao;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.library.base.CommAdapter;
import com.andy.library.image.Image;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/17 11:21
 */
public class MyAdapter extends CommAdapter<Data.MoviesBean,MyAdapter.ViewHolder>{

    public MyAdapter(Context context, List<Data.MoviesBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, Data.MoviesBean moviesBean) {
//        holder.image.setImageResource(R.mipmap.ic_launcher);
        Image.display(moviesBean.getImg(),holder.image);
        holder.name.setText("影片： "+moviesBean.getTitleCn());
        String dian = "、";
        String actor = moviesBean.getActorName1()+dian+moviesBean.getActorName2();
        if(actor.startsWith(dian)){
            actor= actor.substring(1,actor.length());
        }else  if(actor.endsWith(dian)){
            actor= actor.substring(0,actor.length()-1);
        }
        holder.actor.setText("演员： "+actor);
        holder.director.setText("导演： "+moviesBean.getDirectorName());
        holder.type.setText("类型： "+moviesBean.getType());
    }

    @Override
    public ViewHolder getViewHolder() {
        return new ViewHolder();
    }

    class ViewHolder{
        @ViewInject(R.id.image)
        ImageView image;
        @ViewInject(R.id.name)
        TextView name;
        @ViewInject(R.id.actor)
        TextView actor;
        @ViewInject(R.id.director)
        TextView director;
        @ViewInject(R.id.type)
        TextView type;
    }
}
