package avater.appscomm.com.mywanandroids.mvp.model.impl;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;
import avater.appscomm.com.mywanandroids.internet.BaseObserver;
import avater.appscomm.com.mywanandroids.internet.NetManager;
import avater.appscomm.com.mywanandroids.mvp.model.ICollectModel;

public class ICollectModelImpl implements ICollectModel {
    @Override
    public void handleCollectIn(String id, final OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener) {
        NetManager.INSTANCE.collectIn(id, new BaseObserver<CurrencyBean.DataBean>() {
            @Override
            protected void onSuccees(CurrencyBean.DataBean dataBean) throws Exception {
                onLoadDatasLostener.onSuccess(dataBean);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                onLoadDatasLostener.onFail(error);
            }
        });
    }

    @Override
    public void handleCancelCollect(String id, final OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener) {
        NetManager.INSTANCE.cancelCollect(id, new BaseObserver<CurrencyBean.DataBean>() {
            @Override
            protected void onSuccees(CurrencyBean.DataBean dataBean) throws Exception {
                onLoadDatasLostener.onSuccess(dataBean);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                onLoadDatasLostener.onFail(error);
            }
        });
    }
}
