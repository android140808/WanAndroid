package avater.appscomm.com.mywanandroids.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.base.BaseMvpFragment;
import avater.appscomm.com.mywanandroids.bean.ProjectTitleBean;
import avater.appscomm.com.mywanandroids.mvp.presenter.ProjectFragmentPresenter;
import avater.appscomm.com.mywanandroids.mvp.view.IFragmentProjectView;
import avater.appscomm.com.mywanandroids.utils.LogUtils;

public class ProjectFragment extends BaseMvpFragment<IFragmentProjectView, ProjectFragmentPresenter> implements IFragmentProjectView {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<ProjectTitleBean.DataBean> titleList = new ArrayList<>();
    private MyFragmentAdapter myFragmentAdapter;
    private TabLayout tablayout;
    private ViewPager vpProject;
    private FrameLayout flTitle;
    private LinearLayout llError;
    private AppCompatTextView tvLoad;


    @Override
    protected ProjectFragmentPresenter createPresenter() {
        return new ProjectFragmentPresenter();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_load) {
            mPresenter.getProjectTittle();
        }
    }

    @Override
    public void getProjectTittleSuccess(List<ProjectTitleBean.DataBean> dataBean) {
        llError.setVisibility(View.GONE);
        LogUtils.d("项目title---- " + dataBean.get(0).getName());
        fragmentList.clear();
        titleList.clear();
        titleList.addAll(dataBean);
        if (dataBean != null && dataBean.size() > 0) {
            for (int i = 0; i < dataBean.size(); i++) {
                fragmentList.add(ProjectSecFragment.newInstance(dataBean.get(i).getId() + ""));
            }
            flTitle.setVisibility(View.VISIBLE);
            myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager());
            vpProject.setAdapter(myFragmentAdapter);
            //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
            tablayout.setTabMode(titleList.size() <= 4 ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
            tablayout.setTabIndicatorFullWidth(false);
            tablayout.setupWithViewPager(vpProject);
        } else {
            flTitle.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void getProjectTittleFail(String msg) {
        llError.setVisibility(View.VISIBLE);
    }

    @Override
    public Dialog getLoadDialog() {
        return dialog;
    }

    @Override
    public void cancelLoadDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void initView() {
        llError = rootView.findViewById(R.id.ll_error);
        tvLoad = rootView.findViewById(R.id.tv_load);
        tablayout = rootView.findViewById(R.id.tablayout);
        vpProject = rootView.findViewById(R.id.vp_project);
        flTitle = rootView.findViewById(R.id.fl_title);
        tvLoad.setOnClickListener(this);
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.5f).titleBar(R.id.tablayout).init();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getProjectTittle();
    }

    public static ProjectFragment newInstance() {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public class MyFragmentAdapter extends FragmentStatePagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position).getName();
        }
    }
}
