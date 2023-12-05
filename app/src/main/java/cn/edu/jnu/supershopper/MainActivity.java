package cn.edu.jnu.supershopper;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jnu.supershopper.ui.BaseActivity;
import cn.edu.jnu.supershopper.ui.HomeFragment;
import cn.edu.jnu.supershopper.ui.MineFragment;
import cn.edu.jnu.supershopper.ui.QuestFragment;
import cn.edu.jnu.supershopper.ui.RewardsFragment;
import cn.edu.jnu.supershopper.ui.StatisticsFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout bottomLayout;
    private TextView quest;
    private TextView rewards;
    private TextView statistics;
    private TextView mine;
    private RelativeLayout fragmentLayout;
    private QuestFragment questFragment;
    private RewardsFragment rewardsFragment;
    private StatisticsFragment statisticsFragment;
    private MineFragment mineFragment;

    //    public class PageViewFragmentAdapter extends FragmentStateAdapter {
//
//        public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//            super(fragmentManager, lifecycle);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            switch(position)
//            {
//                case 0:
//                    return BookItemFragment.newInstance();
//                case 1:
//                    return XinnaNewsFragment.newInstance();
//                case 2:
//                    return BaiduMapFragment.newInstance();
//                case 3:
//                    return GameFragment.newInstance();
//            }
//            return BookItemFragment.newInstance();
//        }
//
//        @Override
//        public int getItemCount() {
//            return 4;
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        ViewPager2 viewPager2Main= findViewById(R.id.viewpager2_main);
//        viewPager2Main.setAdapter(new PageViewFragmentAdapter(getSupportFragmentManager(),getLifecycle()));
//
//        TabLayout tabLayout=findViewById(R.id.tablayout_header);
//        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2Main, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                switch(position)
//                {
//                    case 0:
//                        tab.setText(R.string.string_tab_book);
//                        break;
//                    case 1:
//                        tab.setText(R.string.string_tab_news);
//                        break;
//                    case 2:
//                        tab.setText(R.string.string_tab_seller);
//                        break;
//                    case 3:
//                        tab.setText(R.string.game_str);
//                        break;
//                }
//            }
//        });
//        tabLayoutMediator.attach();
        initView();
        initData();

//     new AlertDialog.Builder(this).setTitle("提示")
//             .setMessage("该版本为测试版本")

    }

    private void initData() {
        questFragment = new QuestFragment();
        rewardsFragment = new RewardsFragment();
        statisticsFragment = new StatisticsFragment();
        mineFragment = new MineFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_layout, questFragment)
                .add(R.id.fragment_layout, rewardsFragment)
                .add(R.id.fragment_layout, statisticsFragment)
                .add(R.id.fragment_layout, mineFragment)
                .hide(rewardsFragment)
                .hide(statisticsFragment)
                .hide(mineFragment)
                .show(questFragment)
                .commitAllowingStateLoss();


        quest.setOnClickListener(this);
        rewards.setOnClickListener(this);
        statistics.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    private void initView() {
        bottomLayout = (LinearLayout) findViewById(R.id.bottom_layout);
        quest = (TextView) findViewById(R.id.quest);
        rewards = (TextView) findViewById(R.id.rewards);
        statistics = (TextView) findViewById(R.id.statistics);
        mine = (TextView) findViewById(R.id.mine);
        fragmentLayout = (RelativeLayout) findViewById(R.id.fragment_layout);
    }

    private void updateFragment(int p) {
        Fragment showFragment;
        switch (p) {
            case 0:
                showFragment = questFragment;
                quest.setTextColor(getResources().getColor(R.color.colorPrimary));
                rewards.setTextColor(getResources().getColor(R.color.textColor));
                statistics.setTextColor(getResources().getColor(R.color.textColor));
                mine.setTextColor(getResources().getColor(R.color.textColor));
                setTextDrawable(quest,R.mipmap.ic_tasks_true);
                setTextDrawable(rewards,R.mipmap.ic_rewards_false);
                setTextDrawable(statistics,R.mipmap.ic_statistics_false);
                setTextDrawable(mine,R.mipmap.ic_mine_false);
                break;
            case 1:
                showFragment = rewardsFragment;
                quest.setTextColor(getResources().getColor(R.color.textColor));
                rewards.setTextColor(getResources().getColor(R.color.colorPrimary));
                statistics.setTextColor(getResources().getColor(R.color.textColor));
                mine.setTextColor(getResources().getColor(R.color.textColor));
                setTextDrawable(quest,R.mipmap.ic_tasks_false);
                setTextDrawable(rewards,R.mipmap.ic_rewards_true);
                setTextDrawable(statistics,R.mipmap.ic_statistics_false);
                setTextDrawable(mine,R.mipmap.ic_mine_false);
                break;
            case 2:
                showFragment = statisticsFragment;
                quest.setTextColor(getResources().getColor(R.color.textColor));
                rewards.setTextColor(getResources().getColor(R.color.textColor));
                statistics.setTextColor(getResources().getColor(R.color.colorPrimary));
                mine.setTextColor(getResources().getColor(R.color.textColor));
                setTextDrawable(quest,R.mipmap.ic_tasks_false);
                setTextDrawable(rewards,R.mipmap.ic_rewards_false);
                setTextDrawable(statistics,R.mipmap.ic_statistics_true);
                setTextDrawable(mine,R.mipmap.ic_mine_false);
                break;
            case 3:
                showFragment = mineFragment;
                quest.setTextColor(getResources().getColor(R.color.textColor));
                rewards.setTextColor(getResources().getColor(R.color.textColor));
                statistics.setTextColor(getResources().getColor(R.color.textColor));
                mine.setTextColor(getResources().getColor(R.color.colorPrimary));
                setTextDrawable(quest,R.mipmap.ic_tasks_false);
                setTextDrawable(rewards,R.mipmap.ic_rewards_false);
                setTextDrawable(statistics,R.mipmap.ic_statistics_false);
                setTextDrawable(mine,R.mipmap.ic_mine_true);
                break;
            default:
                showFragment = questFragment;
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .hide(rewardsFragment)
                .hide(statisticsFragment)
                .hide(mineFragment)
                .hide(questFragment)
                .show(showFragment)
                .commitAllowingStateLoss();

    }
    private void setTextDrawable(TextView tv,int redId) {
        Drawable drawable = getResources().getDrawable(redId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置图片在文字的哪个方向
        tv.setCompoundDrawables(null, drawable, null, null);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quest:
                updateFragment(0);
                break;
            case R.id.rewards:
                updateFragment(1);
                break;
            case R.id.statistics:
                updateFragment(2);
                break;
            case R.id.mine:
                updateFragment(3);
                break;
        }
    }
}