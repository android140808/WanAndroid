package avater.appscomm.com.mywanandroids.base;

import android.app.Dialog;

public interface IBaseMvpView {
    Dialog getLoadDialog();

    void cancelLoadDialog();
}
