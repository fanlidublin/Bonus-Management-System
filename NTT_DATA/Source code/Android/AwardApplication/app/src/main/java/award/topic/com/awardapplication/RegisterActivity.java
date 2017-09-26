package award.topic.com.awardapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    @Bind(R.id.et_userName)
    EditText et_userName;
    @Bind(R.id.et_passward)
    EditText et_passward;
    @Bind(R.id.et_confirm_passward)
    EditText et_confirm_passward;
    @OnClick(R.id.bt_register)
    void setRegister(View v) {
        String userName = et_userName.getText().toString().trim();
        String password = et_passward.getText().toString().trim();
        String confirm_passward = et_confirm_passward.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_passward)) {
            Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();

        } else if (!password.equals(confirm_passward)) {
            Toast.makeText(this, "输入密码不匹配", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setTitle("注册");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
