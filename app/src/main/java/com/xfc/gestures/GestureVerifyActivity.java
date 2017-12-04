package com.xfc.gestures;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import view.GestureContentView;
import view.GestureDrawline;


/**
 * 手势绘制/校验界面
 */
public class GestureVerifyActivity extends Activity implements
        android.view.View.OnClickListener {
    private TextView mTextCancel;
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextForget;
    private TextView mTextOther;
    private TextView phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_verify);
        setUpViews();
    }

    private void setUpViews() {
        mTextCancel = findViewById(R.id.text_cancel);
        mTextCancel.setOnClickListener(this);
        mTextTip = findViewById(R.id.text_tip);
        mGestureContainer = findViewById(R.id.gesture_container);
        mTextForget = findViewById(R.id.text_forget_gesture);
        mTextForget.setOnClickListener(this);
        mTextOther = findViewById(R.id.text_other_account);
        mTextOther.setOnClickListener(this);
        phone = findViewById(R.id.text_phone_number);
        phone.setText(getProtectedMobile("15854265825"));
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true,
                MainActivity.str, new GestureDrawline.GestureCallBack() {

            @Override
            public void onGestureCodeInput(String inputCode) {

            }

            @Override
            public void checkedSuccess() {
                mGestureContentView.clearDrawlineState(0L);
                MainActivity.off = false;
                GestureVerifyActivity.this.finish();
            }

            @Override
            public void checkedFail() {
                mGestureContentView.clearDrawlineState(100L);// 绘制完清除线
                mTextTip.setVisibility(View.VISIBLE);
                mTextTip.setText(Html
                        .fromHtml("<font color='#c70c1e'>密码错误</font>"));
                // 左右移动动画
                Animation shakeAnimation = AnimationUtils
                        .loadAnimation(GestureVerifyActivity.this,
                                R.anim.shake);
                mTextTip.startAnimation(shakeAnimation);
            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_cancel:
                this.finish();
                break;
            default:
                break;
        }
    }

}
