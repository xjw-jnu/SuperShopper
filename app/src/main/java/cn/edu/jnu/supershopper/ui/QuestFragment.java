package cn.edu.jnu.supershopper.ui;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import cn.edu.jnu.supershopper.R;

/**
 * 任务
 */
public class QuestFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quest;
    }

    @Override
    protected void initView() {
        ViewPager2 viewPager2Main = getView().findViewById(R.id.viewpager2_main);
        viewPager2Main.setAdapter(new PageViewFragmentAdapter(getChildFragmentManager(), getLifecycle()));

        TabLayout tabLayout = getView().findViewById(R.id.tablayout_header);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2Main, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("每日任务");
                        break;
                    case 1:
                        tab.setText("每周任务");
                        break;
                    case 2:
                        tab.setText("普通任务");
                        break;
                    case 3:
                        tab.setText("副本任务");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
        getView().findViewById(R.id.add).setOnClickListener(v -> {
            startActivity(new Intent(getContext(),AddQuestActivity.class));
        });
    }


    public class PageViewFragmentAdapter extends FragmentStateAdapter {

        public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new QuestListFragment(0);
                case 1:
                    return new QuestListFragment(1);
                case 2:
                    return new QuestListFragment(2);
                case 3:
                    return new QuestListFragment(3);
            }
            return new QuestListFragment(0);
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
