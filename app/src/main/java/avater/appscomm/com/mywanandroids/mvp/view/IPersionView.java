package avater.appscomm.com.mywanandroids.mvp.view;

import avater.appscomm.com.mywanandroids.base.IBaseMvpView;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;

public interface IPersionView extends IBaseMvpView {
    void loginOutSuccess(CurrencyBean.DataBean dataBean);

    void loginOutFail(String msg);
}
