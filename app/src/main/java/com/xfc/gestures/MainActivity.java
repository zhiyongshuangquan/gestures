package com.xfc.gestures;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    public static boolean off = false;
    public static String str = null;
    private Button mBtnSetLock;
    private Button mBtnVerifyLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSetLock = (Button) findViewById(R.id.btn_set_lockpattern);
        mBtnVerifyLock = (Button) findViewById(R.id.btn_verify_lockpattern);
        mBtnSetLock.setOnClickListener(this);
        mBtnVerifyLock.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (off) {
            mBtnSetLock.setVisibility(View.GONE);
            mBtnVerifyLock.setVisibility(View.VISIBLE);
            mBtnVerifyLock.setTextColor(Color.RED);
        } else {
            mBtnVerifyLock.setVisibility(View.GONE);
            mBtnSetLock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_lockpattern:// 手势密码设置界面
                startActivity(new Intent(MainActivity.this,
                        GestureEditActivity.class));
                break;
            case R.id.btn_verify_lockpattern:// 手势绘制/校验界面
                startActivity(new Intent(MainActivity.this,
                        GestureVerifyActivity.class));
                break;
            default:
                break;
        }
    }

}