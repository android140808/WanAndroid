package avater.appscomm.com.mywanandroids.mvp.presenter;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.BaseMvpPresenter;
import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;
import avater.appscomm.com.mywanandroids.mvp.model.INavigateModel;
import avater.appscomm.com.mywanandroids.mvp.model.impl.INavigateModelImpl;
import avater.appscomm.com.mywanandroids.mvp.view.INavigateView;

public class NavigatePresenter extends BaseMvpPresenter<INavigateView> {
    private INavigateModel iNavigateModel;

    public NavigatePresenter() {
        iNavigateModel = new INavigateModelImpl();
    }

    public void getNavigateList() {
        if (mView == null) {
            return;
        }
        iNavigateModel.handleNavigate(new OnLoadDatasLostener<List<NavigateBean.DataBean>>() {
            @Override
            public void onSuccess(List<NavigateBean.DataBean> list) {
                getView().navigateListSuccess(list);
            }

            @Override
            public void onFail(String msg) {
                getView().navigateListFail(msg);
            }
        });
    }
}
