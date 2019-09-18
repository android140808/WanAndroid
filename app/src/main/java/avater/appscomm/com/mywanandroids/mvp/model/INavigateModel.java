package avater.appscomm.com.mywanandroids.mvp.model;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;

public interface INavigateModel {
    void handleNavigate(OnLoadDatasLostener<List<NavigateBean.DataBean>> listOnLoadDatasLostener);
}
