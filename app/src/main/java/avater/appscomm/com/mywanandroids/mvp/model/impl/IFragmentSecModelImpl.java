package avater.appscomm.com.mywanandroids.mvp.model.impl;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.ProjectListBean;
import avater.appscomm.com.mywanandroids.bean.ProjectTitleBean;
import avater.appscomm.com.mywanandroids.internet.BaseObserver;
import avater.appscomm.com.mywanandroids.internet.NetManager;
import avater.appscomm.com.mywanandroids.mvp.model.IFragmentSecModel;

public class IFragmentSecModelImpl implements IFragmentSecModel {
    @Override
    public void handleProjectTittle(final OnLoadDatasLostener<List<ProjectTitleBean.DataBean>> onLoadDatasLostener) {
        NetManager.INSTANCE.getProjectTittle(new BaseObserver<List<ProjectTitleBean.DataBean>>() {
            @Override
            protected void onSuccees(List<ProjectTitleBean.DataBean> dataBeans) throws Exception {
                onLoadDatasLostener.onSuccess(dataBeans);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                onLoadDatasLostener.onFail(error);
            }
        });
    }

    @Override
    public void handleProjectList(int page, String id, final OnLoadDatasLostener<ProjectListBean.DataBean> onLoadDatasLostener) {
        NetManager.INSTANCE.getProjectList(page, id, new BaseObserver<ProjectListBean.DataBean>() {
            @Override
            protected void onSuccees(ProjectListBean.DataBean dataBean) throws Exception {
                onLoadDatasLostener.onSuccess(dataBean);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                onLoadDatasLostener.onFail(error);
            }
        });
    }
}
