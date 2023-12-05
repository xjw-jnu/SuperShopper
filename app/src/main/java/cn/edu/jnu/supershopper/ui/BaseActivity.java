package cn.edu.jnu.supershopper.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
//        statusbar(true);
    }


//    public void statusbar(boolean isShow) {
//        if (isShow) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                //5.0 全透明实现
//                //getWindow.setStatusBarColor(Color.TRANSPARENT)
//                Window window = getWindow();
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//
//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                //4.4 全透明状态栏
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//
//        } else {
//            initStatusBar();
//        }
//
//    }

//    /**
//     * 说明：
//     * 1. SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：Activity全屏显示，但状态栏不会被隐藏覆盖。
//     * 2. SYSTEM_UI_FLAG_LIGHT_STATUS_BAR：设置状态栏图标为黑色或者白色
//     * 3. StatusBarUtil 工具类是修改状态栏的颜色为白色。
//     */
//    protected void initStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            StatusBarUtils.setStatusBarColor(this, R.color.white);
//        }
//    }

//    private Dialog mDialog;
//
//    public void showLoading(String text) {
//        //打开加载框
//        mDialog = DialogUtil.createLoadingDialog(mContext, text);
//
//    }
//
//    public void showLoading() {
//        //打开加载框
//        showLoading("加载中...");
//
//    }
//
//    public void closeDialog() {
//        if (mDialog != null) {
//            //关闭加载框
//            DialogUtil.closeDialog(mDialog);
//        }
//    }
}
