package cn.edu.jnu.supershopper.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jnu.supershopper.R;
import cn.edu.jnu.supershopper.adapter.RAdapter;
import cn.edu.jnu.supershopper.adapter.RViewHolder;
import cn.edu.jnu.supershopper.data.QuestItem;
import cn.edu.jnu.supershopper.data.RewardsItem;
import cn.edu.jnu.supershopper.uitl.SPUtils;

/**
 * 奖励
 */
public class RewardsFragment extends BaseFragment {
    private RecyclerView recycleView;
    private RAdapter<RewardsItem> adapter;
    private ImageView add;
    private TextView noData;
    private List<RewardsItem> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rewards;
    }

    @Override
    protected void initView() {
        recycleView = (RecyclerView) getView().findViewById(R.id.recycleView);
        add = (ImageView) getView().findViewById(R.id.add);
        noData = (TextView) getView().findViewById(R.id.noData);
        adapter = new RAdapter<RewardsItem>(getContext(), R.layout.rewards_list_item, getList()) {
            @Override
            protected void init(RViewHolder holder, RewardsItem questItem) {
                holder.setText(R.id.title, questItem.title);
                holder.setText(R.id.quantity, questItem.tag);
                holder.setText(R.id.score, "-" + questItem.score);
            }
        };
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        notifyDataSetChanged();
        add.setOnClickListener(v -> {
            startActivity(new Intent(getContext(),AddRewardsActivity.class));
        });

    }


    private List<RewardsItem> getList() {

//        list.add(new RewardsItem("普通阅读30分钟", 10, "1/99"));
//        list.add(new RewardsItem("专业阅读30分钟", 15, "3/99"));
//        list.add(new RewardsItem("keep课程1次", 10, "2/99"));


        String string = SPUtils.getString(getContext(), "rewards_list");
        if (!TextUtils.isEmpty(string)) {
            Log.e("------", "json:" + string);
            Type type = new TypeToken<ArrayList<RewardsItem>>() {
            }.getType();
            list.addAll(new Gson().fromJson(string, type));
        }
        return list;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMes(RewardsItem questItem) {
        list.add(questItem);
        notifyDataSetChanged();
        SPUtils.putString(getContext(), "rewards_list", new Gson().toJson(list));

    }

    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        noData.setVisibility(list.size() == 0 ? View.VISIBLE : View.GONE);
    }

}
