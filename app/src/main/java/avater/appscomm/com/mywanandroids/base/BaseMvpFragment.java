package avater.appscomm.com.mywanandroids.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxFragment;

import avater.appscomm.com.mywanandroids.application.MyApplication;
import avater.appscomm.com.mywanandroids.utils.DiaLogUtils;
import avater.appscomm.com.mywanandroids.utils.HideUtil;

public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxFragment
        implements IBaseMvpView, View.OnClickListener, IFragment {

    protected P mPresenter;
    protected View rootView;
    protected Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        if (rootView instanceof ViewGroup) {
            HideUtil.init(getActivity(), (ViewGroup) rootView);
        }
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachMvpView((V) this);
        dialog = DiaLogUtils.createLoadingDialog(getActivity(), "请骚等~");
        initImmersionBar();
        initView();
        initData(savedInstanceState);
        return rootView;
    }

    protected abstract P createPresenter();

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }
}
