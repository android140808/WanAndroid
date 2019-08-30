package avater.appscomm.com.mywanandroids.mvp.model;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.BannerBean;

public interface IBannerModel {
    void handleBanner(OnLoadDatasLostener<List<BannerBean.DataBean>> listener);
}
