package avater.appscomm.com.mywanandroids.mvp.view;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.IBaseMvpView;
import avater.appscomm.com.mywanandroids.bean.BannerBean;
import avater.appscomm.com.mywanandroids.bean.MainListBean;

public interface IFragmentHomeView extends IBaseMvpView {
    void getMainListSuccess(MainListBean.DataBean dataBean, String type);

    void getMainListFail();

    int getPage();

    void getBannerSuccess(List<BannerBean.DataBean> dataBeans);

    void getBannerFail();
}
