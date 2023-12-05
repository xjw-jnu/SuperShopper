package cn.edu.jnu.supershopper.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import cn.edu.jnu.supershopper.R;
import cn.edu.jnu.supershopper.data.QuestItem;
import cn.edu.jnu.supershopper.data.RewardsItem;

/**
 * 创建奖励
 */
public class AddRewardsActivity extends BaseActivity {

    private ImageView bacl;
    private EditText title;
    private EditText score;
    private EditText tag;
    private TextView post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rewards);
        initView();
        bacl.setOnClickListener(v -> {
            finish();
        });

        post.setOnClickListener(v -> {
            post();
        });
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
        RewardsItem questItem = new RewardsItem(t, new Integer(s), tg);
        EventBus.getDefault().post(questItem);
        finish();
    }

    private void initView() {
        bacl = (ImageView) findViewById(R.id.bacl);
        title = (EditText) findViewById(R.id.title);
        score = (EditText) findViewById(R.id.score);
        tag = (EditText) findViewById(R.id.tag);
        post = (TextView) findViewById(R.id.post);
    }
}