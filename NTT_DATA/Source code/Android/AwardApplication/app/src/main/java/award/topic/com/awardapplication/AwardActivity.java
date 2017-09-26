package award.topic.com.awardapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import award.topic.com.adapter.BalanceAdapter;
import award.topic.com.config.Consts;
import award.topic.com.domain.BalanceAward;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AwardActivity extends AppCompatActivity {

    private BalanceAdapter adapter;
    private List<BalanceAward> balanceAwards = new ArrayList<>();

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award);
        ButterKnife.bind(this);
        setTitle("奖金余额");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecyclerView();
        role= Consts.ROLE;

        if(role.equals("admini")) {
            setMData();
        }else{
            setMData2();
        }
        initAdapter();
    }

    private void setMData() {
        if (balanceAwards == null || balanceAwards.size() < 1) {

            balanceAwards.add(BalanceAward.builder().awardName("项目1").awardBalance("余额:5000¥").build());
            balanceAwards.add(BalanceAward.builder().awardName("项目2").awardBalance("余额:4000¥").build());
            balanceAwards.add(BalanceAward.builder().awardName("项目3").awardBalance("余额:7000¥").build());
            balanceAwards.add(BalanceAward.builder().awardName("项目4").awardBalance("余额:15000¥").build());
        }
    }

    private void setMData2() {
        if (balanceAwards == null || balanceAwards.size() < 1) {

            balanceAwards.add(BalanceAward.builder().awardName("项目1").awardBalance("余额:3450¥").build());
            balanceAwards.add(BalanceAward.builder().awardName("项目2").awardBalance("余额:2000¥").build());
            balanceAwards.add(BalanceAward.builder().awardName("项目3").awardBalance("余额:2560¥").build());
            balanceAwards.add(BalanceAward.builder().awardName("项目4").awardBalance("余额:3210¥").build());
        }
    }

    private void initAdapter() {
        adapter = new BalanceAdapter(balanceAwards,AwardActivity.this);

        mRecyclerView.setAdapter(adapter);

    }

    private void initRecyclerView() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "你点了设置", Toast.LENGTH_LONG).show();
            default:
                break;
        }
        return true;
    }
}
