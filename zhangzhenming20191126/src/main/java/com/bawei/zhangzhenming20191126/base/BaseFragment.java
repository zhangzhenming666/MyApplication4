package com.bawei.zhangzhenming20191126.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 时间：2019/11/26
 * 作者：张振明
 * 类的作用：
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(LayoutId(), container, false);
        initView(inflate);
        return inflate;
    }

    protected abstract void initView(View inflate);

    protected abstract int LayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDta();
    }

    protected abstract void initDta();
}
