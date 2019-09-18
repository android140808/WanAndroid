package avater.appscomm.com.mywanandroids.mvp.model;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;

public interface IPersionModel {
    void handleLogin(String userName, String passWord, OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener);

    void handleLoginOut(OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener);
}
