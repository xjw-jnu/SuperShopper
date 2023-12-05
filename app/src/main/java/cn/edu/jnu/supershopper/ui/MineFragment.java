package cn.edu.jnu.supershopper.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.jnu.supershopper.R;

public class MineFragment extends BaseFragment {
    private LinearLayout rlTop;
    private ImageView headPic;
    private TextView tvUsername;
    private TextView devicesNum;
    private TextView updatePwd;
    private LinearLayout managerLayout;
    private TextView userManager;
    private TextView totalNum;
    private TextView logout;
    private TextView version;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

        rlTop = (LinearLayout) getView().findViewById(R.id.rlTop);
        headPic = (ImageView) getView().findViewById(R.id.headPic);
        tvUsername = (TextView) getView().findViewById(R.id.tv_username);
        devicesNum = (TextView) getView().findViewById(R.id.devicesNum);
        updatePwd = (TextView) getView().findViewById(R.id.updatePwd);
        managerLayout = (LinearLayout) getView().findViewById(R.id.managerLayout);
        userManager = (TextView) getView().findViewById(R.id.userManager);
        totalNum = (TextView) getView().findViewById(R.id.totalNum);
        logout = (TextView) getView().findViewById(R.id.logout);
        version = (TextView) getView().findViewById(R.id.version);
        try {
            PackageInfo info = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            version.setText("版本号：" + info.versionName + "(" + info.versionCode + ")");

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
