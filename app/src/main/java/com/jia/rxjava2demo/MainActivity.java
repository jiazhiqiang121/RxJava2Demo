package com.jia.rxjava2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jia.rxjava2demo.ui.Dagger2Activity;
import com.jia.rxjava2demo.ui.HomeActivity;
import com.jia.rxjava2demo.ui.filterlist.FilterListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;

    @OnClick({R.id.home_btn, R.id.filter_list_btn})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.home_btn:
//                startActivity(new Intent(this, HomeActivity.class));
                Dagger2Activity.startActivity(this);
                break;
            case R.id.filter_list_btn:
                FilterListActivity.startActivity(this);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
