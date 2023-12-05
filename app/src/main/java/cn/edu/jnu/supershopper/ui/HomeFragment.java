package cn.edu.jnu.supershopper.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import cn.edu.jnu.supershopper.BaiduMapFragment;
import cn.edu.jnu.supershopper.BookItemFragment;
import cn.edu.jnu.supershopper.GameFragment;
import cn.edu.jnu.supershopper.R;
import cn.edu.jnu.supershopper.XinnaNewsFragment;

public class HomeFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        ViewPager2 viewPager2Main=getView(). findViewById(R.id.viewpager2_main);
        viewPager2Main.setAdapter(new PageViewFragmentAdapter(getChildFragmentManager(),getLifecycle()));

        TabLayout tabLayout=getView(). findViewById(R.id.tablayout_header);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2Main, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position)
                {
                    case 0:
                        tab.setText(R.string.string_tab_book);
                        break;
                    case 1:
                        tab.setText(R.string.string_tab_news);
                        break;
                    case 2:
                        tab.setText(R.string.string_tab_seller);
                        break;
                    case 3:
                        tab.setText(R.string.game_str);
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }





    public class PageViewFragmentAdapter extends FragmentStateAdapter {

        public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position)
            {
                case 0:
                    return BookItemFragment.newInstance();
                case 1:
                    return XinnaNewsFragment.newInstance();
                case 2:
                    return BaiduMapFragment.newInstance();
                case 3:
                    return GameFragment.newInstance();
            }
            return BookItemFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
