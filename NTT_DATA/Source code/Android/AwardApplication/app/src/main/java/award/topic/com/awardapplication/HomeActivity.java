package award.topic.com.awardapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import award.topic.com.adapter.MyFragmentHomeAdapter;
import award.topic.com.config.Consts;
import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.vpager)
    ViewPager vpager;
    /**
     * 发现，天气，我的按钮
     */
    @Bind(R.id.rb_find)
    RadioButton find;
    @Bind(R.id.rb_weather)
    RadioButton weather;
    @Bind(R.id.rb_mine)
    RadioButton mine;
    @Bind(R.id.rg_tab_bar)
    RadioGroup mGroup;

    private  String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setTitle("工程师奖金");


      /*  Bundle bundle = getIntent().getExtras();
        role = bundle.getString("role");//读出数据*/
        /**
         * 隐藏
         */
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        vpager.setAdapter(new MyFragmentHomeAdapter(getSupportFragmentManager()));
        vpager.addOnPageChangeListener(new PageChangeListener());
        find.setChecked(true);
        mGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        vpager.setAdapter(new MyFragmentHomeAdapter(getSupportFragmentManager()));
        vpager.addOnPageChangeListener(new PageChangeListener());

    }


    /**
     * 实现了对mgroup里面的滑动事件
     */
    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        /*state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕*/
            if (state == 2) {
                switch (vpager.getCurrentItem()) {
                    case Consts.PAGE_ONE:
                        find.setChecked(true);
                        break;
                    case Consts.PAGE_TWO:
                        weather.setChecked(true);
                        break;
                    case Consts.PAGE_THREE:
                        mine.setChecked(true);
                        break;
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                       /* switch (menuItem.getItemId()) {
                            case R.id.nav_found:
                                if (vpager.getCurrentItem() != Consts.PAGE_ONE) {
                                    vpager.setCurrentItem(Consts.PAGE_ONE, true);
                                }
                                break;

                            case R.id.nav_weather:
                                if (vpager.getCurrentItem() != Consts.PAGE_TWO) {
                                    vpager.setCurrentItem(Consts.PAGE_TWO, true);
                                }

                                break;

                            case R.id.nav_mine:
                                if (vpager.getCurrentItem() != Consts.PAGE_THREE) {
                                    vpager.setCurrentItem(Consts.PAGE_THREE, true);
                                }
                                break;

                        }*/
                        // menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    /**
     * 实现了对按钮的点击事件
     */
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.d("RadioGroup", "OnCheckedChangeListener!!!");
            switch (checkedId) {
                case R.id.rb_find:
                    if (vpager.getCurrentItem() != Consts.PAGE_ONE) {
                        vpager.setCurrentItem(Consts.PAGE_ONE, true);
                    }
                    break;
                case R.id.rb_weather:
                    if (vpager.getCurrentItem() != Consts.PAGE_TWO) {
                        vpager.setCurrentItem(Consts.PAGE_TWO, true);
                    }
                    break;
                case R.id.rb_mine:
                    if (vpager.getCurrentItem() != Consts.PAGE_THREE) {
                        vpager.setCurrentItem(Consts.PAGE_THREE, true);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "你点了设置", Toast.LENGTH_LONG).show();
            default:
                break;
        }
        return true;
    }
}
