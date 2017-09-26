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

import award.topic.com.adapter.ProjectAdapter;
import award.topic.com.config.Consts;
import award.topic.com.domain.ProjectAward;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectAwardActivity extends AppCompatActivity {
    private ProjectAdapter adapter;
    private List<ProjectAward> projectAwards = new ArrayList<>();

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_award);
        ButterKnife.bind(this);
        setTitle("奖金发放");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        role=Consts.ROLE;

        if(role.equals("admini")) {
            setMData();
        }else{
            setMData2();
        }
        initRecyclerView();

        initAdapter();


    }
    private void setMData2() {
        if (projectAwards == null || projectAwards.size() < 1) {
            projectAwards.add(ProjectAward.builder().project("项目1").projectAward("发放金额:5601¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目1").projectAward("发放金额:1200¥").projectTime("5-8月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目2").projectAward("发放金额:3000¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目3").projectAward("发放金额:4500¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目3").projectAward("发放金额:7800¥").projectTime("5-8月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目4").projectAward("发放金额:4587¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目4").projectAward("发放金额:3576¥").projectTime("5-8月期间").build());

            /*projectAwards.add(ProjectAward.builder().project("").projectAward("").build());
            projectAwards.add(ProjectAward.builder().project("").projectAward("").build());
            projectAwards.add(ProjectAward.builder().project("").projectAward("").build());
            projectAwards.add(ProjectAward.builder().project("").projectAward("").build());*/
        }
    }

    private void setMData() {
        if (projectAwards == null || projectAwards.size() < 1) {
            projectAwards.add(ProjectAward.builder().project("项目1").projectAward("发放金额:6100¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目1").projectAward("发放金额:8000¥").projectTime("5-8月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目2").projectAward("发放金额:7000¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目3").projectAward("发放金额:5000¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目3").projectAward("发放金额:5600¥").projectTime("5-8月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目4").projectAward("发放金额:9000¥").projectTime("1-4月期间").build());
            projectAwards.add(ProjectAward.builder().project("项目4").projectAward("发放金额:4500¥").projectTime("5-8月期间").build());

            /*projectAwards.add(ProjectAward.builder().project("").projectAward("").build());
            projectAwards.add(ProjectAward.builder().project("").projectAward("").build());
            projectAwards.add(ProjectAward.builder().project("").projectAward("").build());
            projectAwards.add(ProjectAward.builder().project("").projectAward("").build());*/
        }
    }

    private void initAdapter() {
        adapter = new ProjectAdapter(projectAwards,ProjectAwardActivity.this);
        /*adapter.setOnItemClickLitener(new ProjectAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                     Intent intent=new Intent(ProjectAwardActivity.this,ProjectNewsActivity.class);
                     startActivity(intent);
            }
        });*/

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
