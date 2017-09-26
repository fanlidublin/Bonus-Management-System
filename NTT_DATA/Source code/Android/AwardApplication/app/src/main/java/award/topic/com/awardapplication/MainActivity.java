package award.topic.com.awardapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import award.topic.com.config.Consts;
import award.topic.com.utils.ShowMsg;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static award.topic.com.awardapplication.R.id.iv_role;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String userName;
    private String password;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    ShowMsg.showMsg(MainActivity.this, "尊敬的" + userName + "登录成功");
                    //homePageActivity(username);
                    // newsActivity(id);
                    break;
                case 2:
                    //et_pwd.setText("");
                    ShowMsg.showMsg(MainActivity.this, "登录失败");
                    break;
                case 3:
                    ShowMsg.showMsg(MainActivity.this, "网络连接错误");
                    break;
            }
        }
    };

    @Bind(iv_role)ImageView ivRole;
    @OnClick(R.id.bt_login)void login(View v){
       Consts.ROLE=role;

        /*Bundle mBundle = new Bundle();
        mBundle.putString("role", role);
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HomeActivity.class);
        intent.putExtras(mBundle);
        startActivity(intent);*/
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        /*userName = et_userName.getText().toString().trim();
        password = et_passward.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();

            return;
        }
        *//*String path = Consts.BASE_URL + "/NTT/login";
        String data = "userName=" + userName + "&password=" + password;
        WebService.post(path, data, new HttpCallBack() {
            @Override
            public void onFinish(String res,String data) {
                parseJSONWithJSONObject(res);
                *//**//*FinalDb mFinalDb= CFinal.getCFinal().getFinalDb(MainActivity.this);
                mFinalDb.save(data);*//**//*
            }

            @Override
            public void onError(Exception ex) {
                Message msg = Message.obtain();
                msg.obj = ex.toString();
                msg.what = Consts.NET_ERROR;
                handler.sendMessage(msg);
            }
        });
*//*



        Call<User> callUser = CallService.service.login(userName, password,"admin");

        callUser.enqueue(new Callback<User>() {
                             @Override
                             public void onResponse(Response<User> response, Retrofit retrofit) {
                                 User userBack = response.body();
                                 if (userBack != null && !TextUtils.isEmpty(userBack.getUserName())) {
                                     Toast.makeText(MainActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                                     startActivity(new Intent(MainActivity.this, HomeActivity.class));

                                 }
                             }

                             @Override
                             public void onFailure(Throwable t) {
                                 Log.d(TAG, "onFailure");
                             }
                         }
        );*/
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject responseBody = new JSONObject(jsonData);
            //JSONObject user = responseBody.getJSONObject("user");
            Message msg = Message.obtain();
            if (responseBody != null && !TextUtils.isEmpty(responseBody.getString("id"))) {
                msg.what = Consts.SUCCESS;
               // id = responseBody.getString("id");
                userName = responseBody.getString("userName");
            } else {
                msg.what = Consts.ERROR;
            }
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @OnClick(R.id.tv_register)void register(View v){
        startActivity(new Intent(mContext,RegisterActivity.class));
    }

    /**
     * 用户的名字
     */
    @Bind(R.id.et_userName)
    TextView et_userName;

    /**
     * 用户的密码
     */
    @Bind(R.id.et_passward)
    TextView et_passward;

    private String role;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=MainActivity.this;
        setTitle("登录");
        ButterKnife.bind(this);
        initRole();
    }
    private void initRole(){
        Intent intent=this.getIntent();
        role=intent.getStringExtra("role");
        if(role.equals("ceo")){
            ivRole.setBackgroundResource(R.drawable.id_zongcai);

        }else if(role.equals("manager")){
            ivRole.setBackgroundResource(R.drawable.id_jingli);

        }else if(role.equals("admini")){
            ivRole.setBackgroundResource(R.drawable.id_guanliyuan);//管理员
        }else{
            ivRole.setBackgroundResource(R.drawable.id_buzhang);//部长
        }
    }
}
