package com.bawei.zhangzhenming20191126;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.zhangzhenming20191126.base.BaseActivity;

/**
 * 时间：2019/11/26
 * 作者：张振明
 * 类的作用：
 */
public class SengActivity extends BaseActivity {

    private WebView web;

    @Override
    protected void initData() {
   final String key =getIntent().getStringExtra("key");
   web.loadUrl(key);
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("TAG", "onPageStarted: 网页开启加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("TAG", "onPageStarted: 网页结束加载");
            }
        });
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("TAG", "onPageStarted: 开始进度"+newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("TAG", "onPageStarted: 标题进度"+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.e("TAG", "onPageStarted: 图标进度"+icon);

            }
        });
    }

    @Override
    protected void initView() {
        web = findViewById(R.id.web);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected int LayoutId() {
        return R.layout.send_layout;
    }
}
