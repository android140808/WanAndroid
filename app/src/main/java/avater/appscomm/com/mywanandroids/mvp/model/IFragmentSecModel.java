package avater.appscomm.com.mywanandroids.mvp.model;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.OnLoadDatasLostener;
import avater.appscomm.com.mywanandroids.bean.ProjectListBean;
import avater.appscomm.com.mywanandroids.bean.ProjectTitleBean;

public interface IFragmentSecModel {
    void handleProjectTittle(OnLoadDatasLostener<List<ProjectTitleBean.DataBean>> onLoadDatasLostener);

    void handleProjectList(int page, String id, OnLoadDatasLostener<ProjectListBean.DataBean> onLoadDatasLostener);
}
