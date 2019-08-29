package avater.appscomm.com.mywanandroids.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import avater.appscomm.com.mywanandroids.application.MyApplication;
import avater.appscomm.com.mywanandroids.utils.DiaLogUtils;

public abstract class BaseMvpActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxAppCompatActivity
        implements IBaseMvpView, IActivity, View.OnClickListener {

    protected P mPresenter;
    protected Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ImmersionBar.with(this).init();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachMvpView((V) this);
        initImmersionBar();
        dialog = DiaLogUtils.createLoadingDialog(this, "请骚等~");
        initView();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);//1
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }

    protected abstract P createPresenter();

    protected void initImmersionBar() {
        ImmersionBar.with(this).init();
    }
}
