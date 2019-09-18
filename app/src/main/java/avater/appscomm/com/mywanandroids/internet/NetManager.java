package avater.appscomm.com.mywanandroids.internet;

import java.util.List;

import avater.appscomm.com.mywanandroids.bean.BannerBean;
import avater.appscomm.com.mywanandroids.bean.CurrencyBean;
import avater.appscomm.com.mywanandroids.bean.MainListBean;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;
import avater.appscomm.com.mywanandroids.bean.ProjectListBean;
import avater.appscomm.com.mywanandroids.bean.ProjectTitleBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public enum NetManager {
    INSTANCE;

    private ObservableTransformer threadTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void getHomeList(int page, BaseObserver<MainListBean.DataBean> observer) {
        RetrofitFactory.getInstence().getService().getHomeList(page).compose(threadTransformer()).subscribe(observer);
    }

    public void getBanner(BaseObserver<List<BannerBean.DataBean>> observer) {
        RetrofitFactory.getInstence().getService().getBanner().compose(threadTransformer()).subscribe(observer);
    }

    public void getProjectTittle(BaseObserver<List<ProjectTitleBean.DataBean>> observer) {
        RetrofitFactory.getInstence().getService().getProjectTitle().compose(threadTransformer()).subscribe(observer);
    }

    public void collectIn(String id, BaseObserver<CurrencyBean.DataBean> observer) {
        RetrofitFactory.getInstence().getService().collectIn(id).compose(threadTransformer()).subscribe(observer);
    }

    public void cancelCollect(String id, BaseObserver<CurrencyBean.DataBean> observer) {
        RetrofitFactory.getInstence().getService().cancelCollect(id).compose(threadTransformer()).subscribe(observer);
    }

    public void getProjectList(int page, String id, BaseObserver<ProjectListBean.DataBean> observer) {
        RetrofitFactory.getInstence().getService().getProjectList(page, id).compose(threadTransformer()).subscribe(observer);
    }

    public void getNavigateList(BaseObserver<List<NavigateBean.DataBean>> observer) {
        RetrofitFactory.getInstence().getService().getNavigateList().compose(threadTransformer()).subscribe(observer);
    }

    public void login(String userName, String passWord, BaseObserver<CurrencyBean.DataBean> observer) {
        RetrofitFactory.getInstence().getService().login(userName, passWord).compose(threadTransformer()).subscribe(observer);
    }

    public void loginOut(BaseObserver<CurrencyBean.DataBean> observer) {
        RetrofitFactory.getInstence().getService().loginOut().compose(threadTransformer()).subscribe(observer);
    }


}
