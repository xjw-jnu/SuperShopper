package cn.edu.jnu.supershopper.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import org.greenrobot.eventbus.EventBus;

import cn.edu.jnu.supershopper.R;
import cn.edu.jnu.supershopper.adapter.RAdapter;
import cn.edu.jnu.supershopper.data.QuestItem;

/**
 * 添加任务
 */
public class AddQuestActivity extends BaseActivity {

    private ImageView bacl;
    private EditText title;
    private EditText score;
    private EditText tag;
    private TextView type;
    private TextView post;
    private int tt = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest_fragment);
        initView();
        bacl.setOnClickListener(v -> {
            finish();
        });

        post.setOnClickListener(v -> {
            post();
        });
        type.setOnClickListener(v -> {
            showType();
        });
    }

    private void showType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] ss = new String[]{"每日任务", "每周任务", "普通任务", "副本任务"};
        builder.setItems(ss, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                tt = which;
                type.setText(ss[which]);
            }
        });
        builder.show();
    }

    private void post() {
        String t = title.getText().toString().trim();
        if (TextUtils.isEmpty(t)) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_LONG).show();
            return;
        }
        String s = score.getText().toString().trim();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "请输入成就点数", Toast.LENGTH_LONG).show();
            return;
        }
        String tg = tag.getText().toString().trim();
        if (TextUtils.isEmpty(tg)) {
            Toast.makeText(this, "请输入标签", Toast.LENGTH_LONG).show();
            return;
        }
        if (tt < 0) {
            Toast.makeText(this, "请选择任务类型", Toast.LENGTH_LONG).show();
            return;
        }
        QuestItem questItem = new QuestItem(t, new Integer(s), tg, tt);
        EventBus.getDefault().post(questItem);
        finish();
    }

    private void initView() {
        bacl = (ImageView) findViewById(R.id.bacl);
        title = (EditText) findViewById(R.id.title);
        score = (EditText) findViewById(R.id.score);
        tag = (EditText) findViewById(R.id.tag);
        type = (TextView) findViewById(R.id.type);
        post = (TextView) findViewById(R.id.post);
    }
}