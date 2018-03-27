package com.example.mvplearn;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mvplearn.base.BaseActivity;

/**
 * 这是个模拟网络数据请求的demo，所以有三个请求数据的按钮分别对应成功、失败、异常三种不同的反馈状态
 *
 * 在Activity代码中需要强调的是如果想要调用Presenter就要先实现Presenter需要的对应的View接口
 */

public class MainActivity extends BaseActivity implements MvpView  {
    TextView text;
    MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);
        //初始化Presenter
        presenter = new MvpPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开View引用
        presenter.detachView();
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }

    // button 点击事件调用方法
    public void getData(View view){
        presenter.getData("normal");
    }

    // button 点击事件调用方法
    public void getDataForFailure(View view){
        presenter.getData("failure");
    }

    // button 点击事件调用方法
    public void getDataForError(View view){
        presenter.getData("error");
    }
}
