package avater.appscomm.com.mywanandroids.mvp.view;

import avater.appscomm.com.mywanandroids.base.IBaseMvpView;
import avater.appscomm.com.mywanandroids.bean.ProjectListBean;

public interface IFragmentSecView extends IBaseMvpView {
    void getProjectListSuccess(ProjectListBean.DataBean dataBeanList, String type);

    void getProjectListFail(String msg);

    int getPage();

    String getClassifyId();

    void collectSuccess();

    void collectFail();

    String getCollectId();

    void cancelCollectSuccess();

    void cancelCollectFail();
}
