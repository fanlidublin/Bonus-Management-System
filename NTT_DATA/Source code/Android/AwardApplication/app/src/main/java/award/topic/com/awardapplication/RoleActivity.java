package award.topic.com.awardapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoleActivity extends AppCompatActivity {
  private Context mContext;
     @OnClick(R.id.ib_admini) void admini(View v){
       intent("admini");
   }
    @OnClick(R.id.ib_ceo)void ceo(View v){
        intent("ceo");
    }
    @OnClick(R.id.ib_manager)void manager(View v){
        intent("manager");
    }
    @OnClick(R.id.ib_minister)void minister(View v){
        intent("minister");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        mContext=RoleActivity.this;
        ButterKnife.bind(this);

    }

    private void intent(String id) {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("role", id);
        startActivity(intent);
    }
}
