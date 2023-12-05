package cn.edu.jnu.supershopper.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import cn.edu.jnu.supershopper.uitl.SPUtils;

/**
 * 任务列表
 */
public class QuestListFragment extends BaseFragment {
    private RecyclerView recycleView;
    private RAdapter<QuestItem> adapter;
    private ArrayList<QuestItem> list = new ArrayList<>();
    private int type;
    private TextView noData;

    public QuestListFragment(int type) {
        super();
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quest_list;
    }

    @Override
    protected void initView() {

        recycleView = (RecyclerView) getView().findViewById(R.id.recycleView);
        noData = (TextView) getView().findViewById(R.id.noData);
        adapter = new RAdapter<QuestItem>(getContext(), R.layout.quest_list_item, getList()) {
            @Override
            protected void init(RViewHolder holder, QuestItem questItem) {
                holder.setText(R.id.title, questItem.title);
                holder.setText(R.id.quantity, questItem.tag);
                holder.setText(R.id.score, "+" + questItem.score);
            }
        };
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        notifyDataSetChanged();

    }

    private List<QuestItem> getList() {

        String string = SPUtils.getString(getContext(), "quest_list" + type);
        if (!TextUtils.isEmpty(string)) {
            Log.e("------", "json:" + string);
            Type type = new TypeToken<ArrayList<QuestItem>>() {
            }.getType();
            list.addAll(new Gson().fromJson(string, type));
        }

//        if (list.size() == 0) {
//            list.add(new QuestItem("普通阅读30分钟", 10, "1/99"));
//            list.add(new QuestItem("专业阅读30分钟", 15, "3/99"));
//            list.add(new QuestItem("keep课程1次", 10, "2/99"));
//        }

        return list;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMes(QuestItem questItem) {
        if (questItem.type == type) {
            list.add(questItem);
            notifyDataSetChanged();
            SPUtils.putString(getContext(), "quest_list" + type, new Gson().toJson(list));
        }

    }

    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        noData.setVisibility(list.size() ==0? View.VISIBLE:View.GONE);
    }

}
