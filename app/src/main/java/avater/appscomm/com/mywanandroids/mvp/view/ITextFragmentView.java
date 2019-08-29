package avater.appscomm.com.mywanandroids.mvp.view;

import avater.appscomm.com.mywanandroids.base.IBaseMvpView;

public interface ITextFragmentView extends IBaseMvpView {
    void getMainListSuccess();

    void getMainListFail();

    int getPage();

    void getBannerSuccess();

    void getBannerFail();
}
