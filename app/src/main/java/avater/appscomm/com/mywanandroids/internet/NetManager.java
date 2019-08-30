package avater.appscomm.com.mywanandroids.internet;

import java.util.List;

import avater.appscomm.com.mywanandroids.bean.BannerBean;
import avater.appscomm.com.mywanandroids.bean.MainListBean;
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


}
