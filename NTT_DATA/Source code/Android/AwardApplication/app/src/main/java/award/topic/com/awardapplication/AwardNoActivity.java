package award.topic.com.awardapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import award.topic.com.adapter.PmAwardAdapter;
import award.topic.com.config.Consts;
import award.topic.com.domain.PmAward;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AwardNoActivity extends AppCompatActivity {
    private PmAwardAdapter adapter;
    private List<PmAward> pmAwards = new ArrayList<>();

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_no);
        ButterKnife.bind(this);
        setTitle("PM项目经费查询");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        role= Consts.ROLE;

        if(role.equals("admini")) {
            setMData();
        }else{
            setMData2();
        }
        initRecyclerView();
        setMData();
        initAdapter();
    }

    private void setMData() {
        if (pmAwards == null || pmAwards.size() < 1) {
            pmAwards.add(PmAward.builder().awardName("项目1").awardMoney("PM分配费用:6100¥").awardBalance("余额:2000¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目1").awardMoney("PM分配费用:8000¥").awardBalance("余额:3000¥").awardTime("5-8月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目2").awardMoney("PM分配费用:7000¥").awardBalance("余额:3000¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目3").awardMoney("PM分配费用:5000¥").awardBalance("余额:1000¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目3").awardMoney("PM分配费用:5600¥").awardBalance("余额:1560¥").awardTime("5-8月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目4").awardMoney("PM分配费用:9000¥").awardBalance("余额:3500¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目4").awardMoney("PM分配费用:4500¥").awardBalance("余额:1000¥").awardTime("5-8月期间").build());
        }
    }

    private void setMData2() {
        if (pmAwards == null || pmAwards.size() < 1) {
            pmAwards.add(PmAward.builder().awardName("项目1").awardMoney("PM分配费用:5601¥").awardBalance("余额:1200¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目1").awardMoney("PM分配费用:1200¥").awardBalance("余额:456¥").awardTime("5-8月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目2").awardMoney("PM分配费用:3000¥").awardBalance("余额:1230¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目3").awardMoney("PM分配费用:4500¥").awardBalance("余额:800¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目3").awardMoney("PM分配费用:7800¥").awardBalance("余额:2000¥").awardTime("5-8月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目4").awardMoney("PM分配费用:4587¥").awardBalance("余额:2300¥").awardTime("1-4月期间").build());
            pmAwards.add(PmAward.builder().awardName("项目4").awardMoney("PM分配费用:3576¥").awardBalance("余额:956¥").awardTime("5-8月期间").build());
        }
    }

    private void initAdapter() {
        adapter = new PmAwardAdapter(pmAwards,AwardNoActivity.this);
       adapter.setOnItemClickLitener(new PmAwardAdapter.OnItemClickLitener() {
           @Override
           public void onItemClick(View view, int position) {
               startActivity(new Intent(AwardNoActivity.this,ProjectNewsActivity.class));
           }
       });
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
