package avater.appscomm.com.mywanandroids.mvp.model.impl;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;
import avater.appscomm.com.mywanandroids.internet.BaseObserver;
import avater.appscomm.com.mywanandroids.internet.NetManager;
import avater.appscomm.com.mywanandroids.internet.RetrofitFactory;
import avater.appscomm.com.mywanandroids.mvp.model.INavigateModel;

public class INavigateModelImpl implements INavigateModel {
    @Override
    public void handleNavigate(final OnLoadDatasLostener<List<NavigateBean.DataBean>> listOnLoadDatasLostener) {
        NetManager.INSTANCE.getNavigateList(new BaseObserver<List<NavigateBean.DataBean>>() {
            @Override
            protected void onSuccees(List<NavigateBean.DataBean> list) throws Exception {
                listOnLoadDatasLostener.onSuccess(list);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                listOnLoadDatasLostener.onFail(error);
            }
        });
    }
}
