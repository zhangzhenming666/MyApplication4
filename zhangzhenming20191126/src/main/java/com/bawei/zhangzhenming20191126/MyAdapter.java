package com.bawei.zhangzhenming20191126;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 时间：2019/11/26
 * 作者：张振明
 * 类的作用：
 */
public class MyAdapter extends BaseAdapter {
    private List<Bean.ListdataBean> list;

    public MyAdapter(List<Bean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getItemType();
        if (type==1){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        int itemViewType = getItemViewType(i);
        if (view ==null){
            if (itemViewType==0){
                view = View.inflate(viewGroup.getContext(),R.layout.item,null);
            } else {
                view = View.inflate(viewGroup.getContext(),R.layout.item1,null);
            }
            holder =new ViewHolder();
            holder.imge = view.findViewById(R.id.imge);
            holder.name = view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(list.get(i).getContent());
        NetUils.getInstance().getphoto(list.get(i).getImageurl(),holder.imge);
        return view;
    }
    class ViewHolder{
        ImageView imge;
        TextView name;
    }
}
