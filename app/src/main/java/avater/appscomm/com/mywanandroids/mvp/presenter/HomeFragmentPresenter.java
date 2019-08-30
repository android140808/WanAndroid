package avater.appscomm.com.mywanandroids.mvp.presenter;


import java.util.List;

import avater.appscomm.com.mywanandroids.base.BaseMvpPresenter;
import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.BannerBean;
import avater.appscomm.com.mywanandroids.bean.MainListBean;
import avater.appscomm.com.mywanandroids.mvp.model.IBannerModel;
import avater.appscomm.com.mywanandroids.mvp.model.IFragmentHomeModel;
import avater.appscomm.com.mywanandroids.mvp.model.impl.IBannerImpl;
import avater.appscomm.com.mywanandroids.mvp.model.impl.IFragmentHomeImpl;
import avater.appscomm.com.mywanandroids.mvp.view.IFragmentHomeView;

public class HomeFragmentPresenter extends BaseMvpPresenter<IFragmentHomeView> {

    private IFragmentHomeModel iFragmentHomeModel;
    private IBannerModel iBannerModel;

    public HomeFragmentPresenter() {
        iFragmentHomeModel = new IFragmentHomeImpl();
        iBannerModel = new IBannerImpl();
    }

    public void getHomeList(final String type) {
        if (mView == null) {
            return;
        }
        iFragmentHomeModel.handleHomeList(mView.getPage(), new OnLoadDatasLostener<MainListBean.DataBean>() {
            @Override
            public void onSuccess(MainListBean.DataBean dataBean) {
                mView.cancelLoadDialog();
                mView.getMainListSuccess(dataBean, type);
            }

            @Override
            public void onFail(String msg) {
                if (type.equals("首次加载")) {
                    mView.cancelLoadDialog();
                }
                mView.getMainListFail();
            }
        });
    }

    public void getBanner() {
        if (mView == null) {
            return;
        }
        iBannerModel.handleBanner(new OnLoadDatasLostener<List<BannerBean.DataBean>>() {
            @Override
            public void onSuccess(List<BannerBean.DataBean> dataBeans) {
                mView.getBannerSuccess(dataBeans);
            }

            @Override
            public void onFail(String msg) {
                mView.getBannerFail();
            }
        });
    }


}
