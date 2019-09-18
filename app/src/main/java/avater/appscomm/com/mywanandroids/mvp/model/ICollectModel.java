package avater.appscomm.com.mywanandroids.mvp.model;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;

public interface ICollectModel {
    void handleCollectIn(String id, OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener);

    void handleCancelCollect(String id, OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener);
}
