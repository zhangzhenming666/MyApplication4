package com.bawei.zhangzhenming20191126;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.zhangzhenming20191126.base.BaseActivity;
import com.bawei.zhangzhenming20191126.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private TabLayout tab;
    private ViewPager view_pager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    private ImageView imges;

    @Override
    protected void initData() {
      list.add("要闻");
      list.add("推荐");
      list.add("视频");
        HomeFragment homeFragment = new HomeFragment();
        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(homeFragment1);
        fragmentList.add(homeFragment2);
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        tab.setupWithViewPager(view_pager);
    }

    @Override
    protected void initView() {
        tab = findViewById(R.id.tab);
        view_pager = findViewById(R.id.view_pager);
        imges = findViewById(R.id.imges);
        //点击头像
        imges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }
    //设置头像

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
            imges.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
