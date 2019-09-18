package avater.appscomm.com.mywanandroids.mvp.presenter;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.BaseMvpPresenter;
import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.ProjectTitleBean;
import avater.appscomm.com.mywanandroids.mvp.model.IFragProjectModel;
import avater.appscomm.com.mywanandroids.mvp.model.impl.IFragmentModelImpl;
import avater.appscomm.com.mywanandroids.mvp.view.IFragmentProjectView;

public class ProjectFragmentPresenter extends BaseMvpPresenter<IFragmentProjectView> {
    private IFragProjectModel iFragProjectModel;

    public ProjectFragmentPresenter() {
        iFragProjectModel = new IFragmentModelImpl();
    }

    public void getProjectTittle() {
        if (mView == null) {
            return;
        }
        iFragProjectModel.handleProjectTittle(new OnLoadDatasLostener<List<ProjectTitleBean.DataBean>>() {
            @Override
            public void onSuccess(List<ProjectTitleBean.DataBean> dataBeans) {
                getView().getProjectTittleSuccess(dataBeans);
            }

            @Override
            public void onFail(String msg) {
                getView().cancelLoadDialog();
                getView().getProjectTittleFail(msg);
            }
        });
    }
}
