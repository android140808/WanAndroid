package avater.appscomm.com.mywanandroids.mvp.model.impl;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.MainListBean;
import avater.appscomm.com.mywanandroids.internet.BaseObserver;
import avater.appscomm.com.mywanandroids.internet.NetManager;
import avater.appscomm.com.mywanandroids.mvp.model.IFragmentHomeModel;

public class IFragmentHomeImpl implements IFragmentHomeModel {
    @Override
    public void handleHomeList(int page, final OnLoadDatasLostener<MainListBean.DataBean> listener) {
        NetManager.INSTANCE.getHomeList(page, new BaseObserver<MainListBean.DataBean>() {
            @Override
            protected void onSuccees(MainListBean.DataBean dataBean) throws Exception {
                listener.onSuccess(dataBean);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                listener.onFail(error);
            }
        });
    }
}
