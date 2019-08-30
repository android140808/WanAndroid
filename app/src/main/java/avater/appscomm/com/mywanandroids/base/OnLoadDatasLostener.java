package avater.appscomm.com.mywanandroids.base;

public interface OnLoadDatasLostener<T> {
    void onSuccess(T t);

    void onFail(String msg);
}
