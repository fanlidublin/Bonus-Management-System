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

import award.topic.com.adapter.PayAwardAdapter;
import award.topic.com.config.Consts;
import award.topic.com.domain.PayAward;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PayAwardActivity extends AppCompatActivity {
    private PayAwardAdapter adapter;
    private List<PayAward> payAwards = new ArrayList<>();
    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_award);
        ButterKnife.bind(this);
        setTitle("奖金支取");
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

    private void initAdapter() {
        adapter = new PayAwardAdapter(payAwards,PayAwardActivity.this);


        mRecyclerView.setAdapter(adapter);

    }
    private void initRecyclerView() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setMData() {
        if (payAwards == null || payAwards.size() < 1) {
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：800¥").awardWiped("报销金额：200¥").awardTime("2016/3/11").build());
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：980¥").awardWiped("报销金额：120¥").awardTime("2016/4/17").build());
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：670¥").awardWiped("报销金额：100¥").awardTime("2016/4/18").build());
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：2550¥").awardWiped("报销金额：50¥").awardTime("2016/4/18").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：2000¥").awardWiped("报销金额：198¥").awardTime("2016/3/11").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：700¥").awardWiped("报销金额：56¥").awardTime("2016/3/27").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：1500¥").awardWiped("报销金额：98¥").awardTime("2016/4/5").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：1200¥").awardWiped("报销金额：234¥").awardTime("2016/4/26").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：600¥").awardWiped("报销金额：78¥").awardTime("2016/4/2").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：900¥").awardWiped("报销金额：56¥").awardTime("2016/5/1").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：400¥").awardWiped("报销金额：0¥").awardTime("2016/5/30").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：1000¥").awardWiped("报销金额：146¥").awardTime("2016/6/7").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：800¥").awardWiped("报销金额：200¥").awardTime("2016/3/11").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：800¥").awardWiped("报销金额：200¥").awardTime("2016/3/30").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：800¥").awardWiped("报销金额：200¥").awardTime("2016/4/19").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：800¥").awardWiped("报销金额：200¥").awardTime("2016/5/8").build());
        }
    }

    private void setMData2() {
        if (payAwards == null || payAwards.size() < 1) {
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：345¥").awardWiped("报销金额：123¥").awardTime("2016/3/11").build());
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：567¥").awardWiped("报销金额：121¥").awardTime("2016/4/17").build());
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：670¥").awardWiped("报销金额：200¥").awardTime("2016/4/18").build());
            payAwards.add(PayAward.builder().awardName("项目1").awardPay("支取金额：678¥").awardWiped("报销金额：567¥").awardTime("2016/4/18").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：590¥").awardWiped("报销金额：208¥").awardTime("2016/3/11").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：450¥").awardWiped("报销金额：156¥").awardTime("2016/3/27").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：670¥").awardWiped("报销金额：198¥").awardTime("2016/4/5").build());
            payAwards.add(PayAward.builder().awardName("项目2").awardPay("支取金额：1000¥").awardWiped("报销金额：334¥").awardTime("2016/4/26").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：600¥").awardWiped("报销金额：78¥").awardTime("2016/4/2").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：967¥").awardWiped("报销金额：56¥").awardTime("2016/5/1").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：567¥").awardWiped("报销金额：10¥").awardTime("2016/5/30").build());
            payAwards.add(PayAward.builder().awardName("项目3").awardPay("支取金额：678¥").awardWiped("报销金额：0¥").awardTime("2016/6/7").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：546¥").awardWiped("报销金额：12¥").awardTime("2016/3/11").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：678¥").awardWiped("报销金额：45¥").awardTime("2016/3/30").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：800¥").awardWiped("报销金额：36¥").awardTime("2016/4/19").build());
            payAwards.add(PayAward.builder().awardName("项目4").awardPay("支取金额：800¥").awardWiped("报销金额：23¥").awardTime("2016/5/8").build());
        }
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
