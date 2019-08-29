package avater.appscomm.com.mywanandroids.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import avater.appscomm.com.mywanandroids.base.BaseMvpFragment;
import avater.appscomm.com.mywanandroids.mvp.presenter.TextFramentPresenter;
import avater.appscomm.com.mywanandroids.mvp.view.ITextFragmentView;

public class TextFragment extends BaseMvpFragment<ITextFragmentView, TextFramentPresenter> implements ITextFragmentView {
    @Override
    protected TextFramentPresenter createPresenter() {
        return new TextFramentPresenter();
    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public Dialog getLoadDialog() {
        return null;
    }

    @Override
    public void cancelLoadDialog() {

    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getMainListSuccess() {

    }

    @Override
    public void getMainListFail() {

    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void getBannerSuccess() {

    }

    @Override
    public void getBannerFail() {

    }
}
