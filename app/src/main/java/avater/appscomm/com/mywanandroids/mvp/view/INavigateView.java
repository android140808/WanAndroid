package avater.appscomm.com.mywanandroids.mvp.view;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.IBaseMvpView;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;

public interface INavigateView extends IBaseMvpView {
    void navigateListSuccess(List<NavigateBean.DataBean> list);

    void navigateListFail(String msg);
}
