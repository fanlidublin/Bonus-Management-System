package award.topic.com.awardapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectNewsActivity extends AppCompatActivity {
    @Bind(R.id.tv_awardPay)TextView tv_awardPay;
    @Bind(R.id.tv_awardWiped)TextView tv_awardWiped;
    @Bind(R.id.tv_awardBalance)TextView tv_awardBalance;
    @Bind(R.id.tv_awardTime)TextView tv_awardTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_news);
        ButterKnife.bind(this);
        setTitle("PM项目经费查询");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData();
    }

    private void initData(){
        tv_awardBalance.setText("2000¥");
        tv_awardPay.setText("280¥");
        tv_awardTime.setText("2016/4/12");
        tv_awardWiped.setText("120¥");
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
