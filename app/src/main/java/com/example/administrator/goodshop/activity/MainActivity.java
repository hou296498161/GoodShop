package com.example.administrator.goodshop.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.goodshop.R;
import com.example.administrator.goodshop.activity.Basectivity;
import com.example.administrator.goodshop.headpager.HeadPagerFragment;
import com.example.administrator.goodshop.message.MessageFragment;
import com.example.administrator.goodshop.mygoodshop.MyGoodShopFragment;
import com.example.administrator.goodshop.shopcar.ShopCarFragment;
import com.example.administrator.goodshop.wedding.WeddingFragment;

public class MainActivity extends Basectivity {
    public final static String TAG="MainActivity";

    private BottomNavigationBar main_activity_bottom_navigation;//底部导航栏
    private BottomNavigationItem headPagerItem;
    private BottomNavigationItem WeddingItem;
    private BottomNavigationItem messageItem;
    private BottomNavigationItem shopCarItem;
    private BottomNavigationItem myGoodShopItem;
    //fragment变量
    private HeadPagerFragment headPagerFragment;
    private WeddingFragment weddingFragment;
    private MessageFragment messageFragment;
    private ShopCarFragment shopCarFragment;
    private MyGoodShopFragment myGoodShopFragment;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        main_activity_bottom_navigation = (BottomNavigationBar) findViewById(R.id.main_activity_bottom_navigation);

    }

    @Override
    public void initData() {
        super.initData();
        if (!isNetworkAvailable(MainActivity.this)) {
            Toast.makeText(MainActivity.this, R.string.no_network, Toast.LENGTH_SHORT).show();
        }
        initButtom();

    }

    /**
     * 初始化底部菜单栏
     */
    public void initButtom() {
        //BottomNavigationBar.MODE_SHIFTING;
        //BottomNavigationBar.MODE_FIXED;
        //BottomNavigationBar.MODE_DEFAULT;
        //可选择显示的模式
        main_activity_bottom_navigation.setMode(BottomNavigationBar.MODE_CLASSIC);

        main_activity_bottom_navigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        main_activity_bottom_navigation.setBarBackgroundColor(R.color.headPagerBackground);//背景颜色
        main_activity_bottom_navigation.setInActiveColor(R.color.unSelect);//未选中时的颜色
        main_activity_bottom_navigation.setActiveColor(R.color.goodShop);//选中时的颜色

        headPagerItem = new BottomNavigationItem(R.drawable.headerselected,R.string.head_pager);

        WeddingItem = new BottomNavigationItem(R.drawable.wedding, R.string.wedding);

        messageItem = new BottomNavigationItem(R.drawable.message, R.string.message);
        shopCarItem = new BottomNavigationItem(R.drawable.shop_car, R.string.shop_car);
        myGoodShopItem = new BottomNavigationItem(R.drawable.my_good_shop, R.string.my_good_shop);

        main_activity_bottom_navigation.addItem(headPagerItem).addItem(WeddingItem).addItem(messageItem).addItem(shopCarItem).addItem(myGoodShopItem);
        main_activity_bottom_navigation.initialise();
        main_activity_bottom_navigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                hideAllFrag();//先隐藏所有frag
                switch (position) {
                    case 0://首页
                        if (headPagerFragment == null) {
                            headPagerFragment = new HeadPagerFragment();
                        }
                        addFrag(headPagerFragment);
                        getSupportFragmentManager().beginTransaction().show(headPagerFragment).commit();


                        break;
                    case 1://微淘
                        if (weddingFragment == null) {

                            weddingFragment = new WeddingFragment();
                        }
                        addFrag(weddingFragment);
                        getSupportFragmentManager().beginTransaction().show(weddingFragment).commit();
                        break;

                    case 2:
                        //消息
                        if (messageFragment == null) {
                            messageFragment = new MessageFragment();

                        }
                        addFrag(messageFragment);
                        getSupportFragmentManager().beginTransaction().show(messageFragment).commit();
//                        getSupportActionBar().setTitle("订单");
                        break;
                    case 3://购物车
                        if (shopCarFragment == null) {
                            shopCarFragment = new ShopCarFragment();

                        }
                        addFrag(shopCarFragment);
                        getSupportFragmentManager().beginTransaction().show(shopCarFragment).commit();
                        break;
                    case 4://我的欢乐购
                        if (myGoodShopFragment == null) {
                            myGoodShopFragment = new MyGoodShopFragment();

                        }
                        addFrag(myGoodShopFragment);
                        getSupportFragmentManager().beginTransaction().show(myGoodShopFragment).commit();
                        break;
                }

                if (!isNetworkAvailable(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "当前无网络连接", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        setDefaultFrag();//显示默认的Frag

    }


    /*设置默认Fragment*/
    private void setDefaultFrag() {

        if (headPagerFragment == null) {
            headPagerFragment = new HeadPagerFragment();
        }

        addFrag(headPagerFragment);
        /*headerFragment*/
        getSupportFragmentManager().beginTransaction().show(headPagerFragment).commit();

    }

    /*添加Frag*/
    private void addFrag(Fragment frag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (frag != null && !frag.isAdded()) {
            fragmentTransaction.add(R.id.main_activity_linearlayout, frag);
        }
        fragmentTransaction.commit();

    }

    /*隐藏所有fragment*/
    private void hideAllFrag() {
        hideFrag(headPagerFragment);
        hideFrag(weddingFragment);
        hideFrag(messageFragment);
        hideFrag(shopCarFragment);
        hideFrag(myGoodShopFragment);
    }

    /*隐藏frag*/
    private void hideFrag(Fragment frag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (frag != null && frag.isAdded()) {
            fragmentTransaction.hide(frag);
        }
        fragmentTransaction.commit();
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            @SuppressLint("MissingPermission")
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 按两次返回键退出
     */
    long startTime = 0;

    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        if ((currentTime - startTime) >= 2000) {
            Toast.makeText(MainActivity.this, R.string.again_quite, Toast.LENGTH_SHORT).show();
            startTime = currentTime;
        } else {
            finish();
        }
    }


}
