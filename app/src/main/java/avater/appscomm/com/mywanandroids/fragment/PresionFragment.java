package avater.appscomm.com.mywanandroids.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.activity.ArticleDetailsActivity;
import avater.appscomm.com.mywanandroids.base.BaseMvpFragment;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;
import avater.appscomm.com.mywanandroids.mvp.presenter.PresionPreseneter;
import avater.appscomm.com.mywanandroids.mvp.view.IPersionView;
import avater.appscomm.com.mywanandroids.utils.SpUtils;

public class PresionFragment extends BaseMvpFragment<IPersionView, PresionPreseneter> implements IPersionView {

    private ImageView ivLoginOut;
    private LinearLayout llInfo;
    private FrameLayout flHead;
    private ImageView ivHead;
    private AppCompatTextView tvLogin;
    private LinearLayout llNickname;
    private AppCompatTextView tvUsername;
    private LinearLayout llCollect;
    private LinearLayout llMyQzs;
    private static String QZS = "http://qinzishuai.cn";
    private LinearLayout llAbout;

    @Override
    protected PresionPreseneter createPresenter() {
        return new PresionPreseneter();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_login) {
            PresionFragment.startLogin(getActivity());
        }
        if (v.getId() == R.id.ll_collect) {
            PresionFragment.startCollect(getActivity());
        }
        //登出
        if (v.getId() == R.id.iv_login_out) {
            mPresenter.loginOut();
        }
        if (v.getId() == R.id.ll_my_qzs) {
//            PresionFragment.startArticleDetail(getActivity(), "秦子帅的博客", QZS);
        }
        if (v.getId() == R.id.ll_about) {
            PresionFragment.startAbout(getActivity());
        }
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
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initView() {
        llAbout = rootView.findViewById(R.id.ll_about);
        llMyQzs = rootView.findViewById(R.id.ll_my_qzs);
        ivLoginOut = rootView.findViewById(R.id.iv_login_out);
        llInfo = rootView.findViewById(R.id.ll_info);
        flHead = rootView.findViewById(R.id.fl_head);
        ivHead = rootView.findViewById(R.id.iv_head);
        tvLogin = rootView.findViewById(R.id.tv_login);
        llNickname = rootView.findViewById(R.id.ll_nickname);
        tvUsername = rootView.findViewById(R.id.tv_username);
        llCollect = rootView.findViewById(R.id.ll_collect);
        llAbout.setOnClickListener(this);
        llCollect.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        ivLoginOut.setOnClickListener(this);
        llMyQzs.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initInfo();
    }

    private void initInfo() {
        if (SpUtils.GetConfigString("username").equals("")) {
            llInfo.setVisibility(View.GONE);
            tvLogin.setVisibility(View.VISIBLE);
            llNickname.setVisibility(View.GONE);
            ivLoginOut.setVisibility(View.GONE);

        } else {
            llInfo.setVisibility(View.VISIBLE);
            llNickname.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
            tvUsername.setText(SpUtils.GetConfigString("username") + "");
            ivLoginOut.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void loginOutSuccess(CurrencyBean.DataBean dataBean) {
        SpUtils.SetConfigString("username", "");
        PresionFragment.startLogin(getActivity());
        getActivity().finish();
    }

    @Override
    public void loginOutFail(String msg) {

    }

    public static void startArticleDetail(Context context, String title, String url) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void startLogin(Context context) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        context.startActivity(intent);
    }

    public static void startCollect(Context context) {
//        Intent intent = new Intent(context, MyCollectActivity.class);
//        context.startActivity(intent);
    }

    public static void startAbout(Context context) {
//        Intent intent = new Intent(context, QzsActivity.class);
//        context.startActivity(intent);
    }

    public static PresionFragment newInstance() {
        PresionFragment fragment = new PresionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
