package avater.appscomm.com.mywanandroids.mvp.presenter;

import avater.appscomm.com.mywanandroids.base.BaseMvpPresenter;
import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;
import avater.appscomm.com.mywanandroids.mvp.model.IPersionModel;
import avater.appscomm.com.mywanandroids.mvp.model.impl.IPresionModelImpl;
import avater.appscomm.com.mywanandroids.mvp.view.IPersionView;

public class PresionPreseneter extends BaseMvpPresenter<IPersionView> {

    private IPersionModel iPersionModel;

    public PresionPreseneter() {
        iPersionModel = new IPresionModelImpl();
    }

    public void loginOut() {
        if (mView == null) {
            return;
        }
        iPersionModel.handleLoginOut(new OnLoadDatasLostener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                getView().loginOutSuccess(dataBean);
            }

            @Override
            public void onFail(String msg) {
                getView().loginOutFail(msg);
            }
        });
    }

}
