package avater.appscomm.com.mywanandroids.mvp.presenter;

import avater.appscomm.com.mywanandroids.base.BaseMvpPresenter;
import avater.appscomm.com.mywanandroids.mvp.model.IBannerModel;
import avater.appscomm.com.mywanandroids.mvp.model.ITextFragmentModel;
import avater.appscomm.com.mywanandroids.mvp.model.impl.IBannerImpl;
import avater.appscomm.com.mywanandroids.mvp.model.impl.ITextFragmentMoelImpl;
import avater.appscomm.com.mywanandroids.mvp.view.ITextFragmentView;

public class TextFramentPresenter extends BaseMvpPresenter<ITextFragmentView> {

    private ITextFragmentModel iTextFragmentModel;
    private IBannerModel iBannerModel;

    public TextFramentPresenter() {
        iTextFragmentModel = new ITextFragmentMoelImpl();
        iBannerModel = new IBannerImpl();
    }

    public void getHomeList() {
        if (mView == null) {
            return;
        }
        iTextFragmentModel.handleHomeList();
    }

    public void getBanner() {
        if (mView == null) {
            return;
        }
    }
}
