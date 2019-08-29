package avater.appscomm.com.mywanandroids.base;

import java.lang.ref.WeakReference;

public class BaseMvpPresenter<V extends IBaseMvpView> {
    protected V mView;
    private WeakReference<V> weakReferenceView;

    protected V getView() {
        if (mView == null) {
            throw new IllegalStateException("View not attached");
        } else {
            return mView;
        }
    }

    public void attachMvpView(V view) {
        if (view != null) {
            weakReferenceView = new WeakReference<>(view);
            this.mView = weakReferenceView.get();
        }
    }

    public void detachMvpView() {
        weakReferenceView.clear();
        weakReferenceView = null;
        mView = null;
    }

}
