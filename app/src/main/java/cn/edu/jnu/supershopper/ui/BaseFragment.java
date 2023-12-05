package cn.edu.jnu.supershopper.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(getLayoutId(), null);


        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

//
//    protected void showLoading() {
//
//        BaseActivity activity = (BaseActivity) getActivity();
//        activity.showLoading();
//
//    }

//    protected void showLoading(String text) {
//
//        BaseActivity activity = (BaseActivity) getActivity();
//        activity.showLoading(text);
//
//    }

//    protected void closeDialog() {
//        BaseActivity activity = (BaseActivity) getActivity();
//        activity.closeDialog();
//    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMes(String s) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}