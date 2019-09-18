package avater.appscomm.com.mywanandroids.mvp.model.impl;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;
import avater.appscomm.com.mywanandroids.internet.BaseObserver;
import avater.appscomm.com.mywanandroids.internet.NetManager;
import avater.appscomm.com.mywanandroids.mvp.model.IPersionModel;

public class IPresionModelImpl implements IPersionModel {
    @Override
    public void handleLogin(String userName, String passWord, final OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener) {
        NetManager.INSTANCE.login(userName, passWord, new BaseObserver<CurrencyBean.DataBean>() {
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
    public void handleLoginOut(final OnLoadDatasLostener<CurrencyBean.DataBean> onLoadDatasLostener) {
        NetManager.INSTANCE.loginOut(new BaseObserver<CurrencyBean.DataBean>() {
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
