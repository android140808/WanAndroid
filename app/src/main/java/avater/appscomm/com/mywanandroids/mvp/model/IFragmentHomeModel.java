package avater.appscomm.com.mywanandroids.mvp.model;


import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.MainListBean;

public interface IFragmentHomeModel {
    void handleHomeList(int page, OnLoadDatasLostener<MainListBean.DataBean> listener);
}
