package com.bawei.zhangzhenming20191126.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bawei.zhangzhenming20191126.Bean;
import com.bawei.zhangzhenming20191126.MyAdapter;
import com.bawei.zhangzhenming20191126.NetUils;
import com.bawei.zhangzhenming20191126.R;
import com.bawei.zhangzhenming20191126.SengActivity;
import com.bawei.zhangzhenming20191126.base.BaseFragment;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2019/11/26
 * 作者：张振明
 * 类的作用：
 */
public class HomeFragment extends BaseFragment {

    private XListView xlist_view;
    private ImageView img;
    private int page=1;
    List<Bean.ListdataBean> list=new ArrayList<>();


    @Override
    protected void initView(View inflate) {
        xlist_view = inflate.findViewById(R.id.xlist_view);
        img = inflate.findViewById(R.id.img);

        xlist_view.setPullLoadEnable(true);
        xlist_view.setPullRefreshEnable(true);

        //跳转网页
        xlist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bean.ListdataBean listdataBean = list.get(i - 1);
          //      String url = listdataBean.getUrl();
                Intent intent = new Intent(getActivity(), SengActivity.class);
             //   intent.putExtra("key",url);
                startActivity(intent);
            }
        });

        xlist_view.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                getData();
                list.clear();
                xlist_view.stopRefresh();
            }

            @Override
            public void onLoadMore() {
             page++;
             getData();
             xlist_view.stopLoadMore();
            }
        });
    }

    private void getData() {
     if (NetUils.getInstance().hasNet(getContext())){
         img.setVisibility(View.GONE);
         xlist_view.setVisibility(View.VISIBLE);
         String httpUrl="";
         if (page==1){
             httpUrl ="http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
         } else if (page ==2){
             httpUrl ="http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
         } else {
             httpUrl ="http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
         }
         NetUils.getInstance().getJson(httpUrl, new NetUils.MyCallback() {
             @Override
             public void onGetJson(String json) {
                 Log.e("TAG", "onGetJson: "+json );
                 Bean bean = new Gson().fromJson(json, Bean.class);
                 List<Bean.ListdataBean> listdata = bean.getListdata();
                 list.addAll(listdata);
               xlist_view.setAdapter(new MyAdapter(list));
             }
         });
     } else {
         img.setVisibility(View.VISIBLE);
         xlist_view.setVisibility(View.GONE);
     }
    }

    @Override
    protected int LayoutId() {
        return R.layout.home_layout;
    }

    @Override
    protected void initDta() {
   getData();
    }
}
