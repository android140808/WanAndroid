package avater.appscomm.com.mywanandroids.activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.base.BaseMvpActivity;
import avater.appscomm.com.mywanandroids.fragment.HomeFragment;
import avater.appscomm.com.mywanandroids.fragment.NavigateFragment;
import avater.appscomm.com.mywanandroids.fragment.PresionFragment;
import avater.appscomm.com.mywanandroids.fragment.ProjectFragment;
import avater.appscomm.com.mywanandroids.mvp.presenter.MainNewPresenter;
import avater.appscomm.com.mywanandroids.mvp.view.IMainView;

public class MainNewActivity extends BaseMvpActivity<IMainView, MainNewPresenter> implements BottomNavigationBar.OnTabSelectedListener, IMainView {

    private BottomNavigationBar bottomNavigationBar;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private LinearLayout flashView;
    private HomeFragment homeFragment;
    private ProjectFragment projectFragment;
    private NavigateFragment navigateFragment;
    private PresionFragment presionFragment;


    @Override
    protected MainNewPresenter createPresenter() {
        return new MainNewPresenter();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main_new;
    }

    @Override
    public void initView() {
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        flashView = findViewById(R.id.flash_view);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.background_gray_color);
        flashView.setVisibility(View.VISIBLE);
        flashView.postDelayed(new Runnable() {
            @Override
            public void run() {
                flashView.setVisibility(View.GONE);
            }
        }, 1500);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mManager = getSupportFragmentManager();
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_home1, "首页").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_home2).setInActiveColorResource(R.color.txt_unselect))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_project1, "项目").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_project2).setInActiveColorResource(R.color.txt_unselect))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_navigate1, "导航").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_navigate2).setInActiveColorResource(R.color.txt_unselect))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_me1, "我的").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_me2).setInActiveColorResource(R.color.txt_unselect))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.selectTab(0);
    }


    @Override
    public Dialog getLoadDialog() {
        return null;
    }

    @Override
    public void cancelLoadDialog() {

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (projectFragment != null) {
            transaction.hide(projectFragment);
        }
        if (navigateFragment != null) {
            transaction.hide(navigateFragment);
        }
        if (presionFragment != null) {
            transaction.hide(presionFragment);
        }
    }

    @Override
    public void onTabSelected(int position) {
        mTransaction = mManager.beginTransaction();
        hideFragment(mTransaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                    mTransaction.add(R.id.ll_content, homeFragment);
                } else {
                    mTransaction.show(homeFragment);
                }
                break;
            case 1:
                if (projectFragment == null) {
                    projectFragment = ProjectFragment.newInstance();
                    mTransaction.add(R.id.ll_content, projectFragment);
                } else {
                    mTransaction.show(projectFragment);
                }
                break;
            case 2:
                if (navigateFragment == null) {
                    navigateFragment = NavigateFragment.newInstance();
                    mTransaction.add(R.id.ll_content,
                            navigateFragment);
                } else {
                    mTransaction.show(navigateFragment);
                }
            case 3:
                if (presionFragment == null) {
                    presionFragment = PresionFragment.newInstance();
                    mTransaction.add(R.id.ll_content,
                            presionFragment);
                } else {
                    mTransaction.show(presionFragment);
                }
                break;
        }
        mTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
