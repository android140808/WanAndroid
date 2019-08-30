package avater.appscomm.com.mywanandroids.mvp.model.impl;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.BannerBean;
import avater.appscomm.com.mywanandroids.internet.BaseObserver;
import avater.appscomm.com.mywanandroids.internet.NetManager;
import avater.appscomm.com.mywanandroids.mvp.model.IBannerModel;

public class IBannerModelImpl implements IBannerModel {
    @Override
    public void handleBanner(final OnLoadDatasLostener<List<BannerBean.DataBean>> listener) {
        NetManager.INSTANCE.getBanner(new BaseObserver<List<BannerBean.DataBean>>() {
            @Override
            protected void onSuccees(List<BannerBean.DataBean> dataBeans) throws Exception {
                listener.onSuccess(dataBeans);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                listener.onFail(error);
            }
        });
    }
}
