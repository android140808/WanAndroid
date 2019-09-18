package avater.appscomm.com.mywanandroids.mvp.presenter;

import avater.appscomm.com.mywanandroids.base.BaseMvpPresenter;
import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;
import avater.appscomm.com.mywanandroids.bean.ProjectListBean;
import avater.appscomm.com.mywanandroids.mvp.model.ICollectModel;
import avater.appscomm.com.mywanandroids.mvp.model.IFragmentSecModel;
import avater.appscomm.com.mywanandroids.mvp.model.impl.ICollectModelImpl;
import avater.appscomm.com.mywanandroids.mvp.model.impl.IFragmentSecModelImpl;
import avater.appscomm.com.mywanandroids.mvp.view.IFragmentSecView;

public class ProjectSecFragmentPresenter extends BaseMvpPresenter<IFragmentSecView> {
    private ICollectModel iCollectModel;
    private IFragmentSecModel iFragmentSecModel;

    public ProjectSecFragmentPresenter() {
        iCollectModel = new ICollectModelImpl();
        iFragmentSecModel = new IFragmentSecModelImpl();
    }

    public void getProjectList(final String type) {
        if (mView == null) {
            return;
        }
        iFragmentSecModel.handleProjectList(mView.getPage(), mView.getClassifyId(), new OnLoadDatasLostener<ProjectListBean.DataBean>() {
            @Override
            public void onSuccess(ProjectListBean.DataBean dataBean) {
                getView().getProjectListSuccess(dataBean, type);
            }

            @Override
            public void onFail(String msg) {
                getView().getProjectListFail(msg);
            }
        });
    }

    public void handleCollectIn() {
        if (mView == null) {
            return;
        }
        getView().getLoadDialog().show();
        iCollectModel.handleCollectIn(mView.getClassifyId(), new OnLoadDatasLostener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                getView().cancelLoadDialog();
                getView().collectSuccess();
            }

            @Override
            public void onFail(String msg) {
                getView().cancelLoadDialog();
                getView().collectFail();
            }
        });
    }

    public void cancelCollect() {
        if (mView == null) {
            return;
        }
        getView().getLoadDialog().show();
        iCollectModel.handleCancelCollect(mView.getCollectId() + "", new OnLoadDatasLostener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                getView().cancelLoadDialog();
                getView().cancelCollectSuccess();
            }

            @Override
            public void onFail(String msg) {
                getView().cancelLoadDialog();
                getView().cancelCollectFail();
            }
        });
    }


}
