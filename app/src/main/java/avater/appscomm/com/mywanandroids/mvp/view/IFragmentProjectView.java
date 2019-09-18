package avater.appscomm.com.mywanandroids.mvp.view;

import java.util.List;

import avater.appscomm.com.mywanandroids.base.IBaseMvpView;
import avater.appscomm.com.mywanandroids.bean.ProjectTitleBean;

public interface IFragmentProjectView extends IBaseMvpView {
    void getProjectTittleSuccess(List<ProjectTitleBean.DataBean> dataBeans);

    void getProjectTittleFail(String msg);
}

